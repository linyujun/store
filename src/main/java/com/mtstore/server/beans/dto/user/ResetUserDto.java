package com.mtstore.server.beans.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResetUserDto {
    @NotNull(message = "用户ID必填")
    Integer id;
}
