package com.kinzhan.dev.platform.beans.dto.withdraw;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WithdrawRejectDto {
    @NotNull(message = "提现记录必填")
    private Integer id;

    @NotBlank(message = "驳回原因必填")
    private String statusReason;
}
