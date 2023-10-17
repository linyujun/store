package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.mall.recharge.RechargeDto;
import com.mtstore.server.beans.entity.StoreRechargeEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreRechargeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-05-26
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeRecharge")
public class StoreRechargeController {

    private final StoreRechargeService storeRechargeService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        StoreRechargeEntity entity = storeRechargeService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody RechargeDto dto){
        storeRechargeService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<StoreRechargeEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(StoreRechargeEntity::getSort);

        return R.ok("获取成功", storeRechargeService.getPageList(pageDto, wrapper));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeRechargeService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeRechargeService.removeById(id);

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
        storeRechargeService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeRechargeService.download(response, Map.class, pageDto, null);
    }
}
