package com.kinzhan.dev.platform.beans.dto.withdraw;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(value = "发起提现")
public class WithdrawDto extends BaseDto {
    @ApiModelProperty(value = "提现到的账户", required = true)
    @NotBlank(message = "收款账户二维码传")
    private String qrcodeUrl;

    @ApiModelProperty(value = "提现金额", required = true)
    @NotNull(message = "提现金额必填")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "备注信息")
    private String description;
}
