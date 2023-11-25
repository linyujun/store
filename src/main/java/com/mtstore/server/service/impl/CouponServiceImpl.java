package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.coupon.CouponDto;
import com.mtstore.server.beans.entity.CouponCategoryEntity;
import com.mtstore.server.beans.entity.CouponProductEntity;
import com.mtstore.server.beans.entity.UserCouponEntity;
import com.mtstore.server.beans.enums.CouponScopeEnum;
import com.mtstore.server.beans.enums.CouponTypeEnum;
import com.mtstore.server.service.CouponCategoryService;
import com.mtstore.server.service.CouponProductService;
import com.mtstore.server.service.UserCouponService;
import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.beans.mapper.StoreCouponMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 优惠券
* @author songsir
* @date 2023-05-06
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl extends ServiceImpl<StoreCouponMapper, CouponEntity> implements CouponService {

    final CouponCategoryService couponCategoryService;

    final CouponProductService couponProductService;

    final UserCouponService userCouponService;

    @Override
    @Transactional
    public CouponEntity saveOrUpdate(CouponDto dto) {
        CouponEntity entity = Optional
                .ofNullable(dto.getId())
                .map(this::getById)
                .orElse(new CouponEntity());
        BeanUtils.copyProperties(dto, entity);
        entity.setStatus(1);
        entity.setStatusDesc("正常");
        saveOrUpdate(entity);

        //如果是商品优惠券
        if (dto.getScope() == CouponScopeEnum.PRODUCT) {
            couponProductService.save(entity.getId(), dto.getProductIds());
        }

        //如果是分类优惠券
        if (dto.getScope() == CouponScopeEnum.CATEGORY) {
            couponCategoryService.save(entity.getId(), dto.getCategoryId(), dto.getCategoryList());
        }

        return entity;
    }

    @Override
    public List<CouponEntity> findAllByCouponIds(Integer couponIds) {
        return lambdaQuery()
                .eq(CouponEntity::getEnabled, true)
                .eq(CouponEntity::getIsDelete, false)
                .eq(CouponEntity::getStatus, 1)
                .in(CouponEntity::getId, couponIds)
                .list();
    }

    @Override
    public List<Integer> findCouponsByProductId(Integer productId) {
        List<CouponProductEntity>  todoList = couponProductService
                .lambdaQuery()
                .eq(CouponProductEntity::getProductId, productId).list();
        if (CollectionUtils.isEmpty(todoList)) {
            return null;
        }
        return todoList.stream().map(CouponProductEntity::getCouponId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> findCouponsByCategoryId(Integer categoryId) {
        List<CouponCategoryEntity>  todoList = couponCategoryService
                .lambdaQuery()
                .eq(CouponCategoryEntity::getCategoryId, categoryId).list();
        if (CollectionUtils.isEmpty(todoList)) {
            return null;
        }
        return todoList.stream().map(CouponCategoryEntity::getCouponId).collect(Collectors.toList());
    }

    @Override
    public CouponEntity findCouponByUserId(Integer userId, Integer userCouponId) {
        //TODO 需要检查 未使用
        QueryWrapper<UserCouponEntity> queryWrapper = new QueryWrapper<>();
        UserCouponEntity userCouponEntity = userCouponService.getOne(queryWrapper.lambda()
                .eq(UserCouponEntity::getUid, userId)
                .eq(UserCouponEntity::getStatus, 0)
                .eq(UserCouponEntity::getId, userCouponId));

        return Optional.ofNullable(userCouponEntity)
                .map(UserCouponEntity::getCouponId)
                .map(this::getById)
                .orElse(null);
    }

    @Override
    public BigDecimal calcPriceByCouponId(BigDecimal totalPrice, Integer userCouponId, Integer userId) {
        CouponEntity couponEntity = findCouponByUserId(userId, userCouponId);
        log.info("couponEntity {}", couponEntity);
        if (null == couponEntity) {
            return BigDecimal.ZERO;
        }
        //无门槛
        if (couponEntity.getCouponType() == CouponTypeEnum.ALL) {
            if (totalPrice.compareTo(couponEntity.getPrice()) > 0) {

                return couponEntity.getPrice();
            }
        }
        //折扣
        if (couponEntity.getCouponType() == CouponTypeEnum.DISCOUNT) {
            if (totalPrice.compareTo(couponEntity.getPrice()) > 0) {

                return totalPrice.subtract(totalPrice.multiply(couponEntity.getDiscount()));
            }
        }
        //满减
        if (couponEntity.getCouponType() == CouponTypeEnum.FULL) {
            if (totalPrice.compareTo(couponEntity.getPrice()) > 0 &&
                    totalPrice.compareTo(couponEntity.getMinPrice()) > 0) {

                return couponEntity.getPrice();
            }
        }

        return BigDecimal.ZERO;
    }

    @Override
    public List<Integer> findCouponsByPublic() {
        ArrayList<String> types = new ArrayList<>(Arrays.asList("discount", "all"));
        List<CouponEntity>  todoList = lambdaQuery()
                .eq(CouponEntity::getEnabled, true)
                .eq(CouponEntity::getIsDelete, false)
                .eq(CouponEntity::getStatus, 1)
                .in(CouponEntity::getCouponType, types)
                .list();
        if (CollectionUtils.isEmpty(todoList)) {

            return null;
        }
        List<Integer> couponIds = todoList.stream().map(CouponEntity::getId).collect(Collectors.toList());

        return couponIds;
    }

    @Override
    public CouponEntity getDetail(Integer id) {
        CouponEntity entity = Optional.ofNullable(getById(id)).orElseThrow(() -> new RuntimeException("优惠券不存在"));
        if (entity.getScope() == CouponScopeEnum.CATEGORY) {
            CouponCategoryEntity couponCategoryEntity = couponCategoryService.findByCouponId(id);
            if (null != couponCategoryEntity) {
                entity.setCategoryId(couponCategoryEntity.getCategoryId());
                entity.setCategoryList(couponCategoryEntity.getCategoryList());
            }
        }
        if (entity.getScope() == CouponScopeEnum.PRODUCT) {
            List<CouponProductEntity> resultList = couponProductService.findListByCouponId(id);
            if (CollectionUtils.isNotEmpty(resultList)) {
                entity.setProductIds(resultList.stream().map(CouponProductEntity::getProductId).collect(Collectors.toList()));
            }
        }

        return entity;
    }

    /**
     * 检查优惠券合法性
     * @param couponEntity
     */
    private void checkCouponValid(CouponEntity couponEntity) {
        if (couponEntity.getStartTime().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("优惠券活动暂未开始~");
        }
        if (couponEntity.getEndTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("优惠券活动已结束~");
        }
        if (couponEntity.getEnabled() == false) {
            throw new RuntimeException("优惠券已下线~");
        }
        if (couponEntity.getIsDelete() == true) {
            throw new RuntimeException("优惠券已下线~");
        }
        if (couponEntity.getUsed() >= couponEntity.getTotal()) {
            throw new RuntimeException("优惠券已领光~");
        }
        //持有量检测
        if (couponEntity.getHoldLimit() > 0) {
            //获取当前用户优惠券的持有量
            Long holdNum = userCouponService.countHoldNum(couponEntity.getId(), LoggedUser.get().getId());
            log.info("limit {},holdNum {}", couponEntity.getHoldLimit(), holdNum);
            if (couponEntity.getHoldLimit() > 0 &&  holdNum >= couponEntity.getHoldLimit()) {
                throw new RuntimeException("优惠券已达到领取上限~");
            }
        }
    }

    @Override
    @Transactional
    public void send(Integer couponId, Integer num, List<Integer> userIds) {
        CouponEntity couponEntity = Optional.ofNullable(getById(couponId))
                .orElseThrow(() -> new RuntimeException("优惠券不存在"));
        checkCouponValid(couponEntity);
        if (CollectionUtils.isEmpty(userIds)) {
            throw new RuntimeException("未选择用户");
        }
        userIds.forEach(userId -> {
            for(int x = 0; x < num; x++) {
                couponEntity.setUsed(couponEntity.getUsed() + 1);
                userCouponService.save(userId, couponEntity, "system");
                saveOrUpdate(couponEntity);
            }
        });

    }

    @Override
    @Transactional
    public void issue(Integer couponId, Integer userId) {
        CouponEntity couponEntity = Optional.ofNullable(getById(couponId))
                .orElseThrow(() -> new RuntimeException("优惠券不存在"));
        checkCouponValid(couponEntity);
        couponEntity.setUsed(couponEntity.getUsed() + 1);
        userCouponService.save(LoggedUser.get().getUserId(), couponEntity, "user");

        saveOrUpdate(couponEntity);
    }

    /**
     * 优惠券核销
     * @param couponId
     */
    @Override
    public void verify(Integer couponId) {
        Optional.ofNullable(getById(couponId)).ifPresent(entity -> {
            entity.setStatus(1);
            entity.setStatusDesc("已使用");

            saveOrUpdate(entity);
        });
    }

    /**
     * 优惠券返还
     * @param couponId
     */
    @Override
    public void rollback(Integer couponId) {
        Optional.ofNullable(getById(couponId)).ifPresent(entity -> {
            entity.setStatus(0);
            entity.setStatusDesc("未使用");

            saveOrUpdate(entity);
        });
    }

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void activateCheck() {
        lambdaUpdate().eq(CouponEntity::getStatus, 0)
                .le(CouponEntity::getStartTime, LocalDateTime.now())
                .set(CouponEntity::getStatus, 1)
                .update();
    }

    @Override
    public void expiredCheck() {
        lambdaUpdate().eq(CouponEntity::getStatus, 1)
                .lt(CouponEntity::getEndTime, LocalDateTime.now())
                .set(CouponEntity::getStatus, 2)
                .update();
    }

}
