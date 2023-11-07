package com.mtstore.server.service;

import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.beans.dto.mall.coupon.CouponDto;

import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* 商城优惠券
*/
public interface CouponService extends IKService<CouponEntity, CouponDto>{


    List<CouponEntity> findAllByCouponIds(Integer couponIds);

    /**
     * 通过商品匹配优惠券
     * @param productId
     * @return
     */
    List<Integer> findCouponsByProductId(Integer productId);

    /**
     * 通过分类匹配优惠券
     * @param categoryId
     * @return
     */
    List<Integer> findCouponsByCategoryId(Integer categoryId);


    /**
     * 通过用户id查找优惠券信息
     * @param userId
     * @param userCouponId
     * @return
     */
    CouponEntity findCouponByUserId(Integer userId, Integer userCouponId);


    /**
     * 计算优惠信息
     * @param userId
     * @param userCouponId
     * @return
     */
    BigDecimal calcPriceByCouponId(BigDecimal totalPrice, Integer userCouponId, Integer userId);

    /**
     * 获取通用优惠券
     *
     * @return
     */
    List<Integer> findCouponsByPublic();


    CouponEntity getDetail(Integer id);
    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 激活优惠券
     */
    void activateCheck();

    /**
     * 过期检测
     */
    void expiredCheck();

    /**
     * 发放优惠券
     * @param couponId
     * @param userIds
     */
    void send(Integer couponId, Integer num, List<Integer> userIds);

    /**
     * 领取优惠券
     * @param couponId
     */
    void issue(Integer couponId, Integer userId);

    /**
     * 优惠券核销
     * @param couponId
     */
    void verify(Integer couponId);

    /**
     * 退款，退货优惠券返还
     * @param couponId
     */
    void rollback(Integer couponId);
}
