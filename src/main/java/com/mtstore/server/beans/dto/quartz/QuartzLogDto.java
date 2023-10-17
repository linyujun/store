package com.mtstore.server.beans.dto.quartz;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

/**
* @author hupeng
* @date 2020-05-13
*/
@Data
public class QuartzLogDto extends BaseDto {

    /** 定时任务名称 */
    private String beanName;

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
