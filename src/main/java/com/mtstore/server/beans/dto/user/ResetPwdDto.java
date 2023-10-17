package com.mtstore.server.beans.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPwdDto {
    String password;

    @NotBlank(message = "新密码不能为空")
    String newPwd;

    @NotBlank(message = "确认密码不能为空")
    String confirmPwd;
}
