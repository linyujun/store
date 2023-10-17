package com.mtstore.server.controller.system;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.sysaddress.SysAddressDto;
import com.mtstore.server.beans.entity.SysAddressEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.SysAddressService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-06-13
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sysAddress")
public class SysAddressController {

    private final SysAddressService sysAddressService;

    @GetMapping("{id}")
    @ApiOperation("查询系统售后收货地址")
    public Object getOne(@PathVariable("id") Integer id){
        SysAddressEntity entity = sysAddressService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    @ApiOperation("新增系统售后收货地址")
    public Object save(@Validated @RequestBody SysAddressDto dto){
        sysAddressService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询系统售后收货地址")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", sysAddressService.getPageList(pageDto, null));
    }

    @ApiOperation("获取所有可用地址")
    @GetMapping("/getAll")
    public Object getAll() {
        return R.ok("获取成功", sysAddressService.lambdaQuery().eq(SysAddressEntity::getEnabled, true).list());
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            sysAddressService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        sysAddressService.removeById(id);

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
        sysAddressService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        sysAddressService.download(response, Map.class, pageDto, null);
    }
}
