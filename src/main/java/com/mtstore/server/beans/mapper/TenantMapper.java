package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtstore.server.beans.entity.sys.SysTenantEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 代理商表 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2022-02-09
 */
public interface TenantMapper extends BaseMapper<SysTenantEntity> {

    @InterceptorIgnore(tenantLine = "true")
    @Cacheable(cacheManager = "cacheManager", cacheNames = "agents", key = "#id")
    @Select("SELECT short_name FROM kz_sys_tenant where id = #{id} limit 1")
    String selectNameById(Integer id);
}
