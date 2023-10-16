package com.kinzhan.dev.platform.controller.mobile;

import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.service.SysPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
@Api(tags="移动端-全局配置模块")
public class NSettingsController {

    final SysPropertyService sysPropertyService;

    @GetMapping("/global")
    @ApiOperation("全局配置")
    public Object findGlobal() {

        return R.ok("获取成功", sysPropertyService.findAllPublic());
    }
}
