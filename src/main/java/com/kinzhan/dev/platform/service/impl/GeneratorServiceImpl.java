/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysColumnConfig;
import com.kinzhan.dev.platform.beans.entity.sys.SysGenConfig;
import com.kinzhan.dev.platform.beans.mapper.ColumnInfoMapper;
import com.kinzhan.dev.platform.service.GeneratorService;
import com.kinzhan.dev.platform.util.FileUtil;
import com.kinzhan.dev.platform.util.FilterUtil;
import com.kinzhan.dev.platform.util.generator.GenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Service
@SuppressWarnings({"unchecked","all"})
public class GeneratorServiceImpl extends ServiceImpl<ColumnInfoMapper, SysColumnConfig> implements GeneratorService {


    @Override
    public Object getTables() {
        return baseMapper.selectTables();
    }


    @Override
    public Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        Page page = new Page<SysColumnConfig>(pageDto.getPage(),pageDto.getSize());

        return baseMapper.getPage(page, wrapper);
    }


    @Override
    public List<SysColumnConfig> getColumns(String tableName) {
        List<SysColumnConfig> columnInfos = this.list(new LambdaQueryWrapper<SysColumnConfig>()
                .eq(SysColumnConfig::getTableName,tableName).orderByAsc(SysColumnConfig::getId));
        if(CollectionUtil.isEmpty(columnInfos)){
            columnInfos = query(tableName);
            this.saveBatch(columnInfos);
        }

        Optional.ofNullable(columnInfos).ifPresent(todoList -> {
            converter(todoList);
        });

        return columnInfos;
    }

    /**
     * 加工字段信息
     * @param todoList
     */
    public void converter(List<SysColumnConfig> todoList) {
        HashMap<String, String> formMap = new HashMap<String, String>() {{
            put("varchar", "input");
            put("int", "number");
            put("tinyint", "switch");
            put("datetime", "datetime");
        }};
        List<String> formHiddenList = new ArrayList<>(Arrays.asList("id", "create_time", "create_user", "update_time", "is_delete", "tenant_id"));;
        List<String> listHiddenList = new ArrayList<>(Arrays.asList("update_time", "is_delete", "tenant_id", "create_user"));;

        todoList.forEach(item -> {
            if (StringUtils.isBlank(item.getFormType())) {
                item.setFormType(formMap.get(item.getColumnType()));
            }
            if (null == item.getListShow()) {
                item.setListShow(!listHiddenList.contains(item.getColumnName()));
            }
            if (null == item.getFormShow()) {
                item.setFormShow(!formHiddenList.contains(item.getColumnName()));
            }
        });
    }

    @Override
    public List<SysColumnConfig> query(String tableName){
        List<SysColumnConfig> columnInfos = new ArrayList<>();
        List<Map<String,Object>> result = baseMapper.queryByTableName(tableName);
        for (Map<String,Object> map : result) {

            columnInfos.add(
                    new SysColumnConfig(
                            tableName,
                            map.get("COLUMN_NAME").toString(),
                            "NO".equals(map.get("IS_NULLABLE").toString()),
                            map.get("DATA_TYPE").toString(),
                            ObjectUtil.isNotNull( map.get("COLUMN_COMMENT")) ?  map.get("COLUMN_COMMENT").toString() : null,
                            ObjectUtil.isNotNull(map.get("COLUMN_KEY")) ? map.get("COLUMN_KEY").toString() : null,
                            ObjectUtil.isNotNull(map.get("EXTRA")) ? map.get("EXTRA").toString() : null)
            );
        }

        return columnInfos;
    }

    @Override
    public void sync(List<SysColumnConfig> columnInfos, List<SysColumnConfig> columnInfoList) {
        // 第一种情况，数据库类字段改变或者新增字段
        for (SysColumnConfig columnInfo : columnInfoList) {
            // 根据字段名称查找
            List<SysColumnConfig> columns = new ArrayList<SysColumnConfig>(columnInfos.stream().filter(c-> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList()));
            // 如果能找到，就修改部分可能被字段
            if(CollectionUtil.isNotEmpty(columns)){
                SysColumnConfig column = columns.get(0);
                column.setColumnType(columnInfo.getColumnType());
                column.setExtra(columnInfo.getExtra());
                column.setNotNull(columnInfo.getNotNull());
                column.setKeyType(columnInfo.getKeyType());
                if(StringUtils.isBlank(column.getRemark())){
                    column.setRemark(columnInfo.getRemark());
                }
                this.saveOrUpdate(column);
            } else {
                // 如果找不到，则保存新字段信息
                this.save(columnInfo);
            }
        }
        // 第二种情况，数据库字段删除了
        for (SysColumnConfig columnInfo : columnInfos) {
            // 根据字段名称查找
            List<SysColumnConfig> columns = new ArrayList<SysColumnConfig>(columnInfoList.stream().filter(c-> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList()));
            // 如果找不到，就代表字段被删除了，则需要删除该字段
            if(CollectionUtil.isEmpty(columns)){
                this.removeById(columnInfo.getId());
            }
        }
    }

    @Override
    public void save(List<SysColumnConfig> columnInfos) {
        this.saveOrUpdateBatch(columnInfos);
    }

    @Override
    public void generator(SysGenConfig genConfig, List<SysColumnConfig> columns) {
        if(null == genConfig || genConfig.getId() == null){
            throw new RuntimeException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columns, genConfig);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("生成失败，请手动处理已生成的文件");
        }
    }

    @Override
    public ResponseEntity<Object> preview(SysGenConfig genConfig, List<SysColumnConfig> columns) {
        if(null == genConfig || genConfig.getId() == null){
            throw new RuntimeException("请先配置生成器");
        }
        List<Map<String,Object>> genList =  GenUtil.preview(columns, genConfig);

        return new ResponseEntity<>(genList, HttpStatus.OK);
    }

    @Override
    public void download(SysGenConfig genConfig, List<SysColumnConfig> columns, HttpServletRequest request, HttpServletResponse response) {
        if(null == genConfig || genConfig.getId() == null){
            throw new RuntimeException("请先配置生成器");
        }
        try {
            File file = new File(GenUtil.download(columns, genConfig));
            String zipPath = file.getPath()  + ".zip";
            ZipUtil.zip(file.getPath(), zipPath);
            FileUtil.downloadFile(request, response, new File(zipPath), true);
        } catch (IOException e) {
            throw new RuntimeException("打包失败");
        }
    }
}
