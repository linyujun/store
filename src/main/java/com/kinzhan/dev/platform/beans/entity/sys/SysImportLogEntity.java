package com.kinzhan.dev.platform.beans.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kinzhan.dev.platform.beans.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author songsir
 * @since 2023-02-13
 */
@Getter
@Setter
@TableName("kz_sys_import_log")
@ApiModel(value = "SysImportLogEntity对象", description = "")
@Accessors(chain = true)
public class SysImportLogEntity extends BaseEntity {

    @ApiModelProperty("student,teacher,plan,grade")
    private String target;

    private String fileName;

    private String filePath;

    @ApiModelProperty("总条数")
    private Integer totalLine;

    @ApiModelProperty("成功条数")
    private Integer successLine;

    @ApiModelProperty("失败条数")
    private Integer failLine;

    @ApiModelProperty("错误原因")
    private String failResult;


    private String result;

    @ApiModelProperty("失败原因")
    private String error;
}
