package com.kinzhan.dev.platform.beans.dto.agent;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AgentBindDto {
    @NotNull(message = "机构id必填")
    private Integer agentId;
}
