package com.mtstore.server.beans.dto.mall.order;

import com.mtstore.server.beans.enums.CartScopeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单活动信息")
public class OrderActivityDto {

    @ApiModelProperty(value = "场景")
    private CartScopeEnum scope;

    @ApiModelProperty(value = "拼团活动id")
    private Integer combinationId;

    @ApiModelProperty(value = "加团的id")
    private Integer combinationLogId;

    @ApiModelProperty(value = "砍价活动id")
    private Integer bargainId;

    @ApiModelProperty(value = "帮砍活动id")
    private Integer bargainLogId;
}
