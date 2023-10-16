package com.kinzhan.dev.platform.beans.dto.withdraw;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WithdrawCancelDto extends BaseDto {
    @NotNull(message = "提现记录必填")
    private Integer id;
}
