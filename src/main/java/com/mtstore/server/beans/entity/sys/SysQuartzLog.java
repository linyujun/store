/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-13
*/

@Data
@TableName("kz_sys_quartz_log")
public class SysQuartzLog extends SysBaseEntity{
    /** 任务名称 */
    private String beanName;

    private Integer jobId;

    /** cron表达式 */
    private String cronExpression;


    /** 异常详细  */
    private String exceptionDetail;


    /** 状态 */
    private Boolean isSuccess;


    /** 任务名称 */
    private String jobName;


    /** 方法名称 */
    private String methodName;


    /** 参数 */
    private String params;


    /** 耗时（毫秒） */
    private Long time;
}
