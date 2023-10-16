package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.menu.SysMenuDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysMenuEntity;
import com.kinzhan.dev.platform.util.FilterUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    List<SysMenuEntity> getList();

    List<SysMenuEntity> findAllByRole(String role);

    List<SysMenuEntity> findAllByUserId(Integer userId);

    List getTree();
    /**
     * 简化添加和更新逻辑
     * @param dto
     * @return
     */
    default boolean saveOrUpdate(SysMenuDto dto){
        SysMenuEntity entity;
        if (null!= dto.getId() && dto.getId() > 0) {
            entity = getById(dto.getId());
            BeanUtils.copyProperties(dto, entity);
        } else {
            entity = new SysMenuEntity();
            BeanUtils.copyProperties(dto, entity);
        }

        return saveOrUpdate(entity);
    }

    /**
     * 配合前端二次封装分页查询
     * @param pageDto
     * @param wrapper
     * @return
     */
    default Page getPageList(PageDto pageDto, QueryWrapper wrapper) {
        wrapper = FilterUtil.convertFilterDtoToWrapper(pageDto.getFilter(), wrapper);
        Page page = new Page<SysMenuEntity>(pageDto.getPage(),pageDto.getSize());
        return page(page, wrapper);
    }


    /**
     * 复制一个处理
     * @param id
     */
    void copyOne(Integer id);
}
