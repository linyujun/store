package com.kinzhan.dev.platform.beans.dto.mall.seckill;

import lombok.Data;

import java.math.BigDecimal;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.*;

import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-05-23
*/
@Data
public class SeckillDetailDto extends BaseDto{
    /** 秒杀活动id */
    @ApiModelProperty("秒杀活动id")
    private Integer seckillId;

    /** 商品id */
    @ApiModelProperty("商品id")
    @NotNull(message = "商品必填")
    private Integer productId;

    /** 商品sku表 */
    @ApiModelProperty("商品sku表")
    @NotNull(message = "SKU商品必填")
    private Integer productDetailId;

    /** 原价 */
    @ApiModelProperty("原价")
    @NotNull(message = "原价必填")
    private BigDecimal price;

    /** 秒杀价 */
    @ApiModelProperty("秒杀价")
    @NotNull(message = "活动价必填")
    private BigDecimal minPrice;

    /** 限制购买数量 0 不限制 */
    @ApiModelProperty("限制购买数量 0 不限制")
    private Integer limitNum;

    /** 库存数量 */
    @ApiModelProperty("库存数量")
    private Integer stockNum;

    /** 销量 */
    @ApiModelProperty("销量")
    private Integer sales;

    @ApiModelProperty("排序")
    private Integer sort;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
