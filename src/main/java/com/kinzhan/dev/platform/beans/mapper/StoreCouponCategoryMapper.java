package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.CouponCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-05-06
*/
public interface StoreCouponCategoryMapper extends BaseMapper<CouponCategoryEntity> {

    @Delete("delete from kz_store_coupon_category where coupon_id = #{couponId}")
    void forceDeleteByCouponId(Integer couponId);
}
