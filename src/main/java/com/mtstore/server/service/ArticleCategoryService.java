package com.mtstore.server.service;

import com.mtstore.server.beans.entity.ArticleCategoryEntity;
import com.mtstore.server.beans.dto.article.ArticleCategoryDto;

/**
* @author songsir
* 文章分类
*/
public interface ArticleCategoryService extends IKService<ArticleCategoryEntity, ArticleCategoryDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);
}
