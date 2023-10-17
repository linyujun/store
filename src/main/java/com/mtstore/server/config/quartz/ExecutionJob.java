/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.config.quartz;

import com.mtstore.server.beans.entity.sys.SysQuartzJob;
import com.mtstore.server.beans.entity.sys.SysQuartzLog;
import com.mtstore.server.service.QuartzJobService;
import com.mtstore.server.service.QuartzLogService;
import com.mtstore.server.util.SpringContextHolder;
import com.mtstore.server.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author /
 * @date 2019-01-07
 */
@Slf4j
@Async
public class ExecutionJob extends QuartzJobBean {

    @Override
    @SuppressWarnings("unchecked")
    protected void executeInternal(JobExecutionContext context) {
        Boolean isSuccess = true;
        long times = 0;
        SysQuartzJob quartzJob = (SysQuartzJob) context.getMergedJobDataMap().get(SysQuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzLogService quartzLogService = SpringContextHolder.getBean(QuartzLogService.class);
        QuartzJobService quartzJobService = SpringContextHolder.getBean(QuartzJobService.class);
        ExecutorService executorService = SpringContextHolder.getBean(ExecutorService.class);
        SysQuartzLog quartzLog = new SysQuartzLog();
        quartzLog.setJobName(quartzJob.getJobName());
        quartzLog.setJobId(quartzJob.getId());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setMethodName(quartzJob.getMethodName());
        quartzLog.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        quartzLog.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            // 任务状态
        } catch (Exception e) {
            isSuccess = false;
            times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            // 任务状态 0：成功 1：失败
            quartzLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            quartzJob.setIsPause(false);
            //更新状态
            quartzJobService.updateIsPause(quartzJob);
        } finally {
            quartzLog.setIsSuccess(isSuccess);
            if (isSuccess) {
                log.info("执行任务 {} {} 总共耗时：{} 毫秒", quartzJob.getJobName(), "成功", times);
            } else {
                log.info("执行任务 {} {} 总共耗时：{} 毫秒", quartzJob.getJobName(), "失败", times);
            }

            quartzLogService.save(quartzLog);
        }
    }
}
