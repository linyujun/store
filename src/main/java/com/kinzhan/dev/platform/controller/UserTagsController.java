package com.kinzhan.dev.platform.controller;

import java.util.*;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.UserTagsEntity;
import com.kinzhan.dev.platform.beans.dto.user.UserTagsDto;
import com.kinzhan.dev.platform.service.UserTagsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-05-26
*/
@RequiredArgsConstructor
@Api(tags = "用户标签管理")
@RestController
@RequestMapping("/api/userTags")
public class UserTagsController {

    private final UserTagsService userTagsService;

    @GetMapping("{id}")
    @ApiOperation("查询用户标签")
    public Object getOne(@PathVariable("id") Integer id){
        UserTagsEntity entity = userTagsService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    @ApiOperation("新增用户标签")
    public Object save(@Validated @RequestBody UserTagsDto dto){
        userTagsService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询用户标签")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", userTagsService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            userTagsService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        userTagsService.removeById(id);

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
        userTagsService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        userTagsService.download(response, Map.class, pageDto, null);
    }
}
