package com.kinzhan.dev.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import com.kinzhan.dev.platform.beans.entity.ArticleEntity;
import com.kinzhan.dev.platform.service.ArticleService;
import com.kinzhan.dev.platform.beans.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
* @author songsir
* @date 2023-04-27
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
