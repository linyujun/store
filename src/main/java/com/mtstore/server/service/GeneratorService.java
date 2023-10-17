/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.dto.filter.PageDto;
import com.mtstore.server.beans.entity.sys.SysColumnConfig;
import com.mtstore.server.beans.entity.sys.SysGenConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface GeneratorService {

    Page getPageList(PageDto pageDto, QueryWrapper wrapper);
    /**
     * 得到数据表的元数据
     * @param name 表名
     * @return /
     */
    List<SysColumnConfig> getColumns(String name);

    /**
     * 同步表数据
     * @param columnInfos /
     * @param columnInfoList
     */
    @Async
    void sync(List<SysColumnConfig> columnInfos, List<SysColumnConfig> columnInfoList);

    /**
     * 保持数据
     * @param columnInfos /
     */
    void save(List<SysColumnConfig> columnInfos);

    /**
     * 获取所有table
     * @return /
     */
    Object getTables();

    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param columns 字段信息
     */
    void generator(SysGenConfig genConfig, List<SysColumnConfig> columns);

    /**
     * 预览
     * @param genConfig 配置信息
     * @param columns 字段信息
     * @return /
     */
    ResponseEntity<Object> preview(SysGenConfig genConfig, List<SysColumnConfig> columns);

    /**
     * 打包下载
     * @param genConfig 配置信息
     * @param columns 字段信息
     * @param request /
     * @param response /
     */
    void download(SysGenConfig genConfig, List<SysColumnConfig> columns, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询数据库的表字段数据数据
     * @param table /
     * @return /
     */
    List<SysColumnConfig> query(String table);


}
