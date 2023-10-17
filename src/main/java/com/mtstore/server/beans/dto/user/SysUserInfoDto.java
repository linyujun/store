package com.mtstore.server.beans.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Data
public class SysUserInfoDto {
    Integer id;
    @NotBlank(message = "手机号必填")
    @Length(min = 11, max = 11, message = "手机号码长度有误")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号填写有误")
    String phone;
    String avatarUrl;
    String password;
    String gender;
    @NotBlank(message = "用户名必填")
    String userName;
    String nickName;
    Integer roleId;
    String email;
    List<Integer> storeIds;
    String dataScope;
    Boolean enabled = true;
}

