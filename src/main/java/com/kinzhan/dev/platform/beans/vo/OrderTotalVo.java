package com.kinzhan.dev.platform.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel(value = "获取订单支付信息")
public class OrderTotalVo {

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "总积分")
    private BigDecimal totalCredit;

    @ApiModelProperty(value = "邮费")
    private BigDecimal postagePrice;
}
