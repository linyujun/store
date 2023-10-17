package com.mtstore.server.beans.dto.withdraw;

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
