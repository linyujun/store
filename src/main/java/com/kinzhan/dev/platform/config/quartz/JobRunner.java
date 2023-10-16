/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.config.quartz;

import com.kinzhan.dev.platform.beans.entity.sys.SysQuartzJob;
import com.kinzhan.dev.platform.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hupeng
 * @date 2019-01-07
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobRunner implements ApplicationRunner {

    private final QuartzJobService quartzJobService;

    private final QuartzManage quartzManage;

    /**
     * 项目启动时重新激活启用的定时任务
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        log.info("--------------------注入QUARTZ定时任务---------------------");
        List<SysQuartzJob> quartzJobs = quartzJobService.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzManage::addJob);
    }
}
