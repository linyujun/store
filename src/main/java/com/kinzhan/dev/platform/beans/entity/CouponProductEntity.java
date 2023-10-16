package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;

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
