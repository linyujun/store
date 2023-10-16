package com.kinzhan.dev.platform.beans.dto.order;

import com.kinzhan.dev.platform.beans.dto.mall.order.OrderAddressInfoDto;
import com.kinzhan.dev.platform.beans.enums.ShippingTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "购物车支付", description = "购物车支付实体")
public class OrderCartDto {
    /**
     * 购物车id
     */
    @NotNull(message = "购物车必填")
    @ApiModelProperty(value = "购物车ids", required = true)
    private List<Integer> cartIds;

    /**
     * 优惠券id
     */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    @ApiModelProperty("用户备注")
    private String userRemarks;

    @NotNull(message = "收货信息必填")
    @ApiModelProperty("收货信息")
    private OrderAddressInfoDto addressInfo;

    @ApiModelProperty("配送方式")
    private ShippingTypeEnum shippingType;
}
