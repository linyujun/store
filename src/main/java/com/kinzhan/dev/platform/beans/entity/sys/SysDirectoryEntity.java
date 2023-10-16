package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kinzhan.dev.platform.beans.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 目录表
 * </p>
 *
 * @author songsir
 * @since 2023-03-16
 */
@Getter
@Setter
@TableName("kz_sys_directory")
@ApiModel(value = "SysDirectoryEntity对象", description = "目录表")
public class SysDirectoryEntity extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("备注信息")
    private String description;

    @ApiModelProperty("平台，机构，老师")
    private String target;

    private Integer sort;
}
