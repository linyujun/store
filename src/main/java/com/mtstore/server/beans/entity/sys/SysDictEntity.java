package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author ww
 * 系统字典
 **/
@Data
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "kz_sys_dict", autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysDictEntity extends SysBaseEntity {
    Integer parentId;

    String dictName;

    String dictValue;

    @Builder.Default
    Integer sort = 0;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    String[] extra;
}
