package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.dto.mall.order.OrderAddressInfoDto;
import com.mtstore.server.beans.dto.mall.order.OrderDeliveryDto;
import com.mtstore.server.beans.enums.AfterSaleGoodsEnum;
import com.mtstore.server.beans.enums.AfterSaleReturnEnum;
import com.mtstore.server.beans.enums.AfterSaleStatusEnum;
import com.mtstore.server.beans.enums.AfterSaleTypeEnum;
import com.mtstore.server.config.plugins.annotion.OneToMany;
import com.mtstore.server.service.StoreOrderDetailService;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* @date 2023-06-12
*/
@Data
@TableName(value = "kz_store_after_sales", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-售后记录对象", description = "商城-售后记录表")
public class StoreAfterSalesEntity extends BaseEntity{
    /** 商品信息 */
    @ApiModelProperty("商品信息")
    private String productInfo;

    /** 订单id */
    @ApiModelProperty("订单id")
    private String orderId;

    /** 用户id */
    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    /** 实际支付金额 */
    @ApiModelProperty("实际支付金额")
    private BigDecimal payPrice;

    /** 退款金额 */
    @ApiModelProperty("退款金额")
    private BigDecimal refundPrice;

    @ApiModelProperty("申请类型")
    private AfterSaleTypeEnum applyType;

    @ApiModelProperty("退货方式")
    private AfterSaleReturnEnum returnType;

    @ApiModelProperty("退货原因")
    private String applyReason;

    @ApiModelProperty("货物状态")
    private AfterSaleGoodsEnum goodsStatus;

    /** 图片列表 */
    @ApiModelProperty("凭证列表")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> imgUrls;

    /** 详细描述 */
    @ApiModelProperty("详细描述")
    private String description;

    @ApiModelProperty("商家处理结果")
    private String reply;

    private Integer addressId;

    /** 收件信息 */
    @ApiModelProperty("收件信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderAddressInfoDto addressInfo;

    /** 取件信息 */
    @ApiModelProperty("取件信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderAddressInfoDto pickupInfo;

    /** 快递信息 */
    @ApiModelProperty("快递信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private OrderDeliveryDto deliveryInfo;

    /** 状态 */
    @ApiModelProperty("状态")
    private AfterSaleStatusEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("订单商品详情")
    @TableField(exist = false)
    @OneToMany(from = "orderId", to = "details", clazz = StoreOrderDetailService.class, method = "findAllByOrderId")
    private List<StoreOrderDetailEntity> details;
}
