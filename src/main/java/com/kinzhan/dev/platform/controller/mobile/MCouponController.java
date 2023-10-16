package com.kinzhan.dev.platform.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kinzhan.dev.platform.beans.common.R;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.filter.QueryDto;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponDto;
import com.kinzhan.dev.platform.beans.dto.mall.coupon.CouponQueryDto;
import com.kinzhan.dev.platform.beans.dto.user.UserCouponQueryDto;
import com.kinzhan.dev.platform.beans.entity.CouponEntity;
import com.kinzhan.dev.platform.beans.entity.UserCouponEntity;
import com.kinzhan.dev.platform.service.CouponService;
import com.kinzhan.dev.platform.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
* @date 2023-05-06
*/
@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-优惠券模块")
@RequestMapping("/app/store/coupon")
public class MCouponController {

    private final CouponService couponService;

    private final UserCouponService userCouponService;

    @GetMapping("{id}")
    @ApiOperation(value = "获取优惠券详情")
    public Object getOne(@PathVariable("id") Integer id){
        CouponEntity entity = couponService.getDetail(id);

        return R.ok("获取成功", entity);
    }


    @PostMapping("/mine/getPageList")
    @ApiOperation(value = "我的优惠券分页")
    public Object findMinePage(@RequestBody QueryDto<UserCouponQueryDto> queryDto) {
        QueryWrapper<UserCouponEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserCouponEntity::getUid, LoggedUser.get().getUserId());

        return R.ok("获取成功", userCouponService.pageList(queryDto, queryWrapper));
    }

    @GetMapping("/mine/all")
    @ApiOperation(value = "我的全部优惠券")
    public Object findMineAllList() {
        QueryWrapper<UserCouponEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserCouponEntity::getUid, LoggedUser.get().getUserId());

        return R.ok("获取成功", userCouponService.list(queryWrapper));
    }

    @GetMapping("/issue/{couponId}")
    @ApiOperation(value = "领取优惠券")
    public Object issue(@PathVariable("couponId") Integer couponId) {
        couponService.issue(couponId, LoggedUser.get().getUserId());

        return R.ok("领取成功");
    }
}
