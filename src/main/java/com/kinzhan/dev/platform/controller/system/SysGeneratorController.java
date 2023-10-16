/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysColumnConfig;
import com.kinzhan.dev.platform.beans.entity.sys.SysGenConfig;
import com.kinzhan.dev.platform.service.GenConfigService;
import com.kinzhan.dev.platform.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@RestController
@RequestMapping("/api/sys/generator")
@RequiredArgsConstructor
@Api(tags = "系统模块-代码生成")
public class SysGeneratorController {

    private final GeneratorService generatorService;

    private final GenConfigService genConfigService;



    @ApiOperation("查询")
    @GetMapping(value = "/config/{tableName}")
    public Object getConfig(@PathVariable String tableName){

        return R.ok("成功", genConfigService.find(tableName));
    }

    @ApiOperation("修改")
    @PutMapping(value = "/config")
    public Object saveConfig(@Validated @RequestBody SysGenConfig genConfig){

        return R.ok("成功", genConfigService.update(genConfig.getTableName(), genConfig));
    }


    @ApiOperation("查询数据库数据")
    @GetMapping(value = "/tables/all")
    public Object getTables(){

        return R.ok("成功", generatorService.getTables());
    }

    @ApiOperation("查询数据库数据")
    @PostMapping(value = "/getPageList")
    public Object getTables(@RequestBody PageDto pageDto){
        QueryWrapper<SysColumnConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("table_schema = (select database())");
        queryWrapper.orderByDesc("create_time");

        return R.ok("成功", generatorService.getPageList(pageDto, queryWrapper));
    }

    @ApiOperation("查询字段数据")
    @GetMapping(value = "/columns")
    public Object getColumns(@RequestParam String tableName){
        List<SysColumnConfig> columnInfos = generatorService.getColumns(tableName);

        return R.ok("成功", columnInfos);
    }

    @ApiOperation("保存字段数据")
    @PutMapping
    public Object save(@RequestBody List<SysColumnConfig> columnInfos){
        generatorService.save(columnInfos);

        return R.ok("成功");
    }

    @ApiOperation("同步字段数据")
    @PostMapping(value = "sync")
    public Object sync(@RequestBody List<String> tables){
        for (String table : tables) {
            generatorService.sync(generatorService.getColumns(table), generatorService.query(table));
        }

        return R.ok("成功");
    }

    @ApiOperation("生成代码")
    @PostMapping(value = "/{tableName}/{type}")
    public Object generator(@PathVariable String tableName, @PathVariable Integer type, HttpServletRequest request, HttpServletResponse response){
        switch (type){
            // 生成代码
            case 0: generatorService.generator(genConfigService.find(tableName), generatorService.getColumns(tableName));
                    break;
            // 预览
            case 1: return generatorService.preview(genConfigService.find(tableName), generatorService.getColumns(tableName));
            // 打包
            case 2: generatorService.download(genConfigService.find(tableName), generatorService.getColumns(tableName), request, response);
                    break;
            default: throw new RuntimeException("没有这个选项");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
