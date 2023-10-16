package com.kinzhan.dev.platform.beans.dto.mall.order;

import com.kinzhan.dev.platform.beans.annotion.Query;
import com.kinzhan.dev.platform.beans.enums.OrderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


/**
* @author songsir
* @date 2023-04-20
*/
@Data
@ApiModel(value = "订单查询实体")
public class OrderQueryDto{

    @ApiModelProperty("订单id")
    @Query
    private String orderId;

    @ApiModelProperty("商品信息")
    @Query(type = Query.Type.INNER_LIKE)
    private String productInfo;

    /** 状态 */
    @ApiModelProperty("状态")
    @Query
    private Integer status;

    @ApiModelProperty("开始时间")
    @Query(propName = "createTime", type = Query.Type.GREATER_THAN)
    private String startTime;

    @Query(propName = "createTime", type = Query.Type.LESS_THAN_NQ)
    @ApiModelProperty("结束时间")
    private String endTime;
}
