package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.ArticleCategoryEntity;
import com.kinzhan.dev.platform.beans.dto.article.ArticleCategoryDto;

/**
* @author songsir
* @date 2023-04-27
*/
public interface ArticleCategoryService extends IKService<ArticleCategoryEntity, ArticleCategoryDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
