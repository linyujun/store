package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.user.UserBrokerageBillDto;
import com.mtstore.server.beans.entity.UserBrokerageBillEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.UserBrokerageBillService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-06-19
*/
@RequiredArgsConstructor
@Api(tags = "用户佣金记录管理")
@RestController
@RequestMapping("/api/userBrokerageBill")
public class UserBrokerageBillController {

    private final UserBrokerageBillService userBrokerageBillService;

    @GetMapping("{id}")
    @ApiOperation("查询用户佣金记录")
    public Object getOne(@PathVariable("id") Integer id){
        UserBrokerageBillEntity entity = userBrokerageBillService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    @ApiOperation("新增用户佣金记录")
    public Object save(@Validated @RequestBody UserBrokerageBillDto dto){
        userBrokerageBillService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @ApiOperation("分页查询用户佣金记录")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", userBrokerageBillService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            userBrokerageBillService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        userBrokerageBillService.removeById(id);

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
        userBrokerageBillService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        userBrokerageBillService.download(response, Map.class, pageDto, null);
    }
}
