package com.mtstore.server.beans.dto.mall.aftersales;

import com.mtstore.server.beans.enums.AfterSaleStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
