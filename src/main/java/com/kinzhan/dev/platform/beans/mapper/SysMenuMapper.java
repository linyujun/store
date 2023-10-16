package com.kinzhan.dev.platform.beans.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinzhan.dev.platform.beans.entity.sys.SysMenuEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT * FROM `kz_sys_menu` WHERE id in " +
            "(select menu_id from kz_sys_permission WHERE role_id = " +
            "(select id FROM kz_sys_role WHERE role_name = #{role})) AND enabled = 1 AND is_delete = 0 order by sort asc")
    List<SysMenuEntity> findAllByRole(String role);


    @InterceptorIgnore(tenantLine = "true")
    @Select("SELECT * FROM `kz_sys_menu` WHERE id in " +
            "(select menu_id from kz_sys_permission WHERE role_id = " +
            "(select role_id FROM kz_sys_user WHERE id = #{userId})) AND enabled = 1 AND is_delete = 0 order by sort asc")
    List<SysMenuEntity> findAllByUserId(Integer userId);
}
