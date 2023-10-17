package com.mtstore.server.beans.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTotalHourVo {
    private String hour;
    private Long total;
    private BigDecimal totalPrice;
}
