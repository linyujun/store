package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-05-06
*/
@Data
@TableName(value = "kz_store_coupon_product", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-优惠券商品关系对象", description = "商城-优惠券商品关系表")
public class CouponProductEntity extends BaseEntity{
    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 商品id */
    @ApiModelProperty("商品id")
    private Integer productId;

}
