package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.annotion.Query;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.user.UserAddressDto;
import com.kinzhan.dev.platform.beans.entity.UserAddressEntity;
import com.kinzhan.dev.platform.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@Api(tags = "移动端-用户-收货地址管理")
@RestController
@RequestMapping("/app/user/address")
public class MUserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("{id}")
    @ApiOperation("查询用户收货地址")
    public Object getOne(@PathVariable("id") Integer id){
        UserAddressEntity entity = userAddressService.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/default")
    @ApiOperation("查询用户默认收货地址")
    public Object getDefault(){
        UserAddressEntity entity = userAddressService.getDefault(LoggedUser.get().getUserId());

        return R.ok("获取成功", entity);
    }

    @PostMapping
    @ApiOperation("新增用户收货地址")
    public Object save(@Validated @RequestBody UserAddressDto dto){
        userAddressService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }


    @ApiOperation("用户全部地址")
    @GetMapping("/getAll")
    public Object getAll() {

        return R.ok("获取成功", userAddressService.getAll(LoggedUser.get().getUserId()));
    }

    @ApiOperation("分页查询用户收货地址")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<UserAddressEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserAddressEntity::getUid, LoggedUser.get().getUserId());

        return R.ok("获取成功", userAddressService.getPageList(pageDto, queryWrapper));
    }

    @DeleteMapping
    @ApiOperation("批量删除收货地址")
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            userAddressService.forceDelete(id);
        });

        return R.ok("操作成功");
    }

    @ApiOperation("设置默认地址")
    @GetMapping("/default/{id}")
    public Object setDefault(@PathVariable("id") Integer id) {
        userAddressService.setDefault(id);

        return R.ok("操作成功");
    }

    @ApiOperation("删除地址")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        userAddressService.forceDelete(id);

        return R.ok("操作成功");
    }
}
