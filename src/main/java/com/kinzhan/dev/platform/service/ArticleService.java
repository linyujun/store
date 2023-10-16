package com.kinzhan.dev.platform.service;

import com.kinzhan.dev.platform.beans.entity.ArticleEntity;
import com.kinzhan.dev.platform.beans.dto.article.ArticleDto;
import java.util.Map;
import java.util.List;
import java.io.IOException;
/**
* @author songsir
* @date 2023-04-27
*/
public interface ArticleService extends IKService<ArticleEntity, ArticleDto>{

    /**
    * 禁用，启用
    * @param id
    */
    void disable(Integer id);


    void visited(Integer id);
}
