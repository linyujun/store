/**
 * Copyright (C) 2018-2022
 */
package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mtstore.server.util.generator.GenUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列的数据信息
 */
@Data
@NoArgsConstructor
@TableName("kz_sys_column_config")
public class SysColumnConfig {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String tableName;

    /** 数据库字段名称 */
    private String columnName;

    /** 数据库字段类型 */
    private String columnType;

    /**  数据库字段键类型 */
    private String keyType;

    /** 字段额外的参数 */
    private String extra;

    /** 数据库字段描述 */
    private String remark;

    /** 必填 */
    private Boolean notNull;

    /** 是否在列表显示 */
    private Boolean listShow;

    /** 是否表单显示 */
    private Boolean formShow;

    /** 表单类型 */
    private String formType;

    /** 查询 1:模糊 2：精确 */
    private String queryType;

    /** 字典名称 */
    private String dictName;

    /** 日期注解 */
    private String dateAnnotation;

    public SysColumnConfig(String tableName, String columnName, Boolean notNull, String columnType, String remark, String keyType, String extra) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnType = columnType;
        this.keyType = keyType;
        this.extra = extra;
        this.notNull = notNull;
        if(GenUtil.PK.equalsIgnoreCase(keyType) && GenUtil.EXTRA.equalsIgnoreCase(extra)){
            this.notNull = false;
        }
        this.remark = remark;
    }
}
