package com.mtstore.server.beans.dto.mall.product;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductTotalDateVo {
    private LocalDate date;
    private Long visits;
    private Long sales;
    private Long favors;
}
