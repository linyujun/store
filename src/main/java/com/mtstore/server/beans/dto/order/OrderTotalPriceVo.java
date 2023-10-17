package com.mtstore.server.beans.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderTotalPriceVo {
    private LocalDate date;
    private BigDecimal productPrice;
    private BigDecimal rechargePrice;
    private BigDecimal totalPrice;
}
