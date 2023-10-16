package com.kinzhan.dev.platform.beans.dto.mall.bargain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "创建砍价活动")
public class BargainCreateDto {

    @ApiModelProperty(value = "商品id", required =  true)
    @NotNull(message = "砍价商品id必填")
    private Integer productId;

    @ApiModelProperty(value = "sku_id", required =  true)
    @NotNull(message = "砍价商品sku_id必填")
    private Integer productDetailId;

    @ApiModelProperty(value = "砍价活动id", required =  true)
    @NotNull(message = "砍价活动id必填")
    private Integer bargainId;
}
