package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.entity.sys.SysMenuEntity;
import com.kinzhan.dev.platform.beans.entity.sys.SysPermissionEntity;
import com.kinzhan.dev.platform.beans.mapper.SysPermissionMapper;
import com.kinzhan.dev.platform.service.SysMenuService;
import com.kinzhan.dev.platform.service.SysPermissionService;
import com.kinzhan.dev.platform.service.SysRoleService;
import com.kinzhan.dev.platform.util.tree.MenuBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songsir
 * @since 2022-04-02
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
                        .eq(SysMenuEntity::getType, 2)
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
