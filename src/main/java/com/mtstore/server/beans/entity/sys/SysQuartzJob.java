/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* @author hupeng
* 定时任务
*/

@Data
@TableName("kz_sys_quartz_job")
public class SysQuartzJob extends SysBaseEntity{

    public static final String JOB_KEY = "JOB_KEY";

    /** Spring Bean名称 */
    private String beanName;


    /** cron 表达式 */
    private String cronExpression;


    /** 状态：1暂停、0启用 */
    private Boolean isPause;


    /** 任务名称 */
    private String jobName;


    /** 方法名称 */
    private String methodName;


    /** 参数 */
    private String params;


    /** 备注 */
    private String remark;
}
