package com.kinzhan.dev.platform.beans.dto.login;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author dingxiaosong
 * 用户直接通过手机和验证码登录
 */
@Data
public class MsgDto {

    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号码长度有误")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号填写有误")
    private String phone;
//
//    @NotBlank(message = "验证码必填")
//    private String captchaCode;
//
//    @NotBlank(message = "csrfToken必填")
//    private String csrfToken;
}
