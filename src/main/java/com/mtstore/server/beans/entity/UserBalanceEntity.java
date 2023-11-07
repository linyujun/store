package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author songsir
 * 用户账户余
 */
@Getter
@Setter
@TableName("kz_user_balance")
@ApiModel(value = "UserBalanceEntity对象", description = "用户账户余额表")
public class UserBalanceEntity extends BaseEntity{

    @ApiModelProperty("总余额")
    private BigDecimal totalValue;

    @ApiModelProperty("乐观锁")
    private Integer version;
}
