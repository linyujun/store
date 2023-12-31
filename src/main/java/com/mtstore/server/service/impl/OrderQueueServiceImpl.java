package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.OrderQueueEntity;
import com.mtstore.server.beans.mapper.OrderQueueMapper;
import com.mtstore.server.service.OrderQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  订单队列
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
@Service
@RequiredArgsConstructor
public class OrderQueueServiceImpl extends ServiceImpl<OrderQueueMapper, OrderQueueEntity> implements OrderQueueService {

    @Override
    public void finishOrder(String orderId) {
        lambdaUpdate().eq(OrderQueueEntity::getOrderId, orderId)
                .set(OrderQueueEntity::getIsSync, true)
                .set(OrderQueueEntity::getIsUpdate, true).update();
    }

    @Override
    public OrderQueueEntity findByOrderId(String orderId) {

        return getOne(lambdaQuery().eq(OrderQueueEntity::getOrderId, orderId).getWrapper());
    }

    @Override
    public List<OrderQueueEntity> selectQueueInFiveMinute() {

        return baseMapper.getListInFiveMinute();
    }

    @Override
    public List<OrderQueueEntity> selectQueueAfterFiveMinute() {

        return baseMapper.getListAfterFiveMinute();
    }

    @Override
    public void saveOrUpdateByOrderId(String orderId) {
        OrderQueueEntity entity = Optional.ofNullable(findByOrderId(orderId)).orElse(new OrderQueueEntity());
        entity.setOrderId(orderId);
        entity.setCreateTime(LocalDateTime.now());

        saveOrUpdate(entity);
    }
}
