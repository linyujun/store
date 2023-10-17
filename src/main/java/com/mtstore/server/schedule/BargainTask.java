package com.mtstore.server.schedule;

import com.mtstore.server.service.StoreBargainLogService;
import com.mtstore.server.service.StoreBargainService;
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
