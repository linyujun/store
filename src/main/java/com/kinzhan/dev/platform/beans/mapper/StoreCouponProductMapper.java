package com.kinzhan.dev.platform.beans.mapper;

import com.kinzhan.dev.platform.beans.entity.CouponProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
* @author songsir
* @date 2023-05-06
*/
public interface StoreCouponProductMapper extends BaseMapper<CouponProductEntity> {

    @Delete("delete from kz_store_coupon_product where coupon_id = #{couponId}")
    void forceDeleteByCouponId(Integer couponId);
}
