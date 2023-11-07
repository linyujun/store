package com.mtstore.server.service;

import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.beans.entity.UserCouponEntity;
import com.mtstore.server.beans.dto.user.UserCouponDto;

/**
* 用户-优惠券关系
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
