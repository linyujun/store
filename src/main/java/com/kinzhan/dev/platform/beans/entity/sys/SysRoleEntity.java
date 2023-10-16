package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kinzhan.dev.platform.beans.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author ww
 * @date 2021/6/9
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
