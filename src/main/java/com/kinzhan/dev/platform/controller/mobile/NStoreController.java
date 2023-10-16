package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.article.ArticleQueryDto;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.store.StoreQueryDto;
import com.kinzhan.dev.platform.beans.entity.ArticleCategoryEntity;
import com.kinzhan.dev.platform.beans.entity.ArticleEntity;
import com.kinzhan.dev.platform.beans.entity.StoreEntity;
import com.kinzhan.dev.platform.service.ArticleCategoryService;
import com.kinzhan.dev.platform.service.ArticleService;
import com.kinzhan.dev.platform.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@CrossOrigin
@Api(tags="移动端-门店模块")
public class NStoreController {

    final StoreService storeService;


    @GetMapping("{id}")
    @ApiOperation("查询门店详情")
    public Object getOne(@PathVariable("id") Integer id){

        return R.ok("获取成功", storeService.getById(id));
    }

    @ApiOperation("分页查询门店")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody QueryDto<StoreQueryDto> queryDto) {
        QueryWrapper<StoreEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StoreEntity::getEnabled,true);
        if (null != queryDto.getFilter().getLatitude() && null != queryDto.getFilter().getLongitude()) {
            queryWrapper.select(
                    String.format("*, ST_Distance_Sphere(POINT(%s, %s), POINT(longitude, latitude)) as distance",
                    queryDto.getFilter().getLongitude(),
                    queryDto.getFilter().getLatitude()));
            queryWrapper.orderByAsc("distance");
        }

        return R.ok("获取成功", storeService.pageList(queryDto, queryWrapper));
    }
}
