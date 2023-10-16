package com.kinzhan.dev.platform.beans.dto.login;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author dingxiaosong
 * 后台登陆
 */
@Data
@ApiModel(description= "后台用户登录请求Payload")
public class AdminLoginDto {
    private String phone;

    private String username;

    private String password;

    private String captchaCode;

    private String csrfToken;
}
