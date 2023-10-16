package com.kinzhan.dev.platform.beans.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@ApiModel(value = "学生请求对象", description = "学生")
public class StudentDto extends BaseDto {

    private Integer storeId;

    @NotBlank(message ="手机号必填")
    private String phone;

    @ApiModelProperty("姓名")
    @NotBlank(message ="姓名必填")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("地址位置")
    private String[] address;

    @ApiModelProperty("机构备注信息")
    private String description;

    @ApiModelProperty("极光推送id")
    private String pushId;

    @ApiModelProperty("导入批次id")
    private Integer logId;

    private Integer uid;
}
