package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author ww
 * 系统属性
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "kz_sys_property")
public class SysPropertyEntity extends SysBaseEntity {
    private String mgroups;
    private Integer parentId;
    private String label;
    private String pk;
    private String k;
    private String v;
    private String mtype;
    private Boolean isHidden;
    private Boolean isSync;
    private Boolean multiple;
    private Boolean isPublic;
    private Integer sort;
}
