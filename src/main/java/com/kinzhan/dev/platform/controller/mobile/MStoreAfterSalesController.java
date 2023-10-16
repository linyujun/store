package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreAfterSalesDto;
import com.kinzhan.dev.platform.beans.dto.mall.aftersales.StoreReturnDto;
import com.kinzhan.dev.platform.beans.entity.StoreAfterSalesEntity;
import com.kinzhan.dev.platform.service.StoreAfterSalesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
* @author songsir
* @date 2023-06-12
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store/afterSales")
@Api(tags = "移动端-售后")
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
