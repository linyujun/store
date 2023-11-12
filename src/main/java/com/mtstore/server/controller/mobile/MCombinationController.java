package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.ActivityQueryDto;
import com.mtstore.server.beans.dto.mall.product.ProductQueryDto;
import com.mtstore.server.beans.entity.ProductEntity;
import com.mtstore.server.beans.entity.StoreCombinationEntity;
import com.mtstore.server.beans.entity.StoreCombinationLogEntity;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import com.mtstore.server.service.ProductService;
import com.mtstore.server.service.StoreCombinationLogService;
import com.mtstore.server.service.StoreCombinationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-拼团模块")
public class MCombinationController {

    private final StoreCombinationService combinationService;

    private final StoreCombinationLogService combinationLogService;

    private final ProductService productService;

    @PostMapping("/app/combination/log/page")
    @ApiOperation(value = "获取拼团记录-分页")
    public Object getLogList(@RequestBody(required = false) QueryDto<ActivityQueryDto> queryDto){
        QueryWrapper<StoreCombinationLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StoreCombinationLogEntity::getUid, LoggedUser.get().getUserId());
        Page<ProductEntity> page = combinationLogService.pageList(queryDto, queryWrapper);

        return R.ok("获取成功", page);
    }

    @GetMapping("/open/combination/log/{id}")
    @ApiOperation(value = "拼团记录详情")
    public Object getLogDetail(@PathVariable("id") Integer id){
        StoreCombinationLogEntity entity = combinationLogService.getById(id);
        if (null != entity) {
            List<StoreCombinationLogEntity> childList = combinationLogService.findAllByGroupId(entity.getId());
            entity.setDetails(childList);
        }

        return R.ok("获取成功", entity);
    }

    @PostMapping("/open/combination/page")
    @ApiOperation(value = "获取拼团商品-分页")
    public Object getList(@RequestBody(required = false) QueryDto<ProductQueryDto> queryDto){
        /*List<StoreCombinationEntity> resultList = combinationService
                .lambdaQuery()
                .eq(StoreCombinationEntity::getStatus, ActivityStatusEnum.RUNNING)
                .list();
        List<Integer> productIds = resultList.stream()
                .map(StoreCombinationEntity::getProductIds)
                .flatMap(List::stream).distinct().collect(Collectors.toList());
        QueryWrapper<ProductEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProductEntity::getEnabled, true)
                .eq(ProductEntity::getIsDelete, false)
                .in(ProductEntity::getId, productIds);
        Page<ProductEntity> page = productService.pageList(queryDto, queryWrapper);
        page.getRecords().forEach(item -> {
            resultList.forEach(combination -> {
                if (combination.getProductIds().contains(item.getId())) {
                    item.setCombination(combination);
                }
            });
        });

        return R.ok("获取成功", page);*/
        return R.ok("获取成功", null);
    }

    @GetMapping("/open/combination/{id}")
    @ApiOperation(value = "拼团活动详情")
    public Object getDetail(@PathVariable("id") Integer id){
        StoreCombinationEntity entity = combinationService.getById(id);
        if (null != entity) {
            entity.setRecords(combinationLogService.findAllByCombinationId(id));
        }

        return R.ok("获取成功", entity);
    }
}
