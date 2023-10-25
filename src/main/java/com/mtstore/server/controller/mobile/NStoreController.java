package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.store.StoreQueryDto;
import com.mtstore.server.beans.entity.StoreEntity;
import com.mtstore.server.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/open/store")
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
