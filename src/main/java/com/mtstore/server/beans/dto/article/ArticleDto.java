package com.mtstore.server.beans.dto.article;

import lombok.Data;
import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-27
*/
@Data
public class ArticleDto extends BaseDto{
    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private Integer categoryId;

    /** 封面图 */
    @ApiModelProperty("封面图")
    private String imgUrl;

    /** 轮播图 */
    @ApiModelProperty("轮播图")
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
