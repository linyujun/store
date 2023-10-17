package com.mtstore.server.beans.dto.mall.recharge;

import lombok.Data;

import java.math.BigDecimal;

import com.mtstore.server.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-26
*/
@Data
public class RechargeDto extends BaseDto{
    /** 显示名称 */
    @ApiModelProperty("显示名称")
    private String title;

    /** 充值金额 */
    @ApiModelProperty("充值金额")
    @NotNull
    private BigDecimal price;

    /** 额外赠送 */
    @ApiModelProperty("额外赠送")
    private BigDecimal extraPrice;

    /** 概述 */
    @ApiModelProperty("概述")
    private String description;

    /** 排序方式(数字越小越靠前) */
    @ApiModelProperty("排序方式(数字越小越靠前)")
    private Integer sort;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;
}
