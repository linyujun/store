package com.mtstore.server.beans.dto.mall.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("查询订单实体")
public class OrderSimpleDto {

    @NotNull(message = "订单id必填")
    @ApiModelProperty(value = "订单id必填")
    private String orderId;
}
