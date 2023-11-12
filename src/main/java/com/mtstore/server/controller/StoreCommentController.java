package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.dto.mall.comment.ReplyDto;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.StoreCommentEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.StoreCommentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Api(tags = "商城商品评价管理")
@RestController
@RequestMapping("/api/storeComment")
public class StoreCommentController {

    private final StoreCommentService storeCommentService;

    @GetMapping("{id}")
    @ApiOperation("查询商城商品评价")
    public Object getOne(@PathVariable("id") Integer id){
        StoreCommentEntity entity = storeCommentService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping("/reply")
    @ApiOperation("商家答复")
    public Object save(@Validated @RequestBody ReplyDto dto){
        storeCommentService.reply(dto);

        return R.ok("操作成功", true);
    }

    @ApiOperation("分页查询商城商品评价")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<StoreCommentEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(StoreCommentEntity::getStatus, 1);

        return R.ok("获取成功", storeCommentService.getPageList(pageDto, queryWrapper));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeCommentService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeCommentService.removeById(id);

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
        storeCommentService.disable(id);
        return R.ok("操作成功", true);
    }


    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        storeCommentService.download(response, Map.class, pageDto, null);
    }
}
