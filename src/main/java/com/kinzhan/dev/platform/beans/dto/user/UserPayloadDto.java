package com.kinzhan.dev.platform.beans.dto.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dingxiaosong
 * 用户登陆成功的JWT负载信息
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPayloadDto {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名，商户、代理商，可能跟手机号一致
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户微信OpenId
     */
    private String openId;

    /**
     * 用户真实姓名
     */
    private String phone;

    /**
     * 角色，
     * ADMIN-管理员
     * COMPANY-招聘方
     * PERSONAL-个人
     */
    private String role;

    /**
     * 首次登录
     */
    private Boolean isFirstLogin;

    /**
     * 多租户字段
     */
    private Integer tenantId;

    /**
     * 平台
     */
    private String platform;
}
