package com.mtstore.server.controller.mobile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtstore.server.beans.common.R;
import com.mtstore.server.beans.dto.filter.QueryDto;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.dto.user.UserCouponQueryDto;
import com.mtstore.server.beans.entity.CouponEntity;
import com.mtstore.server.beans.entity.UserCouponEntity;
import com.mtstore.server.service.CouponService;
import com.mtstore.server.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "移动端-商城-优惠券模块")
@RequestMapping("/app/store/coupon")
@Slf4j
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
        log.info(LoggedUser.get().getUserId() + " 领取优惠券：" + couponId);
        return R.ok("领取成功");
    }
}
