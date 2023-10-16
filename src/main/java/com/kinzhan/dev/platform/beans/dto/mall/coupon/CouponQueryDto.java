package com.kinzhan.dev.platform.beans.dto.mall.coupon;

import com.kinzhan.dev.platform.beans.annotion.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
* @author songsir
* @date 2023-04-20
*/
@Data
@ApiModel(value = "优惠券查询实体")
public class CouponQueryDto {
    /** 优惠券类型 */
    @ApiModelProperty("优惠券类型, ALL：无门槛，FULL:满减 DISCOUNT:折扣")
    @Query
    private String couponType;
}
