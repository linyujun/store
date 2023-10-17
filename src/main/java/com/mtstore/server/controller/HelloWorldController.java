package com.mtstore.server.controller;

import com.mtstore.server.beans.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songsir
 * @date 2021/6/4
 **/
@Api(tags="接口测试")
@RestController
@CrossOrigin
public class HelloWorldController {


    @ApiOperation("字符串")
    @GetMapping({ "/hello" })
    public String hello() {

        return "Hello World" ;
    }

    @ApiOperation("需要登录的接口")
    @GetMapping({ "/api/hello" })
    public Object hello2() {

        return R.ok("成功","Hello World");
    }
}
