package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kinzhan.dev.platform.beans.entity.BaseEntity;
import com.kinzhan.dev.platform.beans.mapper.SysDeptMapper;
import com.kinzhan.dev.platform.beans.mapper.SysRoleMapper;
import com.kinzhan.dev.platform.beans.mapper.SysUserMapper;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 组织架构
 * </p>
 *
 * @author songsir
 * @since 2023-03-03
 */
@Getter
@Setter
@TableName("kz_sys_dept")
@ApiModel(value = "SysDeptEntity对象", description = "组织架构")
public class SysDeptEntity extends BaseEntity {

    @ApiModelProperty("组织名称")
    private String deptName;

    @ApiModelProperty("父级ID")
    private Integer parentId;

    @ApiModelProperty("层级信息，从根节点到当前节点的最短路径，使用,分割节点ID")
    private String hierarchy;

    @ApiModelProperty("当前节点深度")
    private Integer depth;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("管理员用户ID")
    private Integer adminId;

    @TableField(exist = false)
    @OneToOne(from = "parentId", to = "parentName", clazz = SysDeptMapper.class, method = "selectNameById")
    private String parentName;

    @TableField(exist = false)
    @OneToOne(from = "adminId", to = "adminUser", clazz = SysUserMapper.class, method = "selectByCreateUser")
    private String adminUser;

    @ApiModelProperty("排序字段，由小到大")
    private Integer sort;
}
