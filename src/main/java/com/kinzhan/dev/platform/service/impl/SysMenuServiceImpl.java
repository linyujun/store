package com.kinzhan.dev.platform.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinzhan.dev.platform.beans.dto.logged.LoggedUser;
import com.kinzhan.dev.platform.beans.entity.sys.SysMenuEntity;
import com.kinzhan.dev.platform.beans.mapper.SysMenuMapper;
import com.kinzhan.dev.platform.service.SysMenuService;
import com.kinzhan.dev.platform.util.tree.MenuBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Override
    public List<SysMenuEntity> getList() {
        return lambdaQuery()
                .eq(SysMenuEntity::getEnabled, 1)
                .eq(SysMenuEntity::getIsDelete, 0)
                .orderByAsc(SysMenuEntity::getSort)
                .list();
    }

    @Override
    public List<SysMenuEntity> findAllByRole(String role) {
        if ("SA".equalsIgnoreCase(role)) {
            return getList();
        }

        return baseMapper.findAllByRole(role);
    }

    @Override
    public List<SysMenuEntity> findAllByUserId(Integer userId) {
        if ("SA".equalsIgnoreCase(LoggedUser.get().getRole())) {
            return getList();
        }
        return baseMapper.findAllByUserId(userId);
    }

    @Override
    public List getTree() {
        List<SysMenuEntity> menuList = getList();
        List<MenuBuilder.Node> nodes = new ArrayList();
        menuList.forEach(entity ->{
            MenuBuilder.Node node = new MenuBuilder.Node();
            MenuBuilder.Meta meta = new MenuBuilder.Meta();
            BeanUtils.copyProperties(entity, meta);
            node.setMeta(meta);
            BeanUtils.copyProperties(entity, node);
            nodes.add(node);
        });
        log.info("dict {}", nodes);
        List<MenuBuilder.Node> result = new MenuBuilder().buildTree(nodes);

        return result;
    }

    @Override
    public void copyOne(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setId(null);
            entity.setTitle(entity.getTitle() + "-复制");

            saveOrUpdate(entity);
        });
    }

}
