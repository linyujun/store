package com.mtstore.server.service;

import com.mtstore.server.beans.entity.ArticleEntity;
import com.mtstore.server.beans.dto.article.ArticleDto;

/**
* @author songsir
* 文章
*/
public interface ArticleService extends IKService<ArticleEntity, ArticleDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);


    void visited(Integer id);
}
