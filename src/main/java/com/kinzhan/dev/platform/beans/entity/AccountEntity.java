package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收款账户表
 * </p>
 *
 * @author songsir
 * @since 2022-04-05
 */
@Getter
@Setter
@TableName("kz_account")
@ApiModel(value = "AccountEntity对象", description = "收款账户表")
public class AccountEntity extends BaseEntity{

    @ApiModelProperty("账户")
    private String account;

    @ApiModelProperty("账户类型，如微信，支付宝，银行卡")
    private String accountType;

    private String trueName;

    private String bankCode;

    @ApiModelProperty("收款二维码")
    private String accountUrl;
}
