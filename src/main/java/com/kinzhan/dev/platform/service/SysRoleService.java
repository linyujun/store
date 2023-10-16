package com.kinzhan.dev.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinzhan.dev.platform.beans.dto.filter.PageDto;
import com.kinzhan.dev.platform.beans.dto.system.SysRoleDto;
import com.kinzhan.dev.platform.beans.entity.sys.SysRoleEntity;
import com.kinzhan.dev.platform.util.FilterUtil;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 系统角色Service层
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    default SysRoleEntity findByRoleName(String roleName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_name", roleName);

        return getBaseMapper().selectOne(queryWrapper);
    }

    /**
     * 简化添加和更新逻辑
     * @param dto
     * @return
     */
    default boolean saveOrUpdate(SysRoleDto dto){
        SysRoleEntity entity;
        if (null!= dto.getId() && dto.getId() > 0) {
            entity = getById(dto.getId());
            BeanUtils.copyProperties(dto, entity);
        } else {
            entity = new SysRoleEntity();
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
        Page page = new Page<SysRoleEntity>(pageDto.getPage(),pageDto.getSize());

        return page(page, wrapper);
    }
}
