package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.mtstore.server.beans.enums.CouponScopeEnum;
import com.mtstore.server.beans.enums.CouponTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.*;
import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* 商城优惠券对象
*/
@Data
@TableName(value = "kz_store_coupon", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城优惠券对象", description = "商城优惠券表")
public class CouponEntity extends BaseEntity{
    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer shopId;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    private String title;

    /** 优惠券类型，满减，折扣，类目，无门槛等 */
    @ApiModelProperty("优惠券类型，满减，折扣，无门槛等")
    @TableField(typeHandler = EnumTypeHandler.class)
    private CouponTypeEnum couponType;

    @ApiModelProperty("使用场景，全场通用，指定分类，指定商品")
    @TableField(typeHandler = EnumTypeHandler.class)
    private CouponScopeEnum scope;

    /** 总量 */
    @ApiModelProperty("总量")
    private Integer total;

    /** 剩余 */
    @ApiModelProperty("已领取")
    private Integer used;

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
    private Integer categoryId;

    @TableField(exist = false)
    private List<Integer> categoryList;

    @TableField(exist = false)
    private List<Integer> productIds;

    @Version
    private Integer version;
}
