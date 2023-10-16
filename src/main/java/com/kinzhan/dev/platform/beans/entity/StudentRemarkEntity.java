package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 学生备注表
 * </p>
 *
 * @author songsir
 * @since 2023-02-12
 */
@Getter
@Setter
@TableName("kz_student_remark")
@ApiModel(value = "StudentRemarkEntity对象", description = "学生备注表")
public class StudentRemarkEntity extends BaseEntity {

    private Integer sid;

    @ApiModelProperty("备注名")
    private String customName;

    @ApiModelProperty("备注信息")
    private String description;
}
