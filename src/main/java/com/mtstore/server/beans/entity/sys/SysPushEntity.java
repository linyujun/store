package com.mtstore.server.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import com.mtstore.server.beans.mapper.TenantMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2023-02-02
 */
@Getter
@Setter
@TableName(value = "kz_sys_push", autoResultMap = true)
@ApiModel(value = "SysPushEntity对象", description = "")
public class SysPushEntity extends SysBaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("图片列表")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] imgUrls;

    @ApiModelProperty("查询条件")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List filters;

    @ApiModelProperty("推送状态")
    private Integer status;

    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("所属机构")
    private Integer tenantId;

    @OneToOne(from = "tenantId", to = "agentName", clazz = TenantMapper.class, method = "selectNameById")
    @TableField(exist = false)
    private String agentName;
}
