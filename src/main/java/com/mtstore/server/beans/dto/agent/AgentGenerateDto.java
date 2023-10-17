package com.mtstore.server.beans.dto.agent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AgentGenerateDto {
    @NotNull(message = "代理商必填")
    private Integer agentId;

    @NotNull(message = "计划必填")
    private Integer planId;

    @NotNull(message = "数量必填")
    @Min(value = 1)
    @Max(value = 1000)
    private Integer num;

    private Boolean notExpired;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredTime;
}
