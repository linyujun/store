package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mtstore.server.beans.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author ww
 * 系统角色
 **/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "kz_sys_role")
public class SysRoleEntity extends BaseEntity {

    @Column(name = "is_system")
    private Boolean isSystem;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "role_name")
    private String roleName;

    private String dataScope;

    @Column(name = "description")
    private String description;
}
