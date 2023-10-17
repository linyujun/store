/**
 * Copyright (C) 2018-2022
 */
package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.sys.SysGenConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GenConfigMapper extends BaseMapper<SysGenConfig> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("select count(*) > 0 from kz_sys_gen_config where table_name = #{name}")
    Boolean findByTableName(String name);
}
