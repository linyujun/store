package com.kinzhan.dev.platform.beans.dto.teacher;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "教师信息", description = "")
public class TeacherDto extends BaseDto {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    @NotBlank(message = "性别必填")
    private String gender;

    private String password;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号必填")
    private String phone;

    @ApiModelProperty("老师姓名")
    @NotBlank(message = "老师姓名必填")
    private String realName;

    @ApiModelProperty("老师头像")
    private String avatarUrl;

    @ApiModelProperty("老师介绍")
    private String description;

    private Integer uid;
}
