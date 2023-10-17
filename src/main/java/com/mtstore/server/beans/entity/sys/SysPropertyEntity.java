package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author ww
 * @date 2021/6/9
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "kz_sys_property")
public class SysPropertyEntity extends SysBaseEntity {
    private String groups;
    private Integer parentId;
    private String label;
    private String pk;
    private String k;
    private String v;
    private String type;
    private Boolean isHidden;
    private Boolean isSync;
    private Boolean multiple;
    private Boolean isPublic;
    private Integer sort;
}
