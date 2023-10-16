package com.kinzhan.dev.platform.controller;

import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.service.SysPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Api(tags="平台配置信息（免登录）")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/site")
public class SiteController {

    final SysPropertyService sysPropertyService;

    @GetMapping("/config")
    @ApiOperation("站点配置")
    public Object findGlobal() {

        return R.ok("获取成功", sysPropertyService.findAllByGroup("website"));
    }
}
