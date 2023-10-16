package com.kinzhan.dev.platform.beans.dto.student;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 学生备注表
 * </p>
 *
 * @author songsir
 * @since 2023-02-12
 */
@Data
public class StudentRemarkDto extends BaseDto {

    @NotNull(message = "学生id必填")
    private Integer sid;

    @ApiModelProperty("备注名")
    @NotBlank(message = "备注名必填")
    private String customName;

    @ApiModelProperty("备注信息")
    private String description;
}
