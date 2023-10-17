package com.mtstore.server.beans.dto.mall.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "发货实体")
public class OrderDeliveryDto {
    @ApiModelProperty("订单id")
    @NotBlank(message = "订单id必填")
    private String orderId;

    @ApiModelProperty("快递公司")
    @NotBlank(message = "快递公司必填")
    private String deliveryCompany;

    @ApiModelProperty("快递公司识别码")
    @NotBlank(message = "快递公司识别码必填")
    private String deliveryId;

    @ApiModelProperty("快递单号")
    @NotBlank(message = "快递单号必填")
    private String deliveryNo;
}
