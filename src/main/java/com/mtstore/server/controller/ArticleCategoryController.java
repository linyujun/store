package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.util.tree.CategoryBuilder;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.article.ArticleCategoryDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.ArticleCategoryEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.ArticleCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-04-27
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article/category")
public class ArticleCategoryController {

    private final ArticleCategoryService articleCategoryService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        ArticleCategoryEntity entity = articleCategoryService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody ArticleCategoryDto dto){
        articleCategoryService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", articleCategoryService.getPageList(pageDto, null));
    }

    @GetMapping("/getAll")
    public Object findAll() {
        QueryWrapper<ArticleCategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("enabled", 1);

        return R.ok("获取成功", articleCategoryService.list(wrapper));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            articleCategoryService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        articleCategoryService.removeById(id);

        return R.ok("操作成功");
    }


    @GetMapping({ "/getTree" })
    public Object getTree() {
        List<ArticleCategoryEntity> listDict = articleCategoryService.
                lambdaQuery()
                .eq(ArticleCategoryEntity::getEnabled, true)
                .eq(ArticleCategoryEntity::getIsDelete, false)
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

    /**
    * 禁用，启用
    * @param id
    * @return
    */
    @GetMapping("/disable/{id}")
    @ResponseBody
    public Object disable(@PathVariable("id") Integer id) {
        articleCategoryService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        articleCategoryService.download(response, Map.class, pageDto, null);
    }
}
