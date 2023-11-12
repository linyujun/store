package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.service.SysPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags="平台配置信息（免登录）")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/open/site")
public class SiteController {

    final SysPropertyService sysPropertyService;

    @GetMapping("/config")
    @ApiOperation("站点配置")
    public Object findGlobal() {

        return R.ok("获取成功", sysPropertyService.findAllByGroup("website"));
    }
}
