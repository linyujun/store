package com.mtstore.server.beans.dto.user;


import com.mtstore.server.beans.dto.login.WxUserInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author dingxiaosong
 * 用户登陆提交的负载信息
 */
@Data
public class UserWxLoginDto {
    @NotNull(message = "code必填")
    private String code;

    private String encryptedData;

    private String iv;

    private String role;

    //邀请用户id
    private Integer inviteId;

    @NotNull(message = "userInfo必填")
    private WxUserInfo userInfo;
}
