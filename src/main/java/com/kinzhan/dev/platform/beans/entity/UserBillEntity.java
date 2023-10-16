package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 充值记录表
 * </p>
 *
 * @author songsir
 * @since 2022-10-12
 */
@Getter
@Setter
@TableName("kz_user_bill")
@ApiModel(value = "UserBillEntity对象", description = "充值记录表")
public class UserBillEntity extends BaseEntity {
    @ApiModelProperty("用户id")
    private Integer uid;

    @ApiModelProperty("类型,积分，余额")
    private String category;

    @ApiModelProperty("操作类型，签到，邀请用户，购买商品")
    private String action;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("总额")
    private BigDecimal totalAmount;

    @ApiModelProperty("增加 -，或者减少 +")
    private String direction;

    @ApiModelProperty("账单原由，明细等等")
    private String title;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("备用字段")
    private String attr;
}
