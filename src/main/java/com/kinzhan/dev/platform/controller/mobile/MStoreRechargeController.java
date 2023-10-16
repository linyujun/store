package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.entity.StoreRechargeEntity;
import com.kinzhan.dev.platform.service.StoreRechargeService;
import com.kinzhan.dev.platform.service.SysPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* @author songsir
* @date 2023-05-26
*/
@Api(tags = "移动端-充值")
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store/recharge")
public class MStoreRechargeController {

    private final StoreRechargeService storeRechargeService;
    private final SysPropertyService propertyService;

    @ApiOperation(value = "获取全部充值方案", notes = "")
    @GetMapping("/getAll")
    public Object getAll() {
        QueryWrapper<StoreRechargeEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(StoreRechargeEntity::getSort);
        wrapper.lambda().eq(StoreRechargeEntity::getEnabled, true);

        return R.ok("获取成功", storeRechargeService.list(wrapper));
    }

    @ApiOperation(value = "获取充值说明", notes = "")
    @GetMapping("/config")
    public Object getConfig() {

        return R.ok("获取成功", propertyService.getValue("rechargeDesc"));
    }
}
