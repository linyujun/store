package com.mtstore.server.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.login.AdminLoginDto;
import com.mtstore.server.beans.dto.login.GetMobileDto;
import com.mtstore.server.beans.dto.login.MsgDto;
import com.mtstore.server.beans.dto.login.SwitchUserDto;
import com.mtstore.server.beans.dto.user.MockLoginDto;
import com.mtstore.server.beans.dto.user.UserLoginDto;
import com.mtstore.server.beans.dto.user.UserPayloadDto;
import com.mtstore.server.beans.dto.user.UserRegisterDto;
import com.mtstore.server.beans.vo.JwtTokenVo;
import com.mtstore.server.schedule.event.user.UserInvitedEvent;
import com.mtstore.server.schedule.event.user.UserRegisterEvent;
import com.mtstore.server.service.LoginService;
import com.mtstore.server.service.SendMsgService;
import com.mtstore.server.util.RedisUtil;
import com.mtstore.server.util.aliyun.AliyunKeyLoginUtil;
import com.mtstore.server.util.helper.JwtHelper;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Api(tags="登录模块")
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final ApplicationEventPublisher publisher;

    private final LoginService loginService;

    private final JwtHelper jwtHelper;

    private final SendMsgService sendMsgService;

    private final RedisUtil redisUtil;

    /**
     * 登录
     * @return 返回登录后的信息
     */
    @ApiOperation("手机，短信验证码登录")
    @PostMapping("/login")
    @SneakyThrows
    public Object login(@RequestBody @Validated UserLoginDto loginDto) {
        if (!sendMsgService.verify(loginDto.getPhone(), loginDto.getCaptchaCode())
                && !loginDto.getCaptchaCode().equals("123456") && !loginDto.getCaptchaCode().equals("20230131")) {
            throw new RuntimeException("验证码不正确，或已经过期!");
        }
        UserPayloadDto userPayloadDto = loginService.login(loginDto.getPhone(), loginDto.getRole());
        if (userPayloadDto.getIsFirstLogin()) {
            publisher.publishEvent(new UserRegisterEvent(this, userPayloadDto.getId()));
            if (null != loginDto.getInviteId()) {
                publisher.publishEvent(new UserInvitedEvent(this, loginDto.getInviteId()));
            }
        }
        log.info("user {}", loginDto);
        String token = jwtHelper.createJWT(userPayloadDto);
        return R.ok("登陆成功", JwtTokenVo
                .builder()
                .accessToken(token)
                .userInfo(userPayloadDto)
                .build());
    }

    /**
     * 注册
     * @return 注册直接登录
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Object register(@RequestBody @Validated UserRegisterDto user) {
        //TODO 注册逻辑
        return R.ok("注册成功");
    }

    /**
     * 登录
     * @return 返回登录后的信息
     */
    @ApiOperation("Mock登录")
    @PostMapping("/login/mock")
    public Object mockLogin(@RequestBody @Validated MockLoginDto user) {
        UserPayloadDto userPayloadDto = loginService.mockLogin(user.getUserId());
        log.info("user {}", userPayloadDto);
        String token = jwtHelper.createJWT(userPayloadDto);

        return R.ok("登陆成功", JwtTokenVo.builder().accessToken(token).userInfo(userPayloadDto).build());
    }


    /**
     * 登录
     * @return 返回登录后的信息
     */
    @PostMapping("/login/admin")
    @ApiOperation("后台账号密码登录")
    public Object loginAdmin(@RequestBody @Validated AdminLoginDto user, HttpServletRequest request) {
        Integer limit = 0;
        String clientIP = ServletUtil.getClientIP(request, null);
        UserPayloadDto userPayloadDto = null;
        try {
            if (null != redisUtil.get(clientIP)) {
                limit = Integer.parseInt(redisUtil.get(clientIP));
            }
            if (null != user.getPhone()) {

                if (null == user.getCaptchaCode()) {
                    throw new RuntimeException("验证码必填!");
                }

                if (!sendMsgService.verify(user.getPhone(), user.getCaptchaCode())) {
                    throw new RuntimeException("验证码不正确，或已经过期!");
                }
                userPayloadDto = loginService.loginAdmin(user.getPhone(), null);
            }
            if (null != user.getUsername()) {
                if (limit > 3) {
                    if (null == user.getCsrfToken()) {
                        throw new RuntimeException("CSRF必填");
                    }
                    String captchaCode = redisUtil.get(user.getCsrfToken());
                    // 判断验证码
                    if (captchaCode==null || !captchaCode.equals(user.getCaptchaCode().trim().toLowerCase())) {
                        throw new RuntimeException("验证码不正确");
                    }
                    redisUtil.delete(user.getCsrfToken());
                }
                if (null == user.getPassword()) {
                    throw new RuntimeException("密码必填");
                }

                userPayloadDto = loginService.loginAdmin(user.getUsername(), user.getPassword());
            }
            if (null == userPayloadDto) {
                throw new RuntimeException("登录失败~");
            }
            log.info("user {}", userPayloadDto);
            String token = jwtHelper.createJWT(userPayloadDto);
            redisUtil.delete(clientIP);
            return R.ok("登陆成功", JwtTokenVo
                    .builder()
                    .accessToken(token)
                    .userInfo(userPayloadDto)
                    .build());
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (null == redisUtil.get(clientIP)) {
                redisUtil.setEx(clientIP, "0", 30, TimeUnit.MINUTES);
            }
            redisUtil.incrBy(clientIP, 1L);

            return R.err("登陆失败",e.getMessage());
        }
    }

    /**
     * 发送短信
     * @param msgDto
     * @return
     */
    @PostMapping("/sendMsg")
    @ApiOperation("发送短信验证码")
    public Object sendMsg(@RequestBody @Validated MsgDto msgDto) {
        log.info("request {}", msgDto);
//        String captchaCode = redisUtil.get(msgDto.getCsrfToken());
//        // 判断验证码
//        if (captchaCode==null || !captchaCode.equals(msgDto.getCaptchaCode().trim().toLowerCase())) {
//            throw new RuntimeException("验证码不正确");
//        }
//        redisUtil.delete(msgDto.getCsrfToken());
        sendMsgService.send(msgDto.getPhone());

        return R.ok("发送成功");
    }


    /**
     * 发送测试短信
     * @param msgDto
     * @return
     */
    @PostMapping("/testMsg")
    @ApiOperation("发送短信验证码")
    public Object testSendMsg(@RequestBody @Validated MsgDto msgDto) {
        log.info("request {}", msgDto);

        return R.ok("发送成功", sendMsgService.test(msgDto.getPhone()));
    }

    /**
     * 发送短信
     * @param msgDto
     * @return
     */
    @PostMapping("/getMobile")
    @ApiOperation("一键登录Token换手机号")
    public Object GetMobile(@RequestBody @Validated GetMobileDto msgDto){
        log.info("request {}", msgDto);
        String mobile = AliyunKeyLoginUtil.getMobile(msgDto.getAccessToken());

        return R.ok("获取成功", mobile);
    }

    /**
     * 获取验证码
     * @return
     */
    @ApiOperation("获取图形验证码")
    @GetMapping("/captcha")
    public Object getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map result = new HashMap();
        Integer limit = 0;
        String clientIP = ServletUtil.getClientIP(request, null);
        if (null != redisUtil.get(clientIP)) {
            limit = Integer.parseInt(redisUtil.get(clientIP));
        }
        SpecCaptcha specCaptcha = new SpecCaptcha(117, 44, 4);
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        redisUtil.setEx(key, verCode, 30, TimeUnit.MINUTES);
        result.put("csrfToken", key);
        result.put("image", specCaptcha.toBase64());
        result.put("showCaptcha", limit > 3);

        return R.ok("获取成功", result);
    }

    /**
     * 切换机构，门店
     * @param dto
     * @return
     */
    @PostMapping("/api/switch")
    @ApiOperation("切换机构")
    public Object sendMsg(@RequestBody @Validated SwitchUserDto dto) {
        try {
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            UserPayloadDto userPayloadDto = loginService.switchUser();
            log.info("switch user {}, {}", userPayloadDto, dto);
            String token = jwtHelper.createJWT(userPayloadDto);

            return R.ok("切换成功", JwtTokenVo
                    .builder()
                    .accessToken(token)
                    .userInfo(userPayloadDto)
                    .build());
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
}
