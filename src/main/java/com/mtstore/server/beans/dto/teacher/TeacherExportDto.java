package com.mtstore.server.beans.dto.teacher;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class TeacherExportDto {
    @ExcelProperty(value = "老师姓名", index = 0)
    @ColumnWidth(20)
    private String realName;

    @ExcelProperty("昵称")
    @ColumnWidth(20)
    private String nickName;

    @ExcelProperty("性别")
    @ColumnWidth(20)
    private String gender;

    @ExcelProperty("手机号")
    @ColumnWidth(20)
    private String phone;

    @ExcelProperty("老师介绍")
    @ColumnWidth(20)
    private String description;

    @ExcelProperty("账号状态")
    @ColumnWidth(20)
    private boolean enabled;
}
