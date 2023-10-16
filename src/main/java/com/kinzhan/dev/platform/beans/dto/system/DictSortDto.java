package com.kinzhan.dev.platform.beans.dto.system;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author songsir
 * @date 2021/7/8
 **/
@Data
public class DictSortDto {
    @NotNull(message = "id必选")
    private Integer id;

    @NotBlank(message = "操作必选")
    private String action;
}
