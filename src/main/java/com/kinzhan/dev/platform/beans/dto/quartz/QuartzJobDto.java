/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.beans.dto.quartz;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
public class QuartzJobDto extends BaseDto {
    /** Bean名称 */
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
