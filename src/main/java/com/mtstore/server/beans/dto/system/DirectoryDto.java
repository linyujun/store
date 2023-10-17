package com.mtstore.server.beans.dto.system;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DirectoryDto extends BaseDto {

    @ApiModelProperty("目录名称")
    @NotBlank(message = "目录名称必填")
    private String title;

    @ApiModelProperty("备注信息")
    private String description;

    private String target;

    private Integer sort;
}
