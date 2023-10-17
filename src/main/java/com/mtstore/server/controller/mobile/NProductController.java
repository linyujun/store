package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.mall.product.ProductQueryDto;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.service.CategoryService;
import com.mtstore.server.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin
@Api(tags="移动端-商城-商品管理")
public class NProductController {

    final ProductService productService;

    final CategoryService categoryService;

    @GetMapping("{id}")
    @ApiOperation("查询普通商品详情")
    public Object getOne(@PathVariable("id") Integer id){
        try {
            ProductEntity entity = productService.getDetail(id);

            return R.ok("获取成功", entity);
        } finally {
            //TODO 记录访问次数和用户足迹
        }
    }

    @PostMapping("/list")
    @ApiOperation("商品分页")
    public Object getProducts(@RequestBody(required = false) QueryDto<ProductQueryDto> queryDto) {
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.lambda().eq(ProductEntity::getEnabled, true);
        wrapper.lambda().eq(ProductEntity::getIsDelete, false);
        Optional.ofNullable(queryDto.getFilter()).map(ProductQueryDto::getCategoryId).ifPresent(categoryId -> {
            List<Integer> cateIds = categoryService.findAllIdByParentId(categoryId);
            if (cateIds.size() > 1) {
                wrapper.lambda().in(ProductEntity::getCategoryId, cateIds);
                queryDto.getFilter().setCategoryId(null);
            }
        });

        return R.ok("获取成功", productService.pageList(queryDto, wrapper));
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation("分类下所有商品")
    public Object getProductsByCategory(@PathVariable("categoryId") Integer categoryId) {

        return R.ok("获取成功", productService.findAllByCategoryId(categoryId));
    }

    @PostMapping("/video/list")
    @ApiOperation("小视频商品分页")
    public Object getVideoProducts(@RequestBody(required = false) QueryDto<ProductQueryDto> queryDto) {
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();
        wrapper.lambda().isNotNull(ProductEntity::getVideoUrl);
        wrapper.lambda().eq(ProductEntity::getIsDelete, false);
        wrapper.lambda().eq(ProductEntity::getEnabled, true);

        return R.ok("获取成功", productService.pageList(queryDto, wrapper));
    }
}
