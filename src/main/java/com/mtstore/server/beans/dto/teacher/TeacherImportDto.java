package com.mtstore.server.beans.dto.teacher;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeacherImportDto {
    @NotBlank(message = "老师姓名必填")
    @ExcelProperty(value = "真实姓名")
    private String realName;

    @NotBlank(message = "昵称必填")
    @ExcelProperty("*昵称")
    private String nickName;

    @NotBlank(message = "手机号必填")
    @ExcelProperty("*绑定手机号")
    private String phone;

    @ExcelProperty("*初始密码")
    @NotBlank(message = "初始密码必填")
    private String password;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("老师介绍")
    private String description;

    @ExcelProperty("账号状态")
    private String enabled;
}
