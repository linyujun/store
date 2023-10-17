package com.mtstore.server.beans.dto.template;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TemplateDto extends BaseDto {

    @NotBlank(message = "模板名称必填")
    private String title;

    @NotBlank(message = "点评内容必填")
    private String content;

    @ApiModelProperty("点评星级")
    private Integer level;

    @ApiModelProperty("附件")
    private String[] attachment;
}
