package com.mtstore.server.service.impl;

import com.mtstore.server.beans.entity.ArticleCategoryEntity;
import com.mtstore.server.beans.mapper.ArticleCategoryMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.ArticleCategoryService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 文章分类
*/
@Service
@RequiredArgsConstructor
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategoryEntity> implements ArticleCategoryService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }
}
