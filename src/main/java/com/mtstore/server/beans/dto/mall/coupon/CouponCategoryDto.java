package com.mtstore.server.beans.dto.mall.coupon;

import lombok.Data;

import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
import lombok.experimental.Accessors;

/**
* @author songsir
* @date 2023-05-06
*/
@Data
@Accessors(chain = true)
public class CouponCategoryDto extends BaseDto{
    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 分类id */
    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("级联分类")
    private List<Integer> categoryList;
}
