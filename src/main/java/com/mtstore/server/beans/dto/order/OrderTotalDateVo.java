package com.mtstore.server.beans.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTotalDateVo {
    private String date;
    private Long total;
    private BigDecimal totalPrice;
}
