package com.kinzhan.dev.platform.beans.dto.mall.order;

import lombok.Data;
import java.time.*;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-20
*/
@Data
public class OrderStatusDto extends BaseDto{
    /** 订单id */
    @ApiModelProperty("订单id")
    private String orderId;

    /** 订单状态 */
    @ApiModelProperty("订单状态")
    private Integer status;

    /** 订单状态描述 */
    @ApiModelProperty("订单状态描述")
    private String statusDesc;

}
