package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.mall.seckill.SeckillDto;
import com.mtstore.server.beans.entity.StoreSeckillEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreSeckillService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* 管理后台-商城-秒杀模块
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store/seckill")
public class StoreSeckillController {

    private final StoreSeckillService storeSeckillService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreSeckillEntity entity = storeSeckillService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody SeckillDto dto){

        return R.ok("保存成功", storeSeckillService.saveOrUpdate(dto));
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", storeSeckillService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeSeckillService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeSeckillService.removeById(id);

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
        storeSeckillService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeSeckillService.download(response, Map.class, pageDto, null);
    }
}
