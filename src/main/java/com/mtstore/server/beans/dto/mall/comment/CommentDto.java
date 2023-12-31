package com.mtstore.server.beans.dto.mall.comment;

import lombok.Data;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-17
*/
@Data
public class CommentDto extends BaseDto{
    /** 订单ID */
    @ApiModelProperty("订单ID")
    private String orderId;

    /** 产品id */
    @ApiModelProperty("产品id")
    private Integer productId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 属性名称 */
    @ApiModelProperty("属性名称")
    private String attrKey;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Integer uid;

    private Integer status = 0;

    private String statusDesc = "未评价";
}
