package com.mtstore.server.beans.dto.student;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class StudentImportDto {

    @ColumnWidth(20)
    @ExcelProperty("*绑定手机号")
    @NotBlank(message = "手机号必填")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phone;

    @ColumnWidth(20)
    @ExcelProperty("真实姓名")
    private String realName;

    @ColumnWidth(20)
    @ExcelProperty("*昵称")
    @NotBlank(message = "昵称必填")
    private String nickName;

    @ColumnWidth(20)
    @ExcelProperty("出生年月")
    @DateTimeFormat("yyyy-MM-dd")
    private String birthday;

    @ColumnWidth(20)
    @ExcelProperty("机构备注")
    private String description;

    @ColumnWidth(20)
    @ExcelProperty("账号状态")
    private String enabled;
}
