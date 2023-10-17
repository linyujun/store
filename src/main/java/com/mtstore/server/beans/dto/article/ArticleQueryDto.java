package com.mtstore.server.beans.dto.article;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticleQueryDto {

    @ApiModelProperty(value = "分类id")
    @Query
    private Integer categoryId;

    @ApiModelProperty(value = "文章")
    @Query(blurry = "title,description,content")
    private String title;
}
