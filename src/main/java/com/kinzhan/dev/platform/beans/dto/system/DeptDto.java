package com.kinzhan.dev.platform.beans.dto.system;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeptDto extends BaseDto {
    @ApiModelProperty("组织名称")
    @NotBlank(message = "组织名称必填")
    private String deptName;

    @ApiModelProperty("父级ID")
    @NotNull(message = "父级ID必填")
    private Integer parentId;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("部门负责人ID")
    @NotNull(message = "部门负责人ID必填")
    private Integer adminId;

    @ApiModelProperty("排序字段，由小到大")
    private Integer sort;

}
