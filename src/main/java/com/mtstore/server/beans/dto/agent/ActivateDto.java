package com.mtstore.server.beans.dto.agent;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ActivateDto {
    @NotNull(message = "开课码必填")
    private Integer code;
}
