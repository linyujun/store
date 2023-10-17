package com.mtstore.server.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@ApiModel(description = "用户签到状态")
public class CheckInStatusVo {
    @ApiModelProperty(value = "当天是否已签到")
    private Boolean isCheckIn;

    @ApiModelProperty(value = "上次签到时间")
    private LocalDate LastCheckTime;

    @ApiModelProperty(value = "连续签到天数")
    private Integer checkTimes;
}
