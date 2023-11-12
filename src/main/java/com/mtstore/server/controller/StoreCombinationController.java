package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.mall.combination.StoreCombinationDto;
import com.mtstore.server.beans.entity.StoreCombinationEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreCombinationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* 拼团
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeCombination")
public class StoreCombinationController {

    private final StoreCombinationService storeCombinationService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreCombinationEntity entity = storeCombinationService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody StoreCombinationDto dto){
        //同一商品同一时间只能参加一个拼团活动
        storeCombinationService.valid(dto);
        storeCombinationService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", storeCombinationService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeCombinationService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeCombinationService.removeById(id);

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
        storeCombinationService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeCombinationService.download(response, Map.class, pageDto, null);
    }
}
