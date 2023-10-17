package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.dto.user.UserAddressDto;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.UserAddressEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.UserAddressService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userAddress")
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        UserAddressEntity entity = userAddressService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody UserAddressDto dto){
        userAddressService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", userAddressService.getPageList(pageDto, null));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            userAddressService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        userAddressService.removeById(id);

        return R.ok("操作成功");
    }
}
