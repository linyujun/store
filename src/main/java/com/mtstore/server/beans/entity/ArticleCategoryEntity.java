package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-27
*/
@Data
@TableName(value = "kz_article_category", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "文章分类对象", description = "文章分类表")
public class ArticleCategoryEntity extends BaseEntity{
    /** 父级ID */
    @ApiModelProperty("父级ID")
    private Integer parentId;

    /** banner标题 */
    @ApiModelProperty("banner标题")
    private String title;

    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 描述信息 */
    @ApiModelProperty("描述信息")
    private String description;

    /** 跳转链接 */
    @ApiModelProperty("跳转链接")
    private String urlPath;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

}
