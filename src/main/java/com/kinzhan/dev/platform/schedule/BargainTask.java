package com.kinzhan.dev.platform.schedule;

import com.kinzhan.dev.platform.service.StoreBargainLogService;
import com.kinzhan.dev.platform.service.StoreBargainService;
import com.kinzhan.dev.platform.service.StoreCombinationLogService;
import com.kinzhan.dev.platform.service.StoreCombinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BargainTask {

    final StoreBargainService bargainService;

    final StoreBargainLogService bargainLogService;

    /**
     * 检查砍价活动状态
     */
    public void run() {
        log.info("------------------------检查砍价活动状态-------------------------");
        bargainService.check();
    }

    /**
     * 砍价活动过期检测
     */
    public void checkLog() {
        bargainLogService.check();
    }
}
