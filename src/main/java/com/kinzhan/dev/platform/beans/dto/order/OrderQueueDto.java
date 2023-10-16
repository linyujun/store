package com.kinzhan.dev.platform.beans.dto.order;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderQueueDto extends BaseDto {

    private String orderId;

    private LocalDateTime createTime = LocalDateTime.now();
}
