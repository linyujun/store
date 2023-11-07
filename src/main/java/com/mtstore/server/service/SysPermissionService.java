package com.mtstore.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtstore.server.beans.dto.permission.PermissionDto;
import com.mtstore.server.beans.entity.sys.SysPermissionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  系统权限
 * </p>
 *
 * @author songsir
 * @since 2022-04-02
 */
public interface SysPermissionService extends IService<SysPermissionEntity> {

    default void savePermissions(PermissionDto dto) {
        ;
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", dto.getRoleId());
        getBaseMapper().delete(queryWrapper);
        List<SysPermissionEntity> permissions = new ArrayList<>();
        dto.getPermissions().forEach(menuId -> {
            SysPermissionEntity entity = SysPermissionEntity.builder()
                    .roleId(dto.getRoleId())
                    .menuId(menuId)
                    .build();
            permissions.add(entity);
        });

        saveBatch(permissions);
    }

    default List<SysPermissionEntity> findAllByRoleId(Integer roleId) {
        return lambdaQuery().eq(SysPermissionEntity::getRoleId, roleId).list();
    }

    default List<Integer> findPermissionByRoleId(Integer roleId) {
        List<SysPermissionEntity> results = findAllByRoleId(roleId);
        if (null != results) {
            return results.stream()
                    .map(SysPermissionEntity::getMenuId)
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 生成当前用户的前端菜单
     * @return
     */
    List findMenuByCurrentUser();

    /**
     * 查找当前角色的按钮权限
     * @param roleId
     * @return
     */
    List<String> findPermissionListByRoleId(Integer roleId);
}
