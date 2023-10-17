package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.time.*;
import java.util.List;

/**
* @author songsir
* @date 2023-06-02
*/
@Data
@TableName(value = "kz_store_combination", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-拼团对象", description = "商城-拼团表")
public class StoreCombinationEntity extends BaseEntity{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 商品列表 */
    @ApiModelProperty("商品列表")
    @TableField(typeHandler = FastjsonTypeHandler.class)
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
    @JsonFormat(pattern = "HH:00", timezone = "GMT+8")
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
    private ActivityStatusEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @TableField(exist = false)
    @ApiModelProperty("拼团记录")
    private List<StoreCombinationLogEntity> records;
}
