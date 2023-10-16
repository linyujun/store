package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.mall.product.ProductQueryDto;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;
import com.kinzhan.dev.platform.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @author songsir
* @date 2023-05-06
*/
@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-积分模块")
@RequestMapping("/credit")
public class MCreditController {

    private final ProductService productService;

    @PostMapping("/page")
    @ApiOperation(value = "获取所有积分商品-分页")
    public Object getList(@RequestBody(required = false) QueryDto<ProductQueryDto> queryDto){
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProductEntity::getEnabled, true)
                .eq(ProductEntity::getIsDelete, false)
                .like(ProductEntity::getActivity, "CREDIT");

        return R.ok("获取成功", productService.pageList(queryDto, queryWrapper));
    }
}
