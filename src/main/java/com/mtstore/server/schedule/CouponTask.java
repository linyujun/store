package com.mtstore.server.schedule;

import com.mtstore.server.service.CouponService;
import com.mtstore.server.service.UserCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 优惠券处理任务
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CouponTask {

    final CouponService couponService;
    final UserCouponService userCouponService;

    /**
     * 领券中心检测
     */
    public void couponCheck() {
        log.info("------------------------开始检查优惠券过期逻辑-------------------------");
        log.info("------------------------优惠券开放领取检测-------------------------");
        couponService.activateCheck();
        log.info("------------------------优惠券过期检测-------------------------");
        couponService.expiredCheck();
    }

    /**
     * 用户持有的优惠券检测
     */
    public void userCouponCheck() {
        log.info("------------------------开始检查用户优惠券过期逻辑-------------------------");
        userCouponService.expiredCheck();
    }
}
