package com.kinzhan.dev.platform.beans.dto.order;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "开通VIP订单实体")
public class OrderVipDto {

    @NotNull(message = "会员id类型")
    private Integer levelId;
}
