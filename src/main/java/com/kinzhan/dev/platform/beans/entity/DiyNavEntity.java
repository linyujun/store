package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kinzhan.dev.platform.beans.entity.BaseEntity;
import com.kinzhan.dev.platform.config.plugins.MybatisResultConverter;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author songsir
 * @since 2023-03-31
 */
@Getter
@Setter
@TableName("kz_diy_nav")
@ApiModel(value = "DiyNavEntity对象", description = "商品分类表")
public class DiyNavEntity extends BaseEntity {

    @ApiModelProperty("图标地址")
    private String iconPath;

    @ApiModelProperty("选中图标地址")
    private String selectedIconPath;

    @ApiModelProperty("导航标题")
    private String title;

    @ApiModelProperty("备注信息")
    private String description;

    @ApiModelProperty("跳转链接")
    private String pagePath;

    @ApiModelProperty("排序")
    private Integer sort;

    @OneToOne(from = "title", to = "text", clazz= MybatisResultConverter.class, method = "translate")
    @TableField(exist = false)
    private String text;
}
