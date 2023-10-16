package com.kinzhan.dev.platform.schedule;

import com.kinzhan.dev.platform.service.StoreSeckillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeckillTask {

    final StoreSeckillService storeSeckillService;

    /**
     * 检查秒杀活动状态
     */
    public void run() {
        log.info("------------------------检查秒杀活动状态-------------------------");
        storeSeckillService.check();
    }
}
