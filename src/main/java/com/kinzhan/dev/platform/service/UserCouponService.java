package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.CouponEntity;
import com.kinzhan.dev.platform.beans.entity.UserCouponEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserCouponDto;

/**
* @author songsir
* @date 2023-05-08
*/
public interface UserCouponService extends IKService<UserCouponEntity, UserCouponDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 获得优惠券
     * @param coupon
     * @return
     */
    UserCouponEntity save(Integer userId, CouponEntity coupon, String obtainType);

    /**
     * 过期检测
     */
    void expiredCheck();

    /**
     * 持有数量检测
     * @param couponId
     * @param limit
     * @param userId
     */
    Long countHoldNum(Integer couponId, Integer userId);
}
