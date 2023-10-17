package com.mtstore.server.beans.dto.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ww
 * @date 2021/7/29
 **/
@Data
@ApiModel(description= "微信登录请求Payload")
public class WxLoginDto {

    @ApiModelProperty(value = "code必填",required=true)
    @NotBlank(message = "code必填")
    private String code;

    @ApiModelProperty(value = "userInfo必填",required=true)
    @NotNull(message = "userInfo必填")
    private WxUserInfo userInfo;
}
