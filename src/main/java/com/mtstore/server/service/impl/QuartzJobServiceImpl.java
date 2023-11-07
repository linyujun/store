/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 */
package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.sys.SysQuartzJob;
import com.mtstore.server.beans.mapper.QuartzJobMapper;
import com.mtstore.server.config.quartz.QuartzManage;
import com.mtstore.server.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hupeng
 * 定时任务
 */
@Service
@RequiredArgsConstructor
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, SysQuartzJob> implements QuartzJobService {

    private final QuartzManage quartzManage;

    /**
     * 更改定时任务状态
     *
     * @param quartzJob /
     */
    @Override
    public void updateIsPause(SysQuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new RuntimeException("该任务不可操作");
        }
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        this.saveOrUpdate(quartzJob);
    }

    @Override
    public boolean save(SysQuartzJob quartzJob) {
        quartzManage.addJob(quartzJob);
        return retBool(baseMapper.insert(quartzJob));
    }

    @Override
    public boolean updateById(SysQuartzJob quartzJob) {
        quartzManage.updateJobCron(quartzJob);
        return retBool(baseMapper.updateById(quartzJob));
    }

    /**
     * 立即执行定时任务
     *
     * @param quartzJob /
     */
    @Override
    public void execution(SysQuartzJob quartzJob) {
        if (quartzJob.getId().equals(1L)) {
            throw new RuntimeException("该任务不可操作");
        }
        quartzManage.runJobNow(quartzJob);
    }

    /**
     * 查询启用的任务
     *
     * @return List
     */
    @Override
    public List<SysQuartzJob> findByIsPauseIsFalse() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_pause", 0);

        return list(queryWrapper);
    }

    @Override
    public void removeByIds(List<Integer> idList) {
        idList.forEach(id -> {
            SysQuartzJob quartzJob = baseMapper.selectById(id);
            quartzManage.deleteJob(quartzJob);
        });
        baseMapper.deleteBatchIds(idList);
    }
}
