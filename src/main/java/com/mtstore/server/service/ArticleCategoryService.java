package com.mtstore.server.service;

import com.mtstore.server.beans.entity.ArticleCategoryEntity;
import com.mtstore.server.beans.dto.article.ArticleCategoryDto;

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
