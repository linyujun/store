package com.kinzhan.dev.platform.beans.dto.mall.product;

import lombok.Data;
import java.math.BigDecimal;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-14
*/
@Data
public class ProductDetailDto extends BaseDto{
    /** 唯一值 */
    @ApiModelProperty("唯一值")
    private String uuid;

    /** 商品ID */
    @ApiModelProperty("商品ID")
    @NotNull
    private Integer productId;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 商品属性索引值 (attr_value|attr_value[|....]) */
    @ApiModelProperty("商品属性索引值 (attr_value|attr_value[|....])")
    @NotBlank
    private String attrKey;

    /** 属性对应的库存 */
    @ApiModelProperty("属性对应的库存")
    @NotNull
    private Integer stockNum;

    /** 销量 */
    @ApiModelProperty("销量")
    private Integer sales;

    /** 属性金额 */
    @ApiModelProperty("属性金额")
    @NotNull
    private BigDecimal price;

    /** 成本价 */
    @ApiModelProperty("成本价")
    @NotNull
    private BigDecimal costPrice;

    /** 原价 */
    @ApiModelProperty("原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty("秒杀库存")
    private BigDecimal seckillNum;

    @ApiModelProperty("所需积分")
    private BigDecimal credit;

    @ApiModelProperty("差价")
    private BigDecimal creditPrice;

    @ApiModelProperty("积分商品库存")
    private BigDecimal creditNum;

    @ApiModelProperty("拼团价")
    private BigDecimal combinationPrice;

    @ApiModelProperty("拼团库存")
    private BigDecimal combinationNum;

    @ApiModelProperty("砍价")
    private BigDecimal bargainPrice;

    @ApiModelProperty("砍价库存")
    private BigDecimal bargainNum;

    @ApiModelProperty("一级返佣")
    private BigDecimal firstFee;

    @ApiModelProperty("二级返佣")
    private BigDecimal secondFee;

    @ApiModelProperty("三级返佣")
    private BigDecimal thirdFee;

    /** 商品条码 */
    @ApiModelProperty("商品条码")
    private String barCode;

    /** 重量 */
    @ApiModelProperty("重量")
    @NotNull
    private BigDecimal weight;

    /** 体积 */
    @ApiModelProperty("体积")
    @NotNull
    private BigDecimal volume;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 访问次数 */
    @ApiModelProperty("访问次数")
    private Integer visited;

    /** 状态，审核 */
    @ApiModelProperty("状态，审核")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;
}
