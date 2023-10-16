/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.dto.quartz.QuartzJobDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysQuartzJob;

import java.util.List;

/**
 * @author hupeng
 * @date 2020-05-13
 */
public interface QuartzJobService  extends IKService<SysQuartzJob, QuartzJobDto> {

    /**
     * 更改定时任务状态
     * @param quartzJob /
     */
    void updateIsPause(SysQuartzJob quartzJob);

    /**
     * 立即执行定时任务
     * @param quartzJob /
     */
    void execution(SysQuartzJob quartzJob);

    /**
     * 查询启用的任务
     * @return List
     */
    List<SysQuartzJob> findByIsPauseIsFalse();


    void removeByIds(List<Integer> idList);
}
