package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author ww
 * @date 2021/7/22
 **/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "kz_sys_operate_log")
public class SysOperationLogEntity extends SysBaseEntity {
    String ipAddress;
    String type;
    String description;
    String result;
    String module;
}
