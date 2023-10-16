package com.kinzhan.dev.platform.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.entity.StoreServiceEntity;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.PrinterEntity;
import com.kinzhan.dev.platform.beans.dto.printer.PrinterDto;
import com.kinzhan.dev.platform.service.PrinterService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-06-15
*/
@RequiredArgsConstructor
@Api(tags = "打印机管理")
@RestController
@RequestMapping("/api/printer")
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping("{id}")
    @ApiOperation("查询打印机")
    public Object getOne(@PathVariable("id") Integer id){
        PrinterEntity entity = printerService.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/test/{id}")
    @ApiOperation("测试打印机")
    public Object test(@PathVariable("id") Integer id){

        return R.ok("操作成功", JSONObject.parse(printerService.test(id)));
    }

    @PostMapping
    @ApiOperation("新增打印机")
    public Object save(@Validated @RequestBody PrinterDto dto){
        printerService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询打印机")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", printerService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            printerService.removeById(id);
        });

        return R.ok("操作成功");
    }


    @GetMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<PrinterEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PrinterEntity::getEnabled, true);

        return R.ok("获取成功", printerService.list(wrapper));
    }


    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        printerService.removeById(id);

        return R.ok("操作成功");
    }

    /**
    * 禁用，启用
    * @param id
    * @return
    */
    @GetMapping("/disable/{id}")
    @ResponseBody
    public Object disable(@PathVariable("id") Integer id) {
        printerService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        printerService.download(response, Map.class, pageDto, null);
    }
}
