package com.mtstore.server.beans.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Data
public class UserInnerRegisterDto {
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号码长度有误")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号填写有误")
    String phone;
}

