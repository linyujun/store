package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.dto.diy.DiyNavDto;
import com.kinzhan.dev.platform.beans.entity.DiyNavEntity;
import com.kinzhan.dev.platform.service.DiyNavService;
import com.kinzhan.dev.platform.beans.common.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * DIY底部导航栏
 * </p>
 *
 * @author songsir
 * @since 2023-03-31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diy/nav")
public class DiyNavController {
    final DiyNavService service;

    @PostMapping
    public Object save(@RequestBody @Validated List<DiyNavDto> todoList) {
        service.save(todoList);
        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        DiyNavEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<DiyNavEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }

    /**
     * 搜索自动完成
     * @param name
     * @return
     */
    @GetMapping("/search/{name}")
    public Object search(@PathVariable("name") String name) {
        QueryWrapper queryWrapper = new QueryWrapper();

        return R.ok("获取成功", service.list(queryWrapper));
    }
}
