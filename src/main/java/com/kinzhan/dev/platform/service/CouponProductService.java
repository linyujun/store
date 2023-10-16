package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.CouponCategoryEntity;
import com.kinzhan.dev.platform.beans.entity.CouponProductEntity;
import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponProductDto;

import java.util.List;

/**
* @author songsir
* @date 2023-05-06
*/
public interface CouponProductService extends IKService<CouponProductEntity, CouponProductDto>{

    List<CouponProductEntity> findListByCouponId(Integer couponId);

    /**
     * 简单写入
     * @param couponId
     * @param productIds
     * @return
     */
    Boolean save(Integer couponId, List<Integer> productIds);

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);

    /**
     * 强制删除
     * @param couponId
     */
    void forceDeleteByCouponId(Integer couponId);
}
