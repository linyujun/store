package com.kinzhan.dev.platform.controller.mobile;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.product.CartDto;
import com.kinzhan.dev.platform.beans.entity.StoreCartEntity;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.service.StoreCartService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-19
*/
@RequiredArgsConstructor
@Api(tags = "移动端-商城-购物车管理")
@RestController
@RequestMapping("/app/store/cart")
public class MStoreCartController {

    private final StoreCartService storeCartService;

    @PostMapping
    @ApiOperation("加入购物车")
    public Object add(@Validated @RequestBody CartDto dto){
        StoreCartEntity entity = storeCartService.add(dto);

        return R.ok("添加成功", entity);
    }

    @ApiOperation("批量查找购物车")
    @PostMapping("/ids")
    public Object findIds(@RequestBody List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new RuntimeException("未选择任何商品");
        }

        return R.ok("获取成功", storeCartService.listByIds(ids));
    }

    @ApiOperation("查询购物车")
    @GetMapping("/getAll")
    public Object getAll() {
        List<StoreCartEntity> resultList = storeCartService.lambdaQuery()
                .eq(StoreCartEntity::getUid, LoggedUser.get().getUserId())
                .eq(StoreCartEntity::getIsHidden, false)
                .list();

        return R.ok("获取成功", resultList);
    }


    @ApiOperation("分页查询商城购物车")
    @PostMapping("/getPageList")
    public Object findPage(@RequestBody QueryDto pageDto) {
        QueryWrapper<StoreCartEntity> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(StoreCartEntity::getIsHidden, false);

        return R.ok("获取成功", storeCartService.pageList(pageDto, queryWrapper));
    }

    @ApiOperation("删除一个购物车")
    @GetMapping("/delete/{id}")
    public Object deleteOne(@PathVariable("id") Integer id) {
        storeCartService.forceDelete(id);

        return R.ok("操作成功");
    }

    @ApiOperation("批量删除购物车")
    @DeleteMapping
    public Object deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            storeCartService.forceDelete(id);
        });

        return R.ok("操作成功");
    }

    @ApiOperation("清空购物车")
    @DeleteMapping("/clear")
    public Object clear() {
        storeCartService.forceDeleteByUserId(LoggedUser.get().getUserId());

        return R.ok("操作成功");
    }
}
