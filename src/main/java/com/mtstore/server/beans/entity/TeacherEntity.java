package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.SysUserMapper;
import com.mtstore.server.beans.mapper.TenantMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-04-11
 */
@Getter
@Setter
@TableName(value = "kz_teacher", autoResultMap = true)
@ApiModel(value = "TeacherEntity对象", description = "")
public class TeacherEntity extends BaseEntity{

    private String uuid;

    @ApiModelProperty("老师姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("老师头像")
    private String avatarUrl;

    @ApiModelProperty("老师介绍")
    private String description;

    @TableField(exist = false)
    private List<Integer> storeIds;

    private Integer uid;

    @TableField(exist = false)
    @OneToOne(from = "createUser", to = "createBy", clazz = SysUserMapper.class, method = "selectByCreateUser")
    private String createBy;

    @TableField(exist = false)
    @OneToOne(from = "tenantId", to = "agentName", clazz = TenantMapper.class, method = "selectNameById")
    private String agentName;
}
