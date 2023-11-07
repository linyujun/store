package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;

/**
* @author songsir
* 秒杀商品关系
*/
@Data
@TableName(value = "kz_store_seckill_detail", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-秒杀商品关系对象", description = "商城-秒杀商品关系表")
public class StoreSeckillDetailEntity extends BaseEntity{
    /** 秒杀活动id */
    @ApiModelProperty("秒杀活动id")
    private Integer seckillId;

    /** 商品id */
    @ApiModelProperty("商品id")
    private Integer productId;

    /** 商品sku表 */
    @ApiModelProperty("商品sku表")
    private Integer productDetailId;

    /** 原价 */
    @ApiModelProperty("原价")
    private BigDecimal price;

    /** 秒杀价 */
    @ApiModelProperty("秒杀价")
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
