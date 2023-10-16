package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;

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
