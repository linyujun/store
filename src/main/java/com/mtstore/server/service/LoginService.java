package com.mtstore.server.service;


import cn.hutool.core.bean.BeanUtil;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.login.WxUserInfo;
import com.mtstore.server.beans.dto.user.UserPayloadDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.beans.entity.sys.SysUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ww
 * 登录
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    final SysUserService sysUserService;
    final UserService userService;

    /**
     * 模拟登录
     * @param userId
     * @return
     */
    public UserPayloadDto mockLogin(Integer userId) {
        UserEntity userEntity = userService.getById(userId);
        UserPayloadDto userPayloadDto = UserPayloadDto.builder()
                .userId(userEntity.getId())
                .nickName(userEntity.getNickName())
                .phone(userEntity.getPhone())
                .openId(userEntity.getOpenId())
                .tenantId(userEntity.getTenantId())
                .role("GUEST").build();
        loginCallback(userPayloadDto);

        return userPayloadDto;
    }

    /**
     *
     * TODO 用户登录回调
     * @param payload
     */
    public void loginCallback(UserPayloadDto payload) {
        log.info("login {}", payload);
    }

    /**
     * 手机端应用登录，关联角色
     * @param phone
     * @param roleName
     * @return
     */
    public UserPayloadDto loginByPhone(String phone, String roleName) {
        UserEntity userEntity = userService.loginByPhone(phone);
        if (null == userEntity) {
            return null;
        }
        if (!userEntity.getEnabled()) {
            throw new RuntimeException("用户被禁用，不可登录！");
        }
        UserPayloadDto userPayloadDto = BeanUtil.copyProperties(userEntity, UserPayloadDto.class);
        userPayloadDto.setRole(roleName);
        userPayloadDto.setUserId(userEntity.getId());
        userPayloadDto.setPlatform("APP");
        loginCallback(userPayloadDto);

        return userPayloadDto;
    }

    /**
     * 小程序微信登陆
     * @param phone
     * @param openId
     * @param wxUserInfo
     * @return
     */
    public UserPayloadDto loginWx(String phone, String openId, WxUserInfo wxUserInfo) {
        UserEntity userEntity = userService.loginByWx(phone, openId, wxUserInfo);
        if (false == userEntity.getEnabled()) {
            throw new RuntimeException("用户被禁用，不可登录！");
        }
        UserPayloadDto userPayloadDto = BeanUtil.copyProperties(userEntity, UserPayloadDto.class);
        userPayloadDto.setRole("GUEST");
        userPayloadDto.setUserId(userEntity.getId());//deprecated
        userPayloadDto.setPlatform("WEIXIN");
        loginCallback(userPayloadDto);

        return userPayloadDto;
    }

    /**
     * 管理后台用户登陆
     * @param userName
     * @param password
     * @return
     */
    public UserPayloadDto loginAdmin(String userName, String password) {
        SysUserEntity sysUserEntity = sysUserService.findUserByAny(userName);
        if (null == sysUserEntity) {
            throw new RuntimeException("账号未注册，请联系机构");
        }
        if (null != password && !sysUserService.checkPwd(password, sysUserEntity.getPassword())) {
            throw new RuntimeException("密码不正确，请重试！");
        }
        if (!sysUserEntity.getEnabled()) {
            throw new RuntimeException("用户被禁用，不可登录！");
        }
        UserPayloadDto userPayloadDto = BeanUtil.copyProperties(sysUserEntity, UserPayloadDto.class);
        userPayloadDto.setRole(sysUserEntity.getRole().getRoleName());
        userPayloadDto.setUserId(sysUserEntity.getId());
        userPayloadDto.setPlatform("PLATFORM");
        loginCallback(userPayloadDto);

        return userPayloadDto;
    }

    /**
     * @return
     */
    @SneakyThrows
    public UserPayloadDto switchUser() {
        UserPayloadDto payload = LoggedUser.get();
        Optional.ofNullable(payload).orElseThrow(() -> {
            throw new RuntimeException("您还未登录");
        });

        return payload;
    }

}
