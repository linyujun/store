package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;
import java.math.BigDecimal;

/**
* @author songsir
* @date 2023-06-19
*/
@Data
@TableName(value = "kz_user_brokerage_bill", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户佣金记录对象", description = "用户佣金记录表")
public class UserBrokerageBillEntity extends BaseEntity{
    /** 操作类型，签到，邀请用户，购买商品 */
    @ApiModelProperty("操作类型，签到，邀请用户，购买商品")
    private String action;

    /** 账单原由，明细等等 */
    @ApiModelProperty("账单原由，明细等等")
    private String title;

    /** 金额 */
    @ApiModelProperty("金额")
    private BigDecimal amount;

    /** 总额 */
    @ApiModelProperty("总额")
    private BigDecimal totalAmount;

    /** 增加 -，或者减少 + */
    @ApiModelProperty("增加 -，或者减少 +")
    private String direction;

    /** 描述信息 */
    @ApiModelProperty("描述信息")
    private String description;

    /** 备用字段 */
    @ApiModelProperty("备用字段")
    private String attr;

    /** 用户id */
    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    @TableField(exist = false)
    private BigDecimal totalPrice;
}
