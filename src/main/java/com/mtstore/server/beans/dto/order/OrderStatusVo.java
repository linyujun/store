package com.mtstore.server.beans.dto.order;

import lombok.Data;

@Data
public class OrderStatusVo {
    private Integer status;
    private String statusDesc;
    private Long total;
}
