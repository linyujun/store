/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.beans.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kinzhan.dev.platform.beans.mapper.GenConfigMapper;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 表的数据信息
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {

    /** 表名称 */
    private String tableName;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /** 数据库引擎 */
    private String engine;

    /** 编码集 */
    private String coding;

    /** 备注 */
    private String remark;

    @OneToOne(from = "tableName", to = "isReady", clazz = GenConfigMapper.class, method = "findByTableName")
    @TableField(exist = false)
    private Boolean isReady;


}
