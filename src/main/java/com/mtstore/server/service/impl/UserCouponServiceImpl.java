package com.mtstore.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.beans.entity.UserCouponEntity;
import com.mtstore.server.beans.mapper.UserCouponMapper;
import com.mtstore.server.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

/**
* @author songsir
* 用户-优惠券关系
*/
@Service
@RequiredArgsConstructor
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCouponEntity> implements UserCouponService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public UserCouponEntity save(Integer userId, CouponEntity coupon, String obtainType) {
        String uuid = RandomUtil.randomNumbers(15);
        UserCouponEntity entity = BeanUtil.copyProperties(coupon, UserCouponEntity.class,
                "id", "createUser", "createTime", "updateTime", "status", "statusDesc");
        entity.setObtainType(obtainType);
        entity.setCouponId(coupon.getId());
        entity.setUid(userId);
        entity.setStatus(0);
        entity.setUuid(uuid);
        entity.setStatusDesc("未使用");
        entity.setCouponId(coupon.getId());
        saveOrUpdate(entity);

        return entity;
    }

    @Override
    public void expiredCheck() {
        lambdaUpdate()
                .eq(UserCouponEntity::getStatus, 0)
                .lt(UserCouponEntity::getUseEndTime, LocalDateTime.now())
                .set(UserCouponEntity::getStatus, 2)
                .set(UserCouponEntity::getStatusDesc, "已过期")
                .update();
    }

    @Override
    public Long countHoldNum(Integer couponId, Integer userId) {
        return lambdaQuery()
                .eq(UserCouponEntity::getCouponId, couponId)
                .eq(UserCouponEntity::getUid, userId)
                .eq(UserCouponEntity::getStatus, 0)
                .count();
    }
}
