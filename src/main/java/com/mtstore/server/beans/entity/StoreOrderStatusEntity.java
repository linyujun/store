package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;

/**
* @author songsir
* @date 2023-04-20
*/
@Data
@TableName(value = "kz_store_order_status", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城订单状态记录对象", description = "商城订单状态记录表")
public class StoreOrderStatusEntity{
    @TableId(type = IdType.AUTO)
    public Integer id;

    /** 订单id */
    @ApiModelProperty("订单id")
    private String orderId;

    /** 订单状态 */
    @ApiModelProperty("订单状态")
    private Integer status;

    /** 订单状态描述 */
    @ApiModelProperty("订单状态描述")
    private String statusDesc;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime createTime;

    public Integer tenantId;
}
