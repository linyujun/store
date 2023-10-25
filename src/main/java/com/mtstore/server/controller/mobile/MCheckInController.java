package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping({ "/app" })
@Api(tags="移动端-签到模块")
@RequiredArgsConstructor
public class MCheckInController {

    private final UserService userService;

    @ApiOperation("签到接口-签到")
    @PostMapping("/checkIn")
    public Object checkIn() {
        userService.checkIn();
        log.info(LoggedUser.get().getUserId() + " 签到");
        return R.ok("签到成功");
    }

    @ApiOperation("签到接口-查看签到状态")
    @GetMapping("/checkIn/status")
    public Object checkInStatus() {

        return R.ok("获取成功", userService.checkInStatus());
    }
}
