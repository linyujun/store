package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.SysUserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 充值记录表
 * </p>
 *
 * @author songsir
 * @since 2023-02-07
 */
@Getter
@Setter
@TableName(value = "kz_agent_bill", autoResultMap = true)
@ApiModel(value = "AgentBillEntity对象", description = "充值记录表")
@Accessors(chain = true)
public class AgentBillEntity extends BaseEntity {

    @ApiModelProperty("账单原由，明细等等")
    private String billDesc;

    @ApiModelProperty("充值方式，管理员，现金，在线-支付宝 ，在线-微信")
    private String billSource;

    @ApiModelProperty("付费类型")
    private String payType;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endTime;

    @ApiModelProperty("备用字段")
    private String attr;

    @TableField(exist = false)
    @OneToOne(from = "createUser", to = "createBy", clazz = SysUserMapper.class, method = "selectByCreateUser")
    private String createBy;
}
