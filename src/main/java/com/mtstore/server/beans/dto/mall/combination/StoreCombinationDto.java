package com.mtstore.server.beans.dto.mall.combination;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.*;
import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-02
*/
@Data
public class StoreCombinationDto extends BaseDto{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 商品列表 */
    @ApiModelProperty("商品列表")
    private List<Integer> productIds;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 成团人数 */
    @ApiModelProperty("成团人数")
    private Integer groupNum;

    /** 成团有效期（分钟） */
    @ApiModelProperty("成团有效期（分钟）")
    private Integer expireMinute;

    /** 数量限制 */
    @ApiModelProperty("数量限制")
    private Integer limitNum;

    /** 虚拟成团 */
    @ApiModelProperty("虚拟成团")
    private Boolean isVirtual;

    /** 分享标题 */
    @ApiModelProperty("分享标题")
    private String shareTitle;

    /** 规则 */
    @ApiModelProperty("规则")
    private String rules;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
