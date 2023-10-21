package com.mtstore.server.controller.mobile;

/**
 * @author ww
 * @date 2021/6/7
 **/

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.login.WxLoginDto;
import com.mtstore.server.beans.dto.user.UserPayloadDto;
import com.mtstore.server.beans.dto.user.UserWxLoginDto;
import com.mtstore.server.beans.vo.JwtTokenVo;
import com.mtstore.server.schedule.event.user.UserInvitedEvent;
import com.mtstore.server.schedule.event.user.UserRegisterEvent;
import com.mtstore.server.service.LoginService;
import com.mtstore.server.util.helper.JwtHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序用户接口
 */
@Api(tags="移动端-微信登录")
@RestController
@RequestMapping("/login/wx")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class WxLoginController {

    final ApplicationEventPublisher publisher;

    final LoginService loginService;

    final JwtHelper jwtHelper;

    final WxMaService wxService;

    /**
     * 登陆接口
     */
    @ApiOperation(value = "通过code登录接口", response = R.class)
    @PostMapping("")
    public Object login(@RequestBody @Validated WxLoginDto dto) {
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(dto.getCode());
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            UserPayloadDto userPayloadDto = loginService.loginWx(null, session.getOpenid(), dto.getUserInfo());
            String token = jwtHelper.createJWT(userPayloadDto);
            log.info("微信登录 {}", userPayloadDto);
            return R.ok("登陆成功", JwtTokenVo.builder().accessToken(token).build());
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return R.err(e.getMessage());
        }
    }


    /**
     * 手机授权手机号进行登录
     */
    @ApiOperation("手机授权手机号进行登录")
    @PostMapping("/phone")
    public Object phone(@RequestBody UserWxLoginDto loginDto) {
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(loginDto.getCode());
            WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(session.getSessionKey(),
                    loginDto.getEncryptedData(), loginDto.getIv());
            UserPayloadDto userPayloadDto = loginService.loginWx(phoneNoInfo.getPhoneNumber(), session.getOpenid(), loginDto.getUserInfo());
            if (userPayloadDto.getIsFirstLogin()) {
                publisher.publishEvent(new UserRegisterEvent(this, userPayloadDto.getId()));
                if (null != loginDto.getInviteId()) {
                    publisher.publishEvent(new UserInvitedEvent(this, loginDto.getInviteId()));
                }
            }
            String token = jwtHelper.createJWT(userPayloadDto);
            log.info("微信授权手机号登录 {}", loginDto);
            return R.ok("登陆成功", JwtTokenVo.builder()
                    .accessToken(token)
                    .userInfo(userPayloadDto)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return R.err(e.getMessage());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @ApiOperation("获取用户信息接口")
    @GetMapping("/info")
    public Object info(String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        try {
            // 用户信息校验
            if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                throw new Exception("user check failed");
            }
            // 解密用户信息
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            log.info("获取用户信息接口 {}", userInfo);
            return R.ok("获取成功" ,userInfo);
        } catch (Exception e) {
            e.printStackTrace();

            return R.err(e.getMessage());
        }
    }

}
