package com.mtstore.server.beans.dto.order;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderQueueDto extends BaseDto {

    private String orderId;

    private LocalDateTime createTime = LocalDateTime.now();
}
