/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.PlatformApplication;
import com.mtstore.server.beans.entity.sys.SysGenConfig;
import com.mtstore.server.beans.mapper.ColumnInfoMapper;
import com.mtstore.server.beans.mapper.GenConfigMapper;
import com.mtstore.server.beans.vo.TableInfo;
import com.mtstore.server.service.GenConfigService;
import com.mtstore.server.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author songsir
 * 代码生成配置
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GenConfigServiceImpl extends ServiceImpl<GenConfigMapper, SysGenConfig> implements GenConfigService {

    final ColumnInfoMapper columnInfoMapper;

    @Override
    public SysGenConfig find(String tableName) {
        SysGenConfig genConfig = this.getOne(new LambdaQueryWrapper<SysGenConfig>().eq(SysGenConfig::getTableName,tableName));

        return Optional.ofNullable(genConfig).orElse(getInitialGenConfig(tableName));
    }

    private SysGenConfig getInitialGenConfig(String tableName) {
        TableInfo tableInfo = columnInfoMapper.selectTableInfo(tableName);
        ArrayList<String> splitList = new ArrayList<>(Arrays.asList(tableName.split("_")));
        String prefix = splitList.get(0).concat("_");
        String packageName = PlatformApplication.class.getName().substring(0, PlatformApplication.class.getName().lastIndexOf('.'));
        String name = tableInfo.getRemark().replace("表","");
        return new SysGenConfig()
                .setPack(packageName)
                .setModuleName("template")
                .setApiAlias(name)
                .setPageType("page")
                .setAuthor("songsir")
                .setSuperClass(true)
                .setPath(splitList.size() <= 1 ? splitList.toString() : StringUtils.join(splitList.subList(1, splitList.size()), "\\"))
                .setPrefix(prefix);
    }

    @Override
    public SysGenConfig update(String tableName, SysGenConfig genConfig) {
        // 如果 api 路径为空，则自动生成路径
        if(StringUtils.isBlank(genConfig.getApiPath())){
            String separator = File.separator;
            String[] paths;
            String symbol = "\\";
            if (symbol.equals(separator)) {
                paths = genConfig.getPath().split("\\\\");
            } else {
                paths = genConfig.getPath().split(File.separator);
            }
            StringBuilder api = new StringBuilder();
            for (String path : paths) {
                api.append(path);
                api.append(separator);
                if ("src".equals(path)) {
                    api.append("api");
                    break;
                }
            }
            genConfig.setApiPath(api.toString());
        }
        this.saveOrUpdate(genConfig);
        return genConfig;
    }
}
