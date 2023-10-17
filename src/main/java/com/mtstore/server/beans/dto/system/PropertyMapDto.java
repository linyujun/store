package com.mtstore.server.beans.dto.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class PropertyMapDto {

    @NotBlank(message = "分组必填")
    private String group;

    @NotNull(message = "属性详情必填")
    private Map<String, Object> property;
}
