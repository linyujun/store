package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinzhan.dev.platform.beans.entity.sys.SysRoleEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    @Cacheable(cacheManager = "cacheManager", cacheNames = "sysRoles", key = "#id")
    @Select("SELECT role_name FROM kz_sys_role where id = #{id} limit 1")
    String selectNameById(Integer id);
}
