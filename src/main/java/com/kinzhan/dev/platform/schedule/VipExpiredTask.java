package com.kinzhan.dev.platform.schedule;

import com.kinzhan.dev.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VipExpiredTask {

    final UserService userService;

    /**
     * 检查秒杀活动状态
     */
    public void run() {
        log.info("------------------------检查用户VIP是否到期-------------------------");
        userService.closeVip();
    }
}
