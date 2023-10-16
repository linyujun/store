package com.kinzhan.dev.platform.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.DiyPathEntity;
import com.kinzhan.dev.platform.beans.dto.diy.DiyPathDto;
import com.kinzhan.dev.platform.service.DiyPathService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-04-25
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diy/path")
public class DiyPathController {

    private final DiyPathService diyPathService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        DiyPathEntity entity = diyPathService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody DiyPathDto dto){
        diyPathService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", diyPathService.getPageList(pageDto, null));
    }

    @GetMapping("/getAll")
    public Object findAll() {
        QueryWrapper<DiyPathEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", diyPathService.list(wrapper));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            diyPathService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        diyPathService.removeById(id);

        return R.ok("操作成功");
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        diyPathService.download(response, Map.class, pageDto, null);
    }
}
