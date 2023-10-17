package com.mtstore.server.beans.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyChildDto {
    private Integer id;

    private Integer parentId;

    private String groups;

    private String type;

    @NotBlank(message = "label必填")
    private String label;

    @NotBlank(message = "属性名称必填")
    private String k;

    private Integer sort;
}
