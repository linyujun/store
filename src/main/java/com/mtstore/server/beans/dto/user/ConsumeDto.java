package com.mtstore.server.beans.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ConsumeDto {
    private Integer uid = 0;

    private BigDecimal amount;

    private String direction = "-";

    private String billSource = "扣减";

    private String billDesc = "扣减";
}
