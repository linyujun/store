package com.mtstore.server.beans.dto.account;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountDto extends BaseDto {

    @NotBlank(message = "账户必填")
    @ApiModelProperty("账户名称，如支付宝账户，xxz@126.com")
    private String account;

    @NotBlank(message = "账户类型，如微信，支付宝，银行卡")
    @ApiModelProperty("账户类型, 如微信，支付宝，银行卡")
    private String accountType;

    @NotBlank(message = "收款人姓名必填")
    @ApiModelProperty("收款人真实姓名")
    private String trueName;

    @ApiModelProperty("银行号")
    private String bankCode;

    @ApiModelProperty("账户二维码")
    private String accountUrl;
}
