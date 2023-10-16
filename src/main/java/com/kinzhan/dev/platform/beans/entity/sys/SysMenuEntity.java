package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author songsir
 * @since 2021-11-18
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("kz_sys_menu")
@ApiModel(value = "SysMenuEntity对象", description = "系统菜单表")
public class SysMenuEntity extends SysBaseEntity {

    @ApiModelProperty("父级ID")
    private Integer parentId;

    @ApiModelProperty("菜单标题")
    private String title;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("授权标识")
    private String permission;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("打开方式 (1组件 2内链 3外链)")
    private String target;

    @ApiModelProperty("对应Vue等组件")
    private String component;

    @ApiModelProperty("定位标识 (打开方式为组件时其值为组件相对路径，其他为URL地址)")
    private String uri;

    @ApiModelProperty("显示排序")
    private Integer sort;

    @ApiModelProperty("组件缓存：0-开启，1-关闭")
    private Integer noKeepAlive;

    private Boolean noColumn;

    @ApiModelProperty("隐藏菜单:  0-否，1-是")
    private Boolean hidden;

    @ApiModelProperty("菜单类型 （0目录，1菜单，2按钮）")
    private Integer type;

    @ApiModelProperty("是否动态菜单")
    private Boolean dynamicNewTab;

    @ApiModelProperty("备注信息")
    private String remarks;
}
