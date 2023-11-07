package com.mtstore.server.beans.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mtstore.server.beans.mapper.UserLevelMapper;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.TenantMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author songsir
 * 小程序端用户信息
 */
@Getter
@Setter
@TableName(value = "kz_user", autoResultMap = true)
@ApiModel(value = "用户对象", description = "用户表")
public class UserEntity extends BaseEntity {

    private String openId;

    private String uuid;

    private String phone;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("地址位置")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] address;

    private String addressDetail;

    @ApiModelProperty("邀请人id")
    private Integer inviteId;

    @ApiModelProperty("会员id")
    private Integer levelId;

    @TableField(exist = false)
    @OneToOne(from = "levelId", to = "levelInfo", clazz= UserLevelMapper.class, method = "selectById")
    private UserLevelEntity levelInfo;

    @ApiModelProperty("余额")
    private BigDecimal balance;

    @ApiModelProperty("积分")
    private BigDecimal credit;

    @ApiModelProperty("佣金")
    private BigDecimal brokerage;

    @ApiModelProperty("经验值")
    private Long exp;

    @ApiModelProperty("是否付费会员")
    private Boolean isVip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("到期时间")
    public LocalDateTime expiredTime;

    @ApiModelProperty("用户标签")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray tags;

    @TableField(exist = false)
    private String roleName = "GUEST";

    @OneToOne(from = "tenantId", to = "agentName", clazz = TenantMapper.class, method = "selectNameById")
    @TableField(exist = false)
    private String agentName;

    @TableField(exist = false)
    private Boolean isFirstLogin = false;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate lastCheckTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    private Integer checkTimes;

    @TableField(exist = false)
    private Long orderNum;

    @TableField(exist = false)
    private BigDecimal orderPrice;

    @TableField(exist = false)
    private Long inviteNum;

    @TableField(exist = false)
    private Long couponNum = 0L;

    @TableField(exist = false)
    private Long brokerageOrderNum;

    @TableField(exist = false)
    private BigDecimal brokerageOrderPrice;

    @ApiModelProperty("乐观锁")
    @Version
    private Integer version;

    @TableField(exist = false)
    private BigDecimal totalPrice;
}
