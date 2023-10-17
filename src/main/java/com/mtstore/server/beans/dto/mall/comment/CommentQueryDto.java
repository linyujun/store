package com.mtstore.server.beans.dto.mall.comment;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentQueryDto {
    @ApiModelProperty("订单id")
    @Query
    private String orderId;

    @ApiModelProperty("商品id")
    @Query
    private Integer productId;
}
