package com.mtstore.server.beans.dto.user;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author songsir
 * @date 2021/6/9
 **/
@Data
@ApiModel(value = "用户信息")
public class UserInfoDto extends BaseDto {
    private String phone;
    private String avatarUrl;
    private String gender;
    private String[] address;
    private String addressDetail;
    @NotBlank(message = "用户名必填")
    String nickName;
}

