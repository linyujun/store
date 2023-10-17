package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.dto.mall.order.OrderActivityDto;
import com.mtstore.server.beans.dto.mall.order.OrderAddressInfoDto;
import com.mtstore.server.beans.dto.mall.order.OrderDeliveryDto;
import com.mtstore.server.beans.enums.CartScopeEnum;
import com.mtstore.server.beans.enums.OrderEnum;
import com.mtstore.server.beans.enums.PayTypeEnum;
import com.mtstore.server.beans.enums.ShippingTypeEnum;
import com.mtstore.server.config.plugins.annotion.OneToMany;
import com.mtstore.server.service.StoreOrderDetailService;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* @date 2023-04-20
*/
@Data
@TableName(value = "kz_store_order", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城订单对象", description = "商城订单表")
public class StoreOrderEntity extends BaseEntity{
    /** 用户 */
    @ApiModelProperty("用户")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("订单商品详情")
    @TableField(exist = false)
    @OneToMany(from = "orderId", to = "details", clazz = StoreOrderDetailService.class, method = "findAllByOrderId")
    private List<StoreOrderDetailEntity> details;

    /** 商品总量 */
    @ApiModelProperty("商品总量")
    private Integer totalNum;

    /** 活动场景 */
    @ApiModelProperty("活动场景")
    private CartScopeEnum scope;

    /** 订单总价 */
    @ApiModelProperty("订单总价")
    private BigDecimal totalPrice;

    /** 积分抵扣 */
    @ApiModelProperty("积分抵扣")
    private BigDecimal totalCredit;

    /** 实付金额 */
    @ApiModelProperty("实付金额")
    private BigDecimal payPrice;

    /** 优惠券 */
    @ApiModelProperty("优惠券")
    private Integer couponId;

    /** 优惠券金额 */
    @ApiModelProperty("优惠金额")
    private BigDecimal couponPrice;

    /** 邮费 */
    @ApiModelProperty("运费")
    private BigDecimal postagePrice;

    /** 用户备注 */
    @ApiModelProperty("用户备注")
    private String userRemarks;

    @ApiModelProperty("卖家备注")
    private String sellerRemarks;

    /** 门店id */
    @ApiModelProperty("门店id")
    private Integer storeId;

    /** 配送方式 */
    @ApiModelProperty("配送方式")
    @TableField(typeHandler = EnumTypeHandler.class)
    private ShippingTypeEnum shippingType;

    /** 扩展信息 */
    @ApiModelProperty("扩展信息")
    private String extraInfo;

    @ApiModelProperty("商品Id")
    private Integer productId;

    @ApiModelProperty("商品信息")
    private String productInfo;

    /** 收件信息 */
    @ApiModelProperty("收件信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderAddressInfoDto addressInfo;

    /** 快递信息 */
    @ApiModelProperty("快递信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderDeliveryDto deliveryInfo;

    /** 活动信息 */
    @ApiModelProperty("活动信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderActivityDto activityInfo;

    /** 支付状态 */
    @ApiModelProperty("支付状态")
    private Boolean isPaid;

    /** 支付时间 */
    @ApiModelProperty("支付时间")
    private Integer payTime;

    /** 支付方式 */
    @ApiModelProperty("支付方式")
    private PayTypeEnum payType;

    /** 支付订单id */
    @ApiModelProperty("支付订单id")
    private Integer payOrderId;

    @ApiModelProperty("核销码")
    private String verifyCode;

    @ApiModelProperty("核销时间")
    private String verifyTime;

    @ApiModelProperty("核销员")
    private String verifyUid;

    @ApiModelProperty("上级分销员")
    private Integer firstUid;

    @ApiModelProperty("上上级分销员")
    private Integer secondUid;

    @ApiModelProperty("一级返佣")
    private BigDecimal firstFee;

    @ApiModelProperty("二级返佣")
    private BigDecimal secondFee;

    @ApiModelProperty("是否结算")
    private Boolean feeStatus;

    @TableField(exist = false)
    private UserEntity user;

    /** 状态 */
    @ApiModelProperty("状态")
    private OrderEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

//    @TableField(value = "count(id)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @TableField(exist = false)
    private Long total;

    @TableField(exist = false)
    private BigDecimal sumPrice;

}
