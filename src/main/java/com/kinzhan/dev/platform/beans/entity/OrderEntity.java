package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kinzhan.dev.platform.beans.enums.PayBizEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("kz_order")
@ApiModel(value = "OrderEntity对象", description = "")
public class OrderEntity extends BaseEntity {

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("微信用户id")
    private String openId;

    @ApiModelProperty("随机订单号")
    private String orderId;

    @ApiModelProperty("微信订单id")
    private String transactionId;

    @ApiModelProperty("预订单id")
    private String prepayId;

    @ApiModelProperty("退款订单id")
    private String refundId;

    @ApiModelProperty("数量")
    private Integer amount;

    @ApiModelProperty("总价")
    private BigDecimal total;

    @ApiModelProperty("业务类型")
    private PayBizEnum bizType;

    @ApiModelProperty("订单状态 0 未支付，1 已支付，2 已取消 5 已退款")
    private Integer status = 0;

    @ApiModelProperty("订单描述信息")
    private String statusDesc = "未支付";

    @ApiModelProperty("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty("订单报文")
    private String extra;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER, select = false, value = "sum(total)")
    private BigDecimal totalPrice;
}
