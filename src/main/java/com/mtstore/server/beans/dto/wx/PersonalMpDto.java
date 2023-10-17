package com.mtstore.server.beans.dto.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "创建个人小程序")
public class PersonalMpDto {

    @ApiModelProperty(value = "个人用户名字", required = true)
    private String idname;

    @ApiModelProperty(value = "用户微信号", required = true)
    private String wxuser;

    @ApiModelProperty(value = "第三方联系电话", required = true)
    private String componentPhone;
}
