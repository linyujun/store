package com.mtstore.server.beans.dto.mall.coupon;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtstore.server.beans.enums.CouponScopeEnum;
import com.mtstore.server.beans.enums.CouponTypeEnum;
import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author songsir
* @date 2023-05-06
*/
@Data
public class CouponDto extends BaseDto{
    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer shopId;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    @NotBlank(message = "优惠券名称必填")
    private String title;

    /** 优惠券类型，满减，折扣，类目，无门槛等 */
    @ApiModelProperty("优惠券类型，满减，折扣，类目，无门槛等")
    @NotNull(message = "优惠券类型必填")
    private CouponTypeEnum couponType;

    @ApiModelProperty("使用场景，全场通用，指定分类，指定商品")
    private CouponScopeEnum scope;

    /** 总量 */
    @ApiModelProperty("总量")
    @NotNull(message = "总量必填")
    private Integer total;

    /** 优惠券面值 */
    @ApiModelProperty("优惠券面值")
    private BigDecimal price;

    /** 最低消费 */
    @ApiModelProperty("最低消费")
    private BigDecimal minPrice;

    /** 折扣，请填入小数 */
    @ApiModelProperty("折扣，请填入小数")
    private BigDecimal discount;

    /** 最大持有量 */
    @ApiModelProperty("最大持有量")
    private Integer holdLimit;

    /** 开始领取时间 */
    @ApiModelProperty("开始领取时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 结束领取时间 */
    @ApiModelProperty("结束领取时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 可用时间 */
    @ApiModelProperty("可用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useStartTime;

    /** 可用结束时间 */
    @ApiModelProperty("可用结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useEndTime;

    /** 领取后过期时间 */
    @ApiModelProperty("领取后过期时间")
    private Integer expiredDays;

    /** 是否开放领取，1,0 */
    @ApiModelProperty("是否开放领取，1,0")
    private Boolean isShow;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("优惠券使用说明")
    private String description;

    @TableField(exist = false)
    private List<Integer> productIds;

    @TableField(exist = false)
    private Integer categoryId;

    @TableField(exist = false)
    private List<Integer> categoryList;
}
