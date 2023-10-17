package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.mapper.ArticleCategoryMapper;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-04-27
*/
@Data
@TableName(value = "kz_article", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "文章资讯对象", description = "文章资讯表")
public class ArticleEntity extends BaseEntity{

    @ApiModelProperty("分类")
    private Integer categoryId;

    @TableField(exist = false)
    @OneToOne(from = "categoryId",to = "category",clazz = ArticleCategoryMapper.class , method = "selectById")
    private ArticleCategoryEntity  category;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 封面图 */
    @ApiModelProperty("封面图")
    private String imgUrl;

    /** 轮播图 */
    @ApiModelProperty("轮播图")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] imgUrls;

    /** 摘要 */
    @ApiModelProperty("摘要")
    private String description;

    /** 文章详情 */
    @ApiModelProperty("文章详情")
    private String content;

    /** 作者 */
    @ApiModelProperty("作者")
    private String author;

    /** 附件 */
    @ApiModelProperty("附件")
    private String attachments;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    /** 点击次数 */
    @ApiModelProperty("点击次数")
    private Integer visited;

}
