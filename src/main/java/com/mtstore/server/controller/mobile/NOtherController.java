package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.service.SysPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author songsir
 * @date 2021/6/4
 **/
@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-其他模块")
public class NOtherController {
    final SysPropertyService configService;

    @ApiOperation(value ="查看配置信息")
    @GetMapping("/config")
    public Object getAllSettings() {

        return R.ok("成功", configService.getMap());
    }
}
