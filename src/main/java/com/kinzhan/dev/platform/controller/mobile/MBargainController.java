package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.ActivityQueryDto;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainCreateDto;
import com.kinzhan.dev.platform.beans.dto.mall.bargain.BargainHelpDto;
import com.kinzhan.dev.platform.beans.dto.mall.product.ProductQueryDto;
import com.kinzhan.dev.platform.beans.entity.ProductEntity;
import com.kinzhan.dev.platform.beans.entity.StoreBargainLogEntity;
import com.kinzhan.dev.platform.beans.entity.StoreBargainEntity;
import com.kinzhan.dev.platform.beans.enums.ActivityStatusEnum;
import com.kinzhan.dev.platform.service.ProductService;
import com.kinzhan.dev.platform.service.StoreBargainLogService;
import com.kinzhan.dev.platform.service.StoreBargainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author songsir
* @date 2023-05-06
*/
@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-砍价模块")
public class MBargainController {

    private final StoreBargainService bargainService;

    private final StoreBargainLogService bargainLogService;

    private final ProductService productService;


    @PostMapping("/app/bargain/create")
    @ApiOperation(value = "创建砍价")
    public Object doBargain(@RequestBody @Validated BargainCreateDto dto){

        return R.ok("操作成功", bargainService.create(dto));
    }

    @PostMapping("/app/bargain/help")
    @ApiOperation(value = "助力")
    public Object doBargain(@RequestBody @Validated BargainHelpDto dto){

        return R.ok("操作成功", bargainService.help(dto));
    }


    @PostMapping("/app/bargain/log/page")
    @ApiOperation(value = "获取砍价记录-分页")
    public Object getLogList(@RequestBody(required = false) QueryDto<ActivityQueryDto> queryDto){
        QueryWrapper<StoreBargainLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StoreBargainLogEntity::getUid, LoggedUser.get().getUserId());
        queryWrapper.lambda().eq(StoreBargainLogEntity::getParentId, 0);
        Page<ProductEntity> page = bargainLogService.pageList(queryDto, queryWrapper);

        return R.ok("获取成功", page);
    }

    @GetMapping("/bargain/log/{id}")
    @ApiOperation(value = "砍价记录详情")
    public Object getLogDetail(@PathVariable("id") Integer id){
        StoreBargainLogEntity entity = bargainLogService.getById(id);
        if (null != entity) {
            List<StoreBargainLogEntity> childList = bargainLogService
                    .lambdaQuery()
                    .eq(StoreBargainLogEntity::getParentId, id)
                    .list();
            entity.setDetails(childList);
        }

        return R.ok("获取成功", entity);
    }

    @PostMapping("/bargain/page")
    @ApiOperation(value = "获取砍价商品-分页")
    public Object getList(@RequestBody(required = false) QueryDto<ProductQueryDto> queryDto){
        List<StoreBargainEntity> resultList = bargainService
                .lambdaQuery()
                .eq(StoreBargainEntity::getStatus, ActivityStatusEnum.RUNNING)
                .list();
        List<Integer> productIds = resultList.stream()
                .map(StoreBargainEntity::getProductIds)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProductEntity::getEnabled, true)
                .eq(ProductEntity::getIsDelete, false)
                .in(ProductEntity::getId, productIds);

        Page<ProductEntity> page = productService.pageList(queryDto, queryWrapper);
        page.getRecords().forEach(item -> {
            resultList.forEach(bargain -> {
                if (bargain.getProductIds().contains(item.getId())) {
                    item.setBargain(bargain);
                }
            });
        });

        return R.ok("获取成功", page);
    }

    @GetMapping("/bargain/{id}")
    @ApiOperation(value = "砍价活动详情")
    public Object getDetail(@PathVariable("id") Integer id){
        StoreBargainEntity entity = bargainService.getById(id);

        return R.ok("获取成功", entity);
    }
}
