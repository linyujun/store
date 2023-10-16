package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("kz_order_queue")
@ApiModel(value = "订单队列对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderQueueEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private Boolean isUpdate = false;

    private Boolean isSync = false;
}
