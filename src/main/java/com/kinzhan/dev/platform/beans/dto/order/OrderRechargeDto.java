package com.kinzhan.dev.platform.beans.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "充值订单实体", description = "充值订单实体")
public class OrderRechargeDto {
    /**
     * 下单金额
     */
    @NotNull(message = "下单金额必填")
    private BigDecimal total;

    @ApiModelProperty("选取的充值方案id")
    private Integer rechargeId;
}
