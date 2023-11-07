package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;

/**
* @author songsir
* 订单详情，sku级别
*/
@Data
@TableName(value = "kz_store_order_detail", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城订单详情，快照对象", description = "商城订单详情，快照表")
public class StoreOrderDetailEntity extends BaseEntity{
    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

    private String orderId;

    private Integer productId;

    private Integer productDetailId;

    /** 商品名称快照 */
    @ApiModelProperty("商品名称快照")
    private String productName;

    /** 商品属性快照 */
    @ApiModelProperty("商品属性快照")
    private String attrKey;

    /** 商品图片快照 */
    @ApiModelProperty("商品图片快照")
    private String imgUrl;

    /** 数量 */
    @ApiModelProperty("数量")
    private Integer cartNum;

    /** 重量 */
    @ApiModelProperty("重量")
    private BigDecimal weight;

    /** 体积 */
    @ApiModelProperty("体积")
    private BigDecimal volume;

    /** 商品单价 */
    @ApiModelProperty("商品单价")
    private BigDecimal price;

    /** 总价 */
    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

    /** 积分单价 */
    @ApiModelProperty("积分单价")
    private BigDecimal credit;;

    /** 总积分 */
    @ApiModelProperty("总积分")
    private BigDecimal totalCredit;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    /** 用户id */
    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;
}
