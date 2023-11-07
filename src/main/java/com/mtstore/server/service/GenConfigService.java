/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.entity.sys.SysGenConfig;

/**
 * @author Zheng Jie
 *  代码生成配置
 */
public interface GenConfigService extends IService<SysGenConfig> {

    /**
     * 查询表配置
     * @param tableName 表名
     * @return 表配置
     */
    SysGenConfig find(String tableName);

    /**
     * 更新表配置
     * @param tableName 表名
     * @param genConfig 表配置
     * @return 表配置
     */
    SysGenConfig update(String tableName, SysGenConfig genConfig);
}
