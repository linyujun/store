package com.kinzhan.dev.platform.beans.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "佣金提取")
public class UserBrokerageCollectDto {

    @ApiModelProperty(value = "提取金额")
    @NotNull(message = "提取金额必填")
    @DecimalMin(value = "0")
    private BigDecimal total;
}
