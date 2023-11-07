package com.mtstore.server.service.impl;

import com.mtstore.server.beans.dto.mall.combination.StoreCombinationDto;
import com.mtstore.server.beans.dto.mall.order.OrderActivityDto;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import com.mtstore.server.service.StoreCombinationLogService;
import com.mtstore.server.beans.entity.StoreCombinationEntity;
import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.beans.entity.StoreOrderDetailEntity;
import com.mtstore.server.beans.entity.StoreOrderEntity;
import com.mtstore.server.beans.mapper.StoreCombinationMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreCombinationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

/**
* @author songsir
* 拼团
*/
@Service
@RequiredArgsConstructor
public class StoreCombinationServiceImpl extends ServiceImpl<StoreCombinationMapper, StoreCombinationEntity> implements StoreCombinationService {

    final StoreCombinationLogService combinationLogService;

    @Override
    public StoreCombinationEntity saveOrUpdate(StoreCombinationDto dto) {
        StoreCombinationEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new StoreCombinationEntity());
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(ActivityStatusEnum.PENDING);
        entity.setStatusDesc(ActivityStatusEnum.PENDING.getDesc());
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    /**
     * 检测活动约束
     * @param dto
     */
    @Override
    public void valid(StoreCombinationDto dto) {
        dto.getProductIds().forEach(product -> {
            Boolean result = lambdaQuery()
                    .lt(StoreCombinationEntity::getStartTime, dto.getEndTime())
                    .gt(StoreCombinationEntity::getEndTime, dto.getStartTime())
                    .ne(null != dto.getId(),StoreCombinationEntity::getId, dto.getId())
                    .apply(String.format("JSON_CONTAINS(product_ids,'%d','$')", product)).exists();
            if (result) throw new RuntimeException(String.format("一个商品同一时间只能参加一个拼团活动~ :%d", product));
        });
    }

    /**
     * 通过商品反查拼团活动
     * @param productId
     * @return
     */
    @Override
    public StoreCombinationEntity findByProductId(Integer productId) {
        return lambdaQuery()
                .ge(StoreCombinationEntity::getStartTime, LocalDateTime.now())
                .lt(StoreCombinationEntity::getEndTime, LocalDateTime.now())
                .apply(String.format("JSON_CONTAINS(product_ids,'%d','$')", productId)).one();
    }

    /**
     * 开团
     * @param order
     * @return
     */
    @Override
    public StoreCombinationLogEntity create(StoreOrderEntity order) {
        StoreOrderDetailEntity orderDetailEntity = order.getDetails()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("订单详情不为空~"));
        Integer combinationId = Optional.ofNullable(order.getActivityInfo())
                .map(OrderActivityDto::getCombinationId)
                .orElseThrow(() -> new RuntimeException("拼团活动id不为空~"));
        StoreCombinationEntity entity = Optional.ofNullable(getById(combinationId))
                .orElseThrow(() -> new RuntimeException("拼团活动不存在~"));
        StoreCombinationLogEntity logEntity = new StoreCombinationLogEntity()
                .setProductId(orderDetailEntity.getProductId())
                .setImgUrl(orderDetailEntity.getImgUrl())
                .setProductName(orderDetailEntity.getProductName())
                .setActivityName(entity.getActivityName())
                .setStartTime(entity.getStartTime())
                .setOrderId(order.getOrderId())
                .setGroupNum(entity.getGroupNum())
                .setLimitNum(entity.getLimitNum())
                .setPrice(orderDetailEntity.getPrice())
                .setCombinationPrice(orderDetailEntity.getPrice())
                .setEndTime(entity.getEndTime())
                .setExpiredTime(LocalDateTime.now().plusMinutes(entity.getExpireMinute()))
                .setCombinationId(entity.getId())
                .setUid(orderDetailEntity.getUid())
                .setStatus(0)
                .setStatusDesc("进行中")
                .setUid(orderDetailEntity.getUid())
                .setIsSuccess(false)
                .setIsLeader(true);
        combinationLogService.save(logEntity);
        logEntity.setGroupId(logEntity.getId());
        combinationLogService.saveOrUpdate(logEntity);

        return logEntity;
    }

    /**
     * 参团
     * @param order
     * @return
     */
    @Override
    public StoreCombinationLogEntity join(StoreOrderEntity order) {
        Integer combinationLogId = Optional.ofNullable(order.getActivityInfo())
                .map(OrderActivityDto::getCombinationLogId)
                .orElseThrow(() -> new RuntimeException("参团活动id不为空~"));
        StoreCombinationLogEntity logEntity = combinationLogService.getById(combinationLogId);
        logEntity.setId(null);
        logEntity.setOrderId(order.getOrderId());
        logEntity.setIsLeader(false);
        logEntity.setUid(order.getUid());
        combinationLogService.save(logEntity);
        //TODO 检测是否成团，超时，退款等信息
        return logEntity;
    }

    /**
     * 检查拼团活动
     */
    @Override
    public void check() {
        //开启拼团
        lambdaUpdate()
                .eq(StoreCombinationEntity::getEnabled, true)
                .eq(StoreCombinationEntity::getStatus, 0)
                .le(StoreCombinationEntity::getStartTime, LocalDateTime.now())
                .set(StoreCombinationEntity::getStatus, 1)
                .set(StoreCombinationEntity::getStatusDesc, "进行中")
                .update();

        //结束拼团，不可购买
        lambdaUpdate()
                .eq(StoreCombinationEntity::getEnabled, true)
                .eq(StoreCombinationEntity::getStatus, 1)
                .lt(StoreCombinationEntity::getEndTime, LocalDateTime.now())
                .set(StoreCombinationEntity::getStatus, 2)
                .set(StoreCombinationEntity::getStatusDesc, "已结束")
                .update();
    }

}
