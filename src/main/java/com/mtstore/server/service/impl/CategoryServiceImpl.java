package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtstore.server.beans.entity.CategoryEntity;
import com.mtstore.server.beans.mapper.CategoryMapper;
import com.mtstore.server.service.CategoryService;
import com.mtstore.server.util.tree.CategoryBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  商品分类实现类
 * @author songsir
 * @since 2021-11-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Override
    public List<CategoryBuilder.Node> getTree() {
        List<CategoryEntity> listDict =
                lambdaQuery()
                .eq(CategoryEntity::getEnabled, true)
                .eq(CategoryEntity::getIsDelete, false)
                .orderByDesc(CategoryEntity::getSort)
                .list();
        List<CategoryBuilder.Node> nodes = new ArrayList();
        listDict.forEach(entity ->{
            CategoryBuilder.Node node = new CategoryBuilder.Node();
            BeanUtils.copyProperties(entity, node);
            node.setLabel(entity.getTitle());
            nodes.add(node);
        });
        List<CategoryBuilder.Node> result = new CategoryBuilder().buildTree(nodes);

        return result;
    }

    @Override
    public List<Integer> findAllIdByParentId(Integer parentId) {
        List<Integer> list = new ArrayList<>();
        list.add(parentId);
        List<CategoryEntity> resultList = lambdaQuery()
                .eq(CategoryEntity::getParentId, parentId)
                .eq(CategoryEntity::getIsDelete, false)
                .eq(CategoryEntity::getEnabled, true).list();
        if (CollectionUtils.isNotEmpty(resultList)) {
            List<Integer> ids = resultList.stream().map(CategoryEntity::getId).collect(Collectors.toList());
            list.addAll(ids);
            ids.forEach(id -> {
                list.addAll(findAllIdByParentId(id));
            });
        }

        return list.stream().distinct().collect(Collectors.toList());
    }
}
