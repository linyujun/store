/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.PlatformApplication;
import com.kinzhan.dev.platform.beans.entity.sys.SysGenConfig;
import com.kinzhan.dev.platform.beans.mapper.ColumnInfoMapper;
import com.kinzhan.dev.platform.beans.mapper.GenConfigMapper;
import com.kinzhan.dev.platform.beans.vo.TableInfo;
import com.kinzhan.dev.platform.service.GenConfigService;
import com.kinzhan.dev.platform.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author songsir
 * @date 2019-01-14
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
