package com.kinzhan.dev.platform.controller.mobile;

import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping({ "/app/checkIn" })
@Api(tags="移动端-签到模块")
@RequiredArgsConstructor
public class MCheckInController {

    private final UserService userService;

    @ApiOperation("签到接口-签到")
    @PostMapping
    public Object checkIn() {
        userService.checkIn();

        return R.ok("签到成功");
    }

    @ApiOperation("签到接口-查看签到状态")
    @GetMapping("/checkIn/status")
    public Object checkInStatus() {

        return R.ok("获取成功", userService.checkInStatus());
    }
}
