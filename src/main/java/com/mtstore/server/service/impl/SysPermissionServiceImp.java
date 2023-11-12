package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.dto.logged.LoggedUser;
import com.mtstore.server.beans.entity.sys.SysMenuEntity;
import com.mtstore.server.beans.entity.sys.SysPermissionEntity;
import com.mtstore.server.beans.mapper.SysPermissionMapper;
import com.mtstore.server.service.SysMenuService;
import com.mtstore.server.service.SysPermissionService;
import com.mtstore.server.service.SysRoleService;
import com.mtstore.server.util.tree.MenuBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author songsir
 * 系统角色菜单权限
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImp extends ServiceImpl<SysPermissionMapper, SysPermissionEntity> implements SysPermissionService {

    final SysMenuService sysMenuService;

    final SysRoleService sysRoleService;

    @Override
    public List findMenuByCurrentUser() {
        List<SysMenuEntity> menuList = sysMenuService.findAllByUserId(LoggedUser.get().getUserId());
        List<MenuBuilder.Node> nodes = new ArrayList();
        menuList.forEach(entity ->{
            MenuBuilder.Node node = new MenuBuilder.Node();
            MenuBuilder.Meta meta = new MenuBuilder.Meta();
            BeanUtils.copyProperties(entity, meta);
            node.setMeta(meta);
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        List<MenuBuilder.Node> result = new MenuBuilder().buildTree(nodes);

        return result;
    }


    @Override
    public List<String> findPermissionListByRoleId(Integer roleId) {
        List<SysPermissionEntity> permissionList = lambdaQuery().eq(SysPermissionEntity::getRoleId, roleId).list();
        if (null != permissionList) {
            List<Integer> menuIds = permissionList.stream().map(v -> v.getMenuId()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(menuIds)) {
                List<SysMenuEntity> menuList = sysMenuService.lambdaQuery()
                        .in(SysMenuEntity::getId, menuIds)
                        .eq(SysMenuEntity::getMtype, 2)
                        .list();
                if (null != menuList) {
                    return menuList
                            .stream()
                            .map(v->v.getPermission())
                            .collect(Collectors.toList());
                }
            }
        }

        return null;
    }
}
