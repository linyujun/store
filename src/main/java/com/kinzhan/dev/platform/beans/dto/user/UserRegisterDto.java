package com.kinzhan.dev.platform.beans.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String role;

    private String realName;

    private String username;

    private String nickName;
    /**
     * 手机号
     */
    private String phone;

    private String gender;

    private Integer inviteId = 0;
    /**
     * 微信openId
     */
    private String openId;

    private String password;

    private Integer tenantId;

    private String avatarUrl;

    private String dataScope;
}
