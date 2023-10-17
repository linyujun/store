package com.mtstore.server.beans.dto.withdraw;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WithdrawCancelDto extends BaseDto {
    @NotNull(message = "提现记录必填")
    private Integer id;
}
