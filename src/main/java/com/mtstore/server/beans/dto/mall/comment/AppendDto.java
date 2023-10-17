package com.mtstore.server.beans.dto.mall.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-05-17
*/
@Data
@ApiModel(value = "用户追评")
public class AppendDto {

    @ApiModelProperty("评价id")
    @NotNull(message = "评价id必填")
    private Integer id;

    @ApiModelProperty("追评")
    @NotBlank(message = "追评内容必填")
    private String append;
}
