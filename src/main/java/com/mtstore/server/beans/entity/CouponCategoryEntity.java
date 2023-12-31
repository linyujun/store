package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.util.List;

/**
* @author songsir
* @date 2023-05-06
*/
@Data
@TableName(value = "kz_store_coupon_category", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-优惠券分类关系对象", description = "商城-优惠券分类关系表")
public class CouponCategoryEntity extends BaseEntity{
    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 分类id */
    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("分类级联")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> categoryList;
}
