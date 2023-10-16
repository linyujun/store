package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.kinzhan.dev.platform.beans.enums.ActivityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.List;

/**
* @author songsir
* @date 2023-06-07
*/
@Data
@TableName(value = "kz_store_bargain", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-砍价对象", description = "商城-砍价表")
public class StoreBargainEntity extends BaseEntity{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 每次砍价区间 */
    @ApiModelProperty("每次砍价区间")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<BigDecimal> priceRange;

    /** 商品列表 */
    @ApiModelProperty("商品列表")
    @TableField(typeHandler = FastjsonTypeHandler.class)
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
    private ActivityStatusEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
