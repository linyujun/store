package com.kinzhan.dev.platform.service;


import com.kinzhan.dev.platform.beans.dto.category.CategoryDto;
import com.kinzhan.dev.platform.beans.entity.CategoryEntity;
import com.kinzhan.dev.platform.util.tree.CategoryBuilder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
public interface CategoryService extends IKService<CategoryEntity, CategoryDto> {

    List<CategoryBuilder.Node> getTree();

    /**
     * 通过父级查找所有子集
     * @param parentId
     * @return
     */
    List<Integer> findAllIdByParentId(Integer parentId);
}
