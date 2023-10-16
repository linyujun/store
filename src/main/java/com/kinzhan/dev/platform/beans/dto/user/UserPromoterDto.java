package com.kinzhan.dev.platform.beans.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class UserPromoterDto {

    private Integer userId;

    private BigDecimal feeRate;
}
