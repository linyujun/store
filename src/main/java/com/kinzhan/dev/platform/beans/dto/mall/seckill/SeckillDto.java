package com.kinzhan.dev.platform.beans.dto.mall.seckill;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.*;
import java.util.List;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-05-23
*/
@Data
@ApiModel(value = "秒杀活动")
public class SeckillDto extends BaseDto{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    @NotBlank(message = "活动名称必填")
    private String activityName;

    /** 活动时间 */
    @ApiModelProperty("活动时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "活动时间必填")
    private LocalDate activityTime;

    @ApiModelProperty("轮播图")
    private List<String> imgUrls;

    @ApiModelProperty("整点")
    @NotNull(message = "场次必填必填")
    private LocalTime hours;

    @ApiModelProperty("商品")
    @NotNull(message = "商品必填必填")
    private List<Integer> productIds;

    /** 规则 */
    @ApiModelProperty("规则")
    @NotNull(message = "规则必填")
    private String rules;
}
