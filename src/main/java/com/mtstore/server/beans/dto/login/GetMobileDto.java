package com.mtstore.server.beans.dto.login;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dingxiaosong
 * 用户直接通过手机和验证码登录
 */
@Data
public class GetMobileDto {

    @NotBlank(message = "accessToken不能为空")
    private String accessToken;
}
