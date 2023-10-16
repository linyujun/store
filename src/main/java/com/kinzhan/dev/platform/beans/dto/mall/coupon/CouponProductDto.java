package com.kinzhan.dev.platform.beans.dto.mall.coupon;

import lombok.Data;
import java.time.*;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-06
*/
@Data
public class CouponProductDto extends BaseDto{
    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 商品id */
    @ApiModelProperty("商品id")
    private Integer productId;

}
