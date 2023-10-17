package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.store.StoreDto;
import com.mtstore.server.beans.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-04-27
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreEntity entity = storeService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody StoreDto dto){
        storeService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", storeService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeService.removeById(id);

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
        storeService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeService.download(response, Map.class, pageDto, null);
    }
}
