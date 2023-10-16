package com.kinzhan.dev.platform.beans.dto.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RePayOrderDto {
    @NotNull(message = "原始订单id必填")
    private String orderId;
}
