package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.entity.BaseEntity;
import com.mtstore.server.beans.mapper.SysRoleMapper;
import com.mtstore.server.beans.mapper.TenantMapper;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * @author ww
 * 管理系统用户
 **/
@TableName(value = "kz_sys_user", autoResultMap=true)
@Data
@SuperBuilder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysUserEntity extends BaseEntity {
    private String userName;

    private String nickName;

    private Integer roleId;

    @JsonIgnore
    private String password;

    private String email;

    private String phone;

    private String gender;

    private String avatarUrl;

    private String openId;

    @TableField(exist = false)
    private SysRoleEntity role;

    @OneToOne(from = "roleId", to = "roleName", clazz = SysRoleMapper.class, method = "selectNameById")
    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private List<String> roles;

    @TableField(exist = false)
    private List<String> permissions;

    private Integer storeId;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> storeIds;

    private String dataScope;

    @OneToOne(from = "tenantId", to = "agentName", clazz = TenantMapper.class, method = "selectNameById")
    @TableField(exist = false)
    private String agentName;
}
