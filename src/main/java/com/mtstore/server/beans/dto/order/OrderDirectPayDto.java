package com.mtstore.server.beans.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "当面付订单实体", description = "当面付订单实体")
public class OrderDirectPayDto {
    /**
     * 下单金额
     */
    @NotNull(message = "下单金额必填")
    private BigDecimal total;
}
