package com.mtstore.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.template.TemplateDto;
import com.mtstore.server.beans.entity.TemplateEntity;
import com.mtstore.server.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 点评模板
 * </p>
 *
 * @author songsir
 * @since 2022-12-28
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/template")
public class TemplateController {
    final TemplateService service;

    @PostMapping
    public Object save(@RequestBody @Validated TemplateDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        TemplateEntity entity = service.getById(id);

        return R.ok("获取成功", entity);
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        service.removeById(id);

        return R.ok("操作成功");
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", service.getPageList(pageDto, null));
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Object findAll() {
        QueryWrapper<TemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }
}
