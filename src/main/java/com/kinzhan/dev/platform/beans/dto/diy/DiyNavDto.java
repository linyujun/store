package com.kinzhan.dev.platform.beans.dto.diy;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiyNavDto extends BaseDto {

    @ApiModelProperty("图标地址")
    @NotBlank(message = "图标地址必填")
    private String iconPath;

    @ApiModelProperty("选中图标地址")
    @NotBlank(message = "选中图标地址必填")
    private String selectedIconPath;

    @ApiModelProperty("导航标题")
    @NotBlank(message = "导航标题必填")
    private String title;

    @ApiModelProperty("备注信息")
    private String description;

    @ApiModelProperty("跳转链接")
    @NotBlank(message = "跳转链接必填")
    private String pagePath;

}
