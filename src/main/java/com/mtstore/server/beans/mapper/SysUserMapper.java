package com.mtstore.server.beans.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtstore.server.beans.entity.sys.SysUserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author ww
 * @date 2021/7/15
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "role_id", property = "role",
                    one = @One(select = "com.mtstore.server.beans.mapper.SysRoleMapper.selectById")),
    })
    @Select("SELECT * FROM kz_sys_user where user_name = #{name} limit 1")
    SysUserEntity selectByName(@Param("name") String userName);

    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "role_id", property = "role",
                    one = @One(select = "com.mtstore.server.beans.mapper.SysRoleMapper.selectById")),
    })
    @Select("SELECT * FROM kz_sys_user where user_name=#{needle} or phone=#{needle} limit 1")
    SysUserEntity selectByAny(@Param("needle") String needle);

    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "role_id", property = "role",
                    one = @One(select = "com.mtstore.server.beans.mapper.SysRoleMapper.selectById")),
    })
    @Select("SELECT * FROM kz_sys_user where phone = #{phone} limit 1")
    SysUserEntity selectByPhone(@Param("phone") String phone);

    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "role_id", property = "role",
                    one = @One(select = "com.mtstore.server.beans.mapper.SysRoleMapper.selectById")),
    })
    @Select("SELECT * FROM kz_sys_user ${ew.customSqlSegment}")
    Page<SysUserEntity> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Cacheable(cacheManager = "cacheManager", cacheNames = "sysUsers", key = "#id")
    @Select("SELECT nick_name FROM kz_sys_user where id = #{id} limit 1")
    String selectByCreateUser(@Param("id") Integer id);

    @Delete("delete from kz_sys_user where phone = #{phone}")
    void forceDeleteByPhone(String phone);

    @Delete("delete from kz_sys_user where id = #{id}")
    void forceDeleteById(Integer id);

    @Select("SELECT * FROM kz_sys_user where tenant_id = #{tenantId} and role_id = 3 limit 1")
    Integer findUserIdByTenantId(Integer tenantId);
}
