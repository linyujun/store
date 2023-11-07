package com.mtstore.server.service;

import com.mtstore.server.beans.entity.CouponCategoryEntity;
import com.mtstore.server.beans.dto.mall.coupon.CouponCategoryDto;

import java.util.List;

/**
* @author songsir
* 优惠券-分类关系
*/
public interface CouponCategoryService extends IKService<CouponCategoryEntity, CouponCategoryDto>{

    CouponCategoryEntity findByCouponId(Integer couponId);
    /**
     * 简单写入
     * @param couponId
     * @param categoryId
     * @return
     */
    CouponCategoryEntity save(Integer couponId, Integer categoryId, List<Integer> categoryList);

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
