package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mtstore.server.beans.enums.CouponTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.*;
import java.math.BigDecimal;

/**
* @author songsir
* @date 2023-05-08
*/
@Data
@TableName(value = "kz_user_coupon", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户优惠券对象", description = "用户优惠券表")
public class UserCouponEntity extends BaseEntity{
    /** 唯一识别码 */
    @ApiModelProperty("唯一识别码")
    private String uuid;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    private String title;

    /** 用户id */
    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 优惠券类型，满减，折扣，类目，无门槛等 */
    @ApiModelProperty("优惠券类型，满减，折扣，类目，无门槛等")
    @TableField(typeHandler = EnumTypeHandler.class)
    private CouponTypeEnum couponType;

    /** 获取方式，领取，发放 */
    @ApiModelProperty("获取方式，领取->user，发放->system")
    private String obtainType;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer shopId;

    /** 优惠券面值 */
    @ApiModelProperty("优惠券面值")
    private BigDecimal price;

    /** 最低消费 */
    @ApiModelProperty("最低消费")
    private BigDecimal minPrice;

    /** 折扣 */
    @ApiModelProperty("折扣")
    private BigDecimal discount;

    /** 可用时间 */
    @ApiModelProperty("可用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useStartTime;

    /** 可用结束时间 */
    @ApiModelProperty("可用结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useEndTime;

    @ApiModelProperty("使用时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime useTime;

    /** 使用状态 */
    @ApiModelProperty("使用状态")
    private Integer status;

    /** 使用状态：0->未使用；1->已使用；2->已过期 */
    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    private String statusDesc;
}
