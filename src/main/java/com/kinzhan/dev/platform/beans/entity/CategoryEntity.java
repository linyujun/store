package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 演出分类
 * </p>
 *
 * @author songsir
 * @since 2021-11-22
 */
@Data
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("kz_category")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分类对象", description = "")
public class CategoryEntity extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("父级ID")
    @Builder.Default
    private Integer parentId = 0;

    @ApiModelProperty("图片")
    private String imgUrl;

    @ApiModelProperty("图标")
    private String icon;

    private Integer sort;

    @ApiModelProperty("跳转链接")
    private String urlPath;

    @ApiModelProperty("描述")
    private String description;
}
