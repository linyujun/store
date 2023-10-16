package com.kinzhan.dev.platform.beans.dto.user;

import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-08
*/
@Data
public class UserCouponDto extends BaseDto{
    /** 唯一识别码 */
    @ApiModelProperty("唯一识别码")
    private String uuid;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    private String title;

    /** 用户id */
    @ApiModelProperty("用户id")
    private Integer uid;

    /** 优惠券id */
    @ApiModelProperty("优惠券id")
    private Integer couponId;

    /** 优惠券类型，满减，折扣，类目，无门槛等 */
    @ApiModelProperty("优惠券类型，满减，折扣，类目，无门槛等")
    private String couponType;

    /** 获取方式，领取，发放 */
    @ApiModelProperty("获取方式，领取，发放")
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
    private LocalDateTime useStartTime;

    /** 可用结束时间 */
    @ApiModelProperty("可用结束时间")
    private LocalDateTime useEndTime;

    /** 使用状态 */
    @ApiModelProperty("使用状态")
    private Integer status;

    /** 使用状态：0->未使用；1->已使用；2->已过期 */
    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    private String statusDesc;

}
