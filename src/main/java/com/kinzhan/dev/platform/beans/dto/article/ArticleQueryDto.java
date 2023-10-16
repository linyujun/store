package com.kinzhan.dev.platform.beans.dto.article;

import com.kinzhan.dev.platform.beans.annotion.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleQueryDto {

    @ApiModelProperty(value = "分类id")
    @Query
    private Integer categoryId;

    @ApiModelProperty(value = "文章")
    @Query(blurry = "title,description,content")
    private String title;
}
