package com.mtstore.server.beans.dto.mall.order;

import lombok.Data;

import java.math.BigDecimal;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-20
*/
@Data
public class OrderDetailDto extends BaseDto{
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
    private String attrName;

    /** 商品图片快照 */
    @ApiModelProperty("商品图片快照")
    private String imgUrl;

    /** 数量 */
    @ApiModelProperty("数量")
    private Integer cartNum;

    /** 商品单价 */
    @ApiModelProperty("商品单价")
    private BigDecimal price;

    /** 总价 */
    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    /** 用户id */
    @ApiModelProperty("用户id")
    private Integer uid;
}
