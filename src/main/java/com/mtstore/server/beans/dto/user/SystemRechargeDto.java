package com.mtstore.server.beans.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SystemRechargeDto {
    @NotNull(message = "用户id必填")
    private Integer uid;

    @NotNull(message = "金额必填")
    private BigDecimal amount;

    @NotBlank(message = "备注")
    private String description;

    @NotBlank(message = "充值类型必填")
    private String bizType;

    @NotBlank(message = "操作类型必填，add,reduce,total")
    private String action;
}
