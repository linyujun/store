package com.kinzhan.dev.platform.beans.dto.withdraw;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WithdrawConfirmDto{
    @NotNull(message = "提现记录必填")
    private Integer id;

    private String orderId;

    @NotBlank(message = "打款截图必填")
    private String orderImgUrl;
}
