package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.mall.aftersales.StoreAfterSalesDto;
import com.mtstore.server.beans.dto.mall.aftersales.StoreReturnDto;
import com.mtstore.server.beans.entity.StoreAfterSalesEntity;
import com.mtstore.server.service.StoreAfterSalesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author songsir
* @date 2023-06-12
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store/afterSales")
@Api(tags = "移动端-售后")
@Slf4j
public class MStoreAfterSalesController {

    private final StoreAfterSalesService storeAfterSalesService;

    @GetMapping("{id}")
    @ApiOperation("售后详情")
    public Object getOne(@PathVariable("id") Integer id){
        //TODO 检查是否是本人的
        StoreAfterSalesEntity entity = storeAfterSalesService.getById(id);

        return R.ok("获取成功", entity);
    }

    @PostMapping("/returned")
    @ApiOperation("退货方式")
    public Object returned(@Validated @RequestBody StoreReturnDto dto){
        StoreAfterSalesEntity entity = storeAfterSalesService.returned(dto);

        return R.ok("操作成功", entity);
    }

    @PostMapping
    @ApiOperation("发起售后")
    public Object save(@Validated @RequestBody StoreAfterSalesDto dto){
        log.info(LoggedUser.get().getUserId() + " 发起售后：" + dto.getOrderId());
        return R.ok("保存成功", storeAfterSalesService.saveOrUpdate(dto));
    }

    @ApiOperation("售后记录分页")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody PageDto pageDto) {
        QueryWrapper<StoreAfterSalesEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(StoreAfterSalesEntity::getUid, LoggedUser.get().getUserId());

        return R.ok("获取成功", storeAfterSalesService.getPageList(pageDto, queryWrapper));
    }

    @ApiOperation("删除售后记录")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeAfterSalesService.removeById(id);

        return R.ok("操作成功");
    }
}
