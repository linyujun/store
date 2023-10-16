package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Insert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 提现分润记录表
 * </p>
 *
 * @author songsir
 * @since 2022-09-20
 */
@Getter
@Setter
@TableName("kz_withdraw")
@ApiModel(value = "WithdrawEntity对象", description = "提现分润记录表")
public class WithdrawEntity extends BaseEntity {

    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    @ApiModelProperty("提现到的账户")
    private String qrcodeUrl;

    @ApiModelProperty("提现金额")
    private BigDecimal totalPrice;

    @ApiModelProperty("打款金额")
    private BigDecimal payPrice;

    @ApiModelProperty("提现申请状态 0 已提交 1 驳回 2 通过待打款 3已打款，完成")
    private Integer status;

    @ApiModelProperty("提现状态")
    private String statusDesc;

    @ApiModelProperty("提现拒绝原因")
    private String statusReason;

    @ApiModelProperty("备注信息")
    private String description;

    @ApiModelProperty("打款订单id")
    private String orderId;

    @ApiModelProperty("打款时间")
    private LocalDateTime orderTime;

    @ApiModelProperty("支付截图")
    private String orderImgUrl;

    @TableField(exist = false)
    private BigDecimal sumPrice;
}
