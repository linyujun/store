package com.mtstore.server.controller;

import java.util.*;

import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.article.ArticleDto;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* 文章
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        ArticleEntity entity = articleService.getById(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping
    public Object save(@Validated @RequestBody ArticleDto dto){
        articleService.saveOrUpdate(dto);

        return R.ok("保存成功", true);
    }

    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        return R.ok("获取成功", articleService.getPageList(pageDto, null));
    }


    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            articleService.removeById(id);
        });

        return R.ok("操作成功");
    }

    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        articleService.removeById(id);

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
        articleService.disable(id);
        return R.ok("操作成功", true);
    }


    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        articleService.download(response, Map.class, pageDto, null);
    }
}
