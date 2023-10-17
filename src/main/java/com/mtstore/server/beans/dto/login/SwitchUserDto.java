package com.mtstore.server.beans.dto.login;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SwitchUserDto {
    @NotNull(message = "机构id必填")
    private Integer tenantId;

    @NotNull(message = "门店id必填")
    private Integer storeId;
}
