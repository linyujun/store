package com.kinzhan.dev.platform.controller.mobile;

import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@CrossOrigin
@Api(tags="移动端-分类模块")
public class NCategoryController {

    final CategoryService categoryService;

    @GetMapping("/tree")
    @ApiOperation("全部分类")
    public Object findAll() {

        return R.ok("获取成功", categoryService.getTree());
    }
}
