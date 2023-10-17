package com.mtstore.server.beans.dto.mall.order;

import com.alibaba.fastjson.JSONObject;
import com.mtstore.server.beans.enums.OrderEnum;
import lombok.Data;

import java.math.BigDecimal;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
import lombok.experimental.Accessors;

/**
* @author songsir
* @date 2023-04-20
*/
@Data
@Accessors(chain = true)
public class OrderDto extends BaseDto{
    @ApiModelProperty("订单id")
    private String orderId;

    /** 商品总量 */
    @ApiModelProperty("商品总量")
    private Integer totalNum;

    /** 订单总价 */
    @ApiModelProperty("订单总价")
    private BigDecimal totalPrice;

    @ApiModelProperty("订单积分抵扣")
    private BigDecimal totalCredit;

    /** 实付金额 */
    @ApiModelProperty("实付金额")
    private BigDecimal payPrice;

    /** 优惠券 */
    @ApiModelProperty("优惠券")
    private Integer couponId;

    /** 优惠券金额 */
    @ApiModelProperty("优惠券金额")
    private BigDecimal couponPrice;

    /** 邮费 */
    @ApiModelProperty("邮费")
    private BigDecimal postagePrice;

    /** 用户备注 */
    @ApiModelProperty("用户备注")
    private String userRemarks;

    @ApiModelProperty("商家备注")
    private String sellerRemarks;

    /** 门店id */
    @ApiModelProperty("门店id")
    private Integer storeId;

    /** 配送方式 */
    @ApiModelProperty("配送方式")
    private String shippingType;

    /** 扩展信息 */
    @ApiModelProperty("扩展信息")
    private String extraInfo;

    /** 收件信息 */
    @ApiModelProperty("收件信息")
    private JSONObject addressInfo;

    /** 快递信息 */
    @ApiModelProperty("快递信息")
    private JSONObject deliveryInfo;

    private String productInfo;

    /** 支付状态 */
    @ApiModelProperty("支付状态")
    private Boolean isPaid = false;

    /** 支付方式 */
    @ApiModelProperty("支付方式")
    private String payType;

    /** 支付订单id */
    @ApiModelProperty("支付订单id")
    private String payOrderId;

    /** 状态 */
    @ApiModelProperty("状态")
    private OrderEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;
}
