package com.kinzhan.dev.platform.beans.dto.mall.aftersales;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import com.kinzhan.dev.platform.beans.enums.AfterSaleGoodsEnum;
import com.kinzhan.dev.platform.beans.enums.AfterSaleStatusEnum;
import com.kinzhan.dev.platform.beans.enums.AfterSaleTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* @date 2023-06-12
*/
@Data
public class StoreAfterSalesAuditDto {
    @NotNull(message = "售后记录id必填")
    private Integer id;

    private BigDecimal refundPrice;

    @NotNull(message = "审核结果必填")
    private AfterSaleStatusEnum status;

    private Integer addressId;

    private String reply;
}
