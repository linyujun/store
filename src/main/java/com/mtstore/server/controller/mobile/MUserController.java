package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserInfoDto;
import com.mtstore.server.beans.entity.UserEntity;
import com.mtstore.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping({ "/app/user" })
@Api(tags="移动端-用户-基本信息")
@RequiredArgsConstructor
public class MUserController {
    private final UserService userService;

    @ApiOperation("查看当前用户信息")
    @GetMapping({ "/current", "/userInfo" })
    public Object currentUser() {
        try {
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            UserEntity user = userService.findCurrentUser();
            Assert.notNull(user, "用户不存在，请确认");
            log.info(LoggedUser.get().getUserId() + " 查看当前用户信息");
            return R.ok("成功", user);
        } finally {
            userService.loginSuccess(LoggedUser.get().getUserId());
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    @ApiOperation("更新当前用户信息")
    @PostMapping
    public Object save(@RequestBody @Validated UserInfoDto dto) {
        UserEntity user = userService.saveOrUpdate(dto);
        log.info(LoggedUser.get().getUserId() + " 更新当前用户信息：{}" , dto);
        return R.ok("更新成功", user);
    }
}
