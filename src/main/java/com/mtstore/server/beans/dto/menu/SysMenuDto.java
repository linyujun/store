package com.mtstore.server.beans.dto.menu;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "SysMenuDto对象", description = "系统菜单请求体")
public class SysMenuDto {

    public Integer id;

    private Integer parentId = 0;

    @NotBlank(message = "菜单名称必填")
    private String title;

    @NotBlank(message = "菜单名称必填")
    private String name;

    private String icon;

    private String permission;

    @NotBlank(message = "菜单路径必填")
    private String path;

    private String target;

    private String component;

    private String uri;

    private Integer sort;

    private Boolean keepAlive;

    private Boolean hidden;

    private Integer type;

    private String remarks;
}
