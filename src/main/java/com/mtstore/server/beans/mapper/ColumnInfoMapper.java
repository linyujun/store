/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.entity.sys.SysColumnConfig;
import com.mtstore.server.beans.vo.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ColumnInfoMapper extends BaseMapper<SysColumnConfig> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("select table_name ,create_time , engine, table_collation as coding, table_comment as remark from information_schema.tables ${ew.customSqlSegment}")
    Page<TableInfo> getPage(@Param("page") Page page, @Param("ew") Wrapper<TableInfo> wrapper);

    @InterceptorIgnore(tenantLine = "true")
    @Select("<script>select table_name ,create_time , engine, table_collation as coding, table_comment as remark from information_schema.tables " +
            "where table_schema = (select database()) <if test=\"name!=null\"> and table_name like CONCAT('%',#{name},'%') </if> order by create_time desc</script>")
    IPage<TableInfo> selectTablePage(@Param("page") Page page, @Param("name") String name);

    @InterceptorIgnore(tenantLine = "true")
    @Select("<script>select table_name ,create_time , engine, table_collation, table_comment from information_schema.tables " +
            "where table_schema = (select database()) order by create_time desc</script>")
    List<TableInfo> selectTables();

    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT COLUMN_NAME, IS_NULLABLE, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY, EXTRA FROM INFORMATION_SCHEMA.COLUMNS " +
            "WHERE TABLE_NAME = #{name} AND TABLE_SCHEMA = (SELECT DATABASE()) ORDER BY ORDINAL_POSITION")
    List<Map<String,Object>> queryByTableName(@Param("name") String name);


    @InterceptorIgnore(tenantLine = "true")
    @Select("select table_name ,create_time , engine, table_collation as coding, table_comment as remark from information_schema.tables " +
            "where table_schema = (select database()) and TABLE_NAME = #{name}")
    TableInfo selectTableInfo( @Param("name") String name);
}
