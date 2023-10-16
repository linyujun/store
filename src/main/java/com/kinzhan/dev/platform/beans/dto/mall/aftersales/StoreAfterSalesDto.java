package com.kinzhan.dev.platform.beans.dto.mall.aftersales;

import com.kinzhan.dev.platform.beans.dto.mall.order.OrderAddressInfoDto;
import com.kinzhan.dev.platform.beans.dto.mall.order.OrderDeliveryDto;
import com.kinzhan.dev.platform.beans.enums.AfterSaleGoodsEnum;
import com.kinzhan.dev.platform.beans.enums.AfterSaleTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-06-12
*/
@Data
public class StoreAfterSalesDto extends BaseDto{
    /** 订单id */
    @ApiModelProperty(value = "订单id", required = true)
    @NotBlank(message = "原始订单id必填")
    private String orderId;

    /** 状态 */
    @ApiModelProperty(value = "申请类型 1：仅退款，2：退货退款 3:维修 4:换货", required = true)
    @NotNull(message = "申请类型必填")
    private AfterSaleTypeEnum applyType;

    @ApiModelProperty("退货原因")
    @NotNull(message = "退货原因必填")
    private String applyReason;

    @ApiModelProperty("货物状态")
    @NotNull(message = "货物状态必填")
    private AfterSaleGoodsEnum goodsStatus;

    @ApiModelProperty("申请图片")
    private List<String> imgUrls;

    @ApiModelProperty("备注信息")
    private String description;
}
