package com.kinzhan.dev.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.category.CategoryDto;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.CategoryEntity;
import com.kinzhan.dev.platform.beans.entity.sys.SysDeptEntity;
import com.kinzhan.dev.platform.service.CategoryService;
import com.kinzhan.dev.platform.util.tree.CategoryBuilder;
import com.kinzhan.dev.platform.util.tree.DeptBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songsir
 * @since 2021-11-22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    final CategoryService service;

    @PostMapping
    public Object save(@RequestBody @Validated CategoryDto dto) {
        service.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @GetMapping("{id}")
    public Object findOne(@PathVariable("id") Integer id) {
        CategoryEntity entity = service.getById(id);

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

    @GetMapping("/getAll")
    public Object findAll() {
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", service.list(wrapper));
    }

    @GetMapping({ "/getTree" })
    public Object getTree() {
        List<CategoryEntity> listDict = service.
                lambdaQuery()
                .eq(CategoryEntity::getEnabled, true)
                .eq(CategoryEntity::getIsDelete, false)
                .list();
        List<CategoryBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            CategoryBuilder.Node node = new CategoryBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            node.setLabel(entity.getTitle());
            nodes.add(node);
        });
        List<CategoryBuilder.Node> result = new CategoryBuilder().buildTree(nodes);

        return R.ok("获取成功", result);
    }
}
