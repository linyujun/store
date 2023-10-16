package com.kinzhan.dev.platform.beans.dto.user;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author dingxiaosong
 * 用户直接通过手机和验证码登录
 */
@Data
public class MockLoginDto {
    @NotNull(message = "模拟登陆的用户ID不为空")
    private Integer userId;
}
