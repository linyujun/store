package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.List;

/**
* @author songsir
* 秒杀会场对象
*/
@Data
@TableName(value = "kz_store_seckill", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-秒杀会场对象", description = "商城-秒杀会场表")
public class StoreSeckillEntity extends BaseEntity{
    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 活动时间 */
    @ApiModelProperty("活动时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "活动时间必填")
    private LocalDate activityTime;

    /** 秒杀开始时间 */
    @ApiModelProperty("秒杀开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 秒杀结束时间 */
    @ApiModelProperty("秒杀结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 规则 */
    @ApiModelProperty("规则")
    private String rules;

    @ApiModelProperty("轮播图")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> imgUrls;

    @ApiModelProperty("选择商品")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> productIds;

    @TableField(exist = false)
    private List<ProductEntity> products;

    @ApiModelProperty("场次")
    @JsonFormat(pattern = "HH:00", timezone = "GMT+8")
    private LocalTime hours;

    /** 状态 */
    @ApiModelProperty("状态")
    private ActivityStatusEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;
}
