package com.mtstore.server.beans.dto.mall.product;

import com.mtstore.server.beans.enums.CartScopeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.mtstore.server.beans.dto.BaseDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-04-19
*/
@Data
@ApiModel(value = "购物车")
public class CartDto extends BaseDto{

    @ApiModelProperty(value = "商品id")
    @NotNull(message = "商品id必填")
    private Integer productId;

    @NotNull(message = "商品sku_id必填")
    @ApiModelProperty(value = "sku_id")
    private Integer productDetailId;

    @NotNull(message = "购物车场景必填")
    @ApiModelProperty(value = "购物车场景，如普通商品：1，秒杀: 2，拼团:3，砍价:4，积分:5")
    private CartScopeEnum scope = CartScopeEnum.GOODS;

    @Min(value = 0, message = "数量必须大于0")
    @ApiModelProperty(value = "数量")
    private Integer cartNum;

    @ApiModelProperty(value = "是否隐藏?")
    private Boolean isHidden = false;

    @ApiModelProperty(value = "拼团活动id")
    private Integer combinationId;

    @ApiModelProperty(value = "加团的id")
    private Integer combinationLogId;

    @ApiModelProperty(value = "砍价活动id")
    private Integer bargainId;

    @ApiModelProperty(value = "帮砍活动id")
    private Integer bargainLogId;
}
