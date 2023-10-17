package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.service.StoreBargainLogService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.mall.bargain.StoreBargainDto;
import com.mtstore.server.beans.entity.StoreBargainEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreBargainService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-06-07
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeBargain")
public class StoreBargainController {

    private final StoreBargainService storeBargainService;

    private final StoreBargainLogService storeBargainLogService;

    @GetMapping("{id}")
    @ApiOperation("查询商城-砍价")
    public Object getOne(@PathVariable("id") Integer id){
        StoreBargainEntity entity = storeBargainService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody StoreBargainDto dto){
        storeBargainService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", storeBargainService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeBargainService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeBargainService.removeById(id);

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
        storeBargainService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeBargainService.download(response, Map.class, pageDto, null);
    }
}
