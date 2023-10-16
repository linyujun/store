package com.kinzhan.dev.platform.beans.dto.mall.bargain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-07
*/
@Data
public class StoreBargainDto extends BaseDto{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 每次砍价区间 */
    @ApiModelProperty("每次砍价区间")
    private List<BigDecimal> priceRange;

    /** 商品列表 */
    @ApiModelProperty("商品列表")
    private List<Integer> productIds;

    /** 分享标题 */
    @ApiModelProperty("分享标题")
    private String shareTitle;

    /** 活动开始时间 */
    @ApiModelProperty("活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 持续时间（分钟） */
    @ApiModelProperty("持续时间（分钟）")
    private Integer expireMinute;

    /** 活动规则 */
    @ApiModelProperty("活动规则")
    private String rules;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
