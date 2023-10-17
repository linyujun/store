package com.mtstore.server.beans.dto.mall.combination;

import lombok.Data;
import java.time.*;
import java.math.BigDecimal;

import com.mtstore.server.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-02
*/
@Data
public class StoreCombinationLogDto extends BaseDto{
    /** 团id */
    @ApiModelProperty("团id")
    @NotNull
    private Integer groupId;

    /** 拼团活动id */
    @ApiModelProperty("拼团活动id")
    @NotNull
    private Integer combinationId;

    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 商品 */
    @ApiModelProperty("商品")
    @NotNull
    private Integer productId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 商品图片 */
    @ApiModelProperty("商品图片")
    private String imgUrl;

    /** 原价 */
    @ApiModelProperty("原价")
    @NotNull
    private BigDecimal price;

    /** 拼团加 */
    @ApiModelProperty("拼团价")
    @NotNull
    private BigDecimal combinationPrice;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    /** 数量限制 */
    @ApiModelProperty("数量限制")
    private Integer limitNum;

    /** 成团人数 */
    @ApiModelProperty("成团人数")
    private Integer groupNum;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    private LocalDateTime expiredTime;

    /** 虚拟成团 */
    @ApiModelProperty("虚拟成团")
    private Boolean isVirtual;

    /** 团长 */
    @ApiModelProperty("团长")
    private Boolean isLeader;

    /** 用户 */
    @ApiModelProperty("用户")
    @NotNull
    private Integer uid;

    /** 是否成功 */
    @ApiModelProperty("是否成功")
    private Boolean isSuccess;

}
