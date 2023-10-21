package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.article.ArticleQueryDto;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.ArticleCategoryEntity;
import com.mtstore.server.beans.entity.ArticleEntity;
import com.mtstore.server.service.ArticleCategoryService;
import com.mtstore.server.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
@CrossOrigin
@Api(tags="移动端-资讯模块")
public class NArticleController {

    final ArticleCategoryService categoryService;

    final ArticleService articleService;

    @GetMapping("/notice/index")
    @ApiOperation("获取首页公告")
    public Object getNotice(){
        ArticleEntity entity = articleService
                .lambdaQuery().eq(ArticleEntity::getCategoryId, 1)
                .orderByDesc(ArticleEntity::getId).last("limit 1").one();

        return R.ok("获取成功", entity);
    }

    @GetMapping("{id}")
    @ApiOperation("查询文章资讯详情")
    public Object getOne(@PathVariable("id") Integer id){
        try {
            ArticleEntity entity = articleService.getById(id);
            log.info(LoggedUser.get().getUserId() + " 查询文章资讯详情：" + id);
            return R.ok("获取成功", entity);
        } finally {
            articleService.visited(id);
        }
    }

    @ApiOperation("分页查询文章资讯")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody QueryDto<ArticleQueryDto> queryDto) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ArticleEntity::getEnabled,true);

        return R.ok("获取成功", articleService.pageList(queryDto, queryWrapper));
    }

    @GetMapping("/category/getAll")
    @ApiOperation("全部分类")
    public Object findAll() {
        List<ArticleCategoryEntity> resultList = categoryService.lambdaQuery()
                .eq(ArticleCategoryEntity::getEnabled, true)
                .orderByAsc(ArticleCategoryEntity::getSort)
                .list();

        return R.ok("获取成功", resultList);
    }
}
