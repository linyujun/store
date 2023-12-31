package com.mtstore.server.controller.mobile;

import com.mtstore.server.beans.common.R;

import com.mtstore.server.beans.entity.StoreSeckillEntity;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import com.mtstore.server.service.ProductService;
import com.mtstore.server.service.StoreSeckillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-秒杀模块")
@RequestMapping("/seckill")
public class MSeckillController {

    private final StoreSeckillService seckillService;
    private final ProductService productService;

    @GetMapping("/list")
    @ApiOperation(value = "当日活动")
    public Object getList(){
        List<StoreSeckillEntity> result = seckillService
                .lambdaQuery()
                .eq(StoreSeckillEntity::getActivityTime, LocalDate.now())
                .orderByAsc(StoreSeckillEntity::getStartTime)
                .list();

        return R.ok("获取成功", result);
    }

    @GetMapping("/index")
    @ApiOperation(value = "首页秒杀活动")
    public Object getIndex(){
        StoreSeckillEntity entity = seckillService.lambdaQuery()
                .eq(StoreSeckillEntity::getStatus, ActivityStatusEnum.RUNNING)
                .eq(StoreSeckillEntity::getEnabled, true)
                .last("limit 1")
                .one();

        Optional.ofNullable(entity).ifPresent(v -> {
            entity.setProducts(productService.listByIds(v.getProductIds()));
        });

        return R.ok("获取成功", entity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "秒杀详情")
    public Object getDetail(@PathVariable("id") Integer id){

        return R.ok("获取成功", seckillService.getById(id));
    }
}
