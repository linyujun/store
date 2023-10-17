package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2022-04-02
 */
@Getter
@Setter
@Builder
@TableName("kz_sys_permission")
@ApiModel(value = "SysPermissionEntity对象", description = "")
public class SysPermissionEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roleId;

    private Integer menuId;
}
