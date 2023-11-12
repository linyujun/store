package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.mall.coupon.CouponQueryDto;
import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api(tags="移动端-领券中心")
@RequestMapping("/open/coupon")
public class NCouponController {
    private final CouponService couponService;

    @PostMapping("/getPageList")
    @ApiOperation(value = "领券中心分页")
    public Object findPage(@RequestBody QueryDto<CouponQueryDto> queryDto) {
        QueryWrapper<CouponEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CouponEntity::getIsShow, true);
        queryWrapper.lambda().eq(CouponEntity::getStatus, 1);
        queryWrapper.lambda().eq(CouponEntity::getEnabled, true);
        queryWrapper.lambda().eq(CouponEntity::getIsDelete, false);

        return R.ok("获取成功", couponService.pageList(queryDto, queryWrapper));
    }
}
