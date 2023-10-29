package com.mtstore.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mtstore.server.beans.entity.ArticleEntity;
import com.mtstore.server.beans.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import com.mtstore.server.service.ArticleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @author songsir
* 文章
*/
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    @Override
    public void disable(Integer id) {
        Optional.ofNullable(getById(id)).ifPresent(entity -> {
            entity.setEnabled(!entity.getEnabled());
            saveOrUpdate(entity);
        });
    }

    @Override
    public void visited(Integer id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.setSql("visited=visited+" + 1);
        updateWrapper.eq("id", id);

        update(updateWrapper);
    }
}
