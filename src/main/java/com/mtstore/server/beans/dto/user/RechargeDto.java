package com.mtstore.server.beans.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class RechargeDto  {
    private Integer uid;

    private BigDecimal amount;

    private String direction = "+";

    private String billSource = "充值";

    private String billDesc = "充值";
}
