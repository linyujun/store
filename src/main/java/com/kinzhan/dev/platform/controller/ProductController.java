package com.kinzhan.dev.platform.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.dto.mall.product.ProductDto;
import com.kinzhan.dev.platform.beans.dto.mall.product.ActivityProductDto;
import com.kinzhan.dev.platform.beans.entity.ProductDetailEntity;
import com.kinzhan.dev.platform.service.CategoryService;
import com.kinzhan.dev.platform.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;
import com.kinzhan.dev.platform.service.ProductService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author songsir
* @date 2023-04-11
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storeProduct")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
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

        return R.ok("保存成功", result);
    }

    /**
     * 设置秒杀商品
     * @param dto
     * @return
     */
    @PostMapping("/seckill")
    public Object saveSeckillGoods(@Validated @RequestBody ActivityProductDto dto){

        return R.ok("保存成功", productService.saveSeckillProdcut(dto));
    }

    /**
     * 设置积分商品
     * @param dto
     * @return
     */
    @PostMapping("/credit")
    public Object saveCreditGoods(@Validated @RequestBody ActivityProductDto dto){

        return R.ok("保存成功", productService.saveCreditProduct(dto));
    }


    /**
     * 设置拼团商品
     * @param dto
     * @return
     */
    @PostMapping("/combination")
    public Object saveCombinationGoods(@Validated @RequestBody ActivityProductDto dto){

        return R.ok("保存成功", productService.saveCombinationProduct(dto));
    }


    /**
     * 设置砍价商品
     * @param dto
     * @return
     */
    @PostMapping("/bargain")
    public Object saveBargainGoods(@Validated @RequestBody ActivityProductDto dto){

        return R.ok("保存成功", productService.saveBargainProduct(dto));
    }

    /**
     * 设置分销商品
     * @param dto
     * @return
     */
    @PostMapping("/promote")
    public Object saveRebateGoods(@Validated @RequestBody ActivityProductDto dto){

        return R.ok("保存成功", productService.saveRebateProduct(dto));
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

        return R.ok("操作成功");
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, @RequestBody PageDto pageDto) throws IOException {

        productService.download(response, Map.class, pageDto, null);
    }
}
