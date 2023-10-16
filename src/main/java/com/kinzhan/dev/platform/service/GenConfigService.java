/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.entity.sys.SysGenConfig;

/**
 * @author Zheng Jie
 * @date 2019-01-14
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
