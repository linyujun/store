/**
 * Copyright (C) 2018-2022
 */
package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 代码生成配置
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@TableName("kz_sys_gen_config")
public class SysGenConfig {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**表明**/
    private String tableName;

    /** 接口名称 **/
    private String apiAlias;

    /** 包路径 */
    private String pack;

    /** 模块名 */
    private String moduleName;

    /** 前端文件路径 */
    private String path;

    /** 前端文件路径 */
    private String apiPath;

    /** 作者 */
    private String author;

    /** 表前缀 */
    private String prefix;

    /** 是否覆盖 */
    private Boolean cover;

    private String pageType;

    private Boolean superClass;
}
