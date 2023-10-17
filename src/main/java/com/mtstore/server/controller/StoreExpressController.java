package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.mall.StoreExpressDto;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.StoreExpressEntity;
import com.mtstore.server.service.StoreExpressService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-05-31
*/
@RequiredArgsConstructor
@Api(tags = "商城运费模板管理")
@RestController
@RequestMapping("/api/storeExpress")
public class StoreExpressController {

    private final StoreExpressService storeExpressService;

    @GetMapping("{id}")
    @ApiOperation("查询商城运费模板")
    public Object getOne(@PathVariable("id") Integer id){

        return R.ok("获取成功", storeExpressService.getDetail(id));
    }


    @PostMapping
    @ApiOperation("新增商城运费模板")
    public Object save(@Validated @RequestBody StoreExpressDto dto){
        storeExpressService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询商城运费模板")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<StoreExpressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StoreExpressEntity::getParentId, 0);

        return R.ok("获取成功", storeExpressService.getPageList(pageDto, queryWrapper));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeExpressService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        //TODO 检查至少保留一个运费模板
        Optional.ofNullable(storeExpressService.getById(id)).ifPresent(v -> {
            if (v.getIsDefault()) throw new RuntimeException("默认模板不可删除");
        });


        storeExpressService.deleteByParentId(id);
        storeExpressService.removeById(id);

        return R.ok("操作成功");
    }


    @ApiOperation("设置默认地址")
    @GetMapping("/default/{id}")
    public Object setDefault(@PathVariable("id") Integer id) {
        Optional.ofNullable(storeExpressService.getById(id)).ifPresent(v -> {
            if (v.getIsDefault()) throw new RuntimeException("请至少保留一个默认地址");
        });
        storeExpressService.setDefault(id);

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
        Optional.ofNullable(storeExpressService.getById(id)).ifPresent(v -> {
            if (v.getIsDefault()) throw new RuntimeException("默认模板不可禁用");
        });
        storeExpressService.disable(id);

        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeExpressService.download(response, Map.class, pageDto, null);
    }
}
