package com.mtstore.server.beans.dto.mall.aftersales;

import com.mtstore.server.beans.dto.mall.order.OrderAddressInfoDto;
import com.mtstore.server.beans.dto.mall.order.OrderDeliveryDto;
import com.mtstore.server.beans.enums.AfterSaleReturnEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-06-12
*/
@Data
public class StoreReturnDto {
    /** 售后记录id */
    @ApiModelProperty(value = "售后记录id", required = true)
    @NotNull(message = "售后记录id必填")
    private Integer id;

    /** 状态 */
    @ApiModelProperty(value = "退货方式 1：上门取件 2：自行寄回", required = true)
    private AfterSaleReturnEnum returnType;

    @ApiModelProperty(value = "上门取件信息，选择上门取件必填")
    private OrderAddressInfoDto pickupInfo;

    @ApiModelProperty(value = "自行寄回， 选择自行寄回必填")
    private OrderDeliveryDto deliveryInfo;
}
