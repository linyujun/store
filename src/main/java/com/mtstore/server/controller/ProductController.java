package com.mtstore.server.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.mall.product.ProductDto;
import com.mtstore.server.beans.dto.mall.product.ActivityProductDto;
import com.mtstore.server.beans.entity.ProductDetailEntity;
import com.mtstore.server.service.ProductDetailService;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/storeProduct")
public class ProductController {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        ProductEntity entity = productService.getDetail(id);

        return R.ok("获取成功", entity);
    }


    @GetMapping("/detail/{id}")
    public Object getProductDetail(@PathVariable("id") Integer id){
        ProductDetailEntity entity = productDetailService.getById(id);
        Optional.ofNullable(entity).ifPresent(productDetail -> {
            productDetail.setProductName(productService.getById(productDetail.getProductId()).getProductName());
        });

        return R.ok("获取成功", entity);
    }

    @PostMapping
    public Object save(@Validated @RequestBody ProductDto dto){
        ProductEntity result = productService.save(dto);
        log.info("保存商品信息 {}", dto);
        return R.ok("保存成功", result);
    }

    /**
     * 设置积分商品
     * @param dto
     * @return
     */
    @PostMapping("/credit")
    public Object saveCreditGoods(@Validated @RequestBody ActivityProductDto dto){
        log.info("设置积分商品 {}", dto);
        return R.ok("保存成功", productService.saveCreditProduct(dto));
    }

    /**
     * 主商品分页
     * @param pageDto
     * @return
     */
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<ProductEntity> wrapper = new QueryWrapper();

        return R.ok("获取成功", productService.getPageList(pageDto, wrapper));
    }

    /**
     * 规格商品分页
     * @param pageDto
     * @return
     */
    @PostMapping("/details/getPageList")
    public Object findDetailsPage(@RequestBody PageDto pageDto) {
        QueryWrapper<ProductDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(ProductDetailEntity::getProductId);
        Page<ProductDetailEntity> pageList = productDetailService.getPageList(pageDto, queryWrapper);
        if (CollectionUtils.isNotEmpty(pageList.getRecords())) {
            pageList.getRecords().forEach(item-> {
                Optional.ofNullable(productService.getById(item.getProductId()))
                        .ifPresent( v -> item.setProductName(v.getProductName()));
            });
        }

        return R.ok("获取成功", pageList);
    }


    @PostMapping("/list")
    public Object findPage(@RequestBody List<Integer> productIds) {

        return R.ok("获取成功", productService.listByIds(productIds));
    }

    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            productService.removeById(id);
        });
        log.info("批量删除商品");
        return R.ok("操作成功");
    }

    /**
     * 批量上架
     * @param ids
     * @return
     */
    @PostMapping("/online")
    public Object online(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            productService.online(id);
        });
        log.info("批量上架商品");
        return R.ok("操作成功");
    }

    /**
     * 批量下架
     * @param ids
     * @return
     */
    @PostMapping("/offline")
    public Object offline(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            productService.offline(id);
        });
        log.info("批量下架商品");
        return R.ok("操作成功");
    }

    /**
     * 单个上架/下架
     * @param id
     * @return
     */
    @GetMapping("/enabled/{id}")
    public Object enabled(@PathVariable("id") Integer id) {
        productService.enabled(id);
        log.info("单个商品上架/下架：" + id);
        return R.ok("操作成功");
    }

    /**
     * 将商品移出活动
     * @param activity
     * @param id
     * @return
     */
    @GetMapping("/remove/{activity}/{id}")
    public Object removeActivity(@PathVariable("activity") String activity, @PathVariable("id") Integer id) {
        productService.removeActivity(activity, id);
        return R.ok("操作成功");
    }


    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        productService.removeById(id);
        log.info("删除商品：" + id);
        return R.ok("操作成功");
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        productService.download(response, Map.class, pageDto, null);
    }
}
