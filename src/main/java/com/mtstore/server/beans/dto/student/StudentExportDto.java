package com.mtstore.server.beans.dto.student;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value = "学生请求对象", description = "学生")
public class StudentExportDto {
    @ColumnWidth(20)
    @ExcelProperty("所属门店")
    private Integer storeId;

    @ColumnWidth(20)
    @ExcelProperty("手机号")
    private String phone;

    @ColumnWidth(20)
    @ExcelProperty("学生")
    private String realName;

    @ColumnWidth(20)
    @ExcelProperty("昵称")
    private String nickName;

    @ColumnWidth(20)
    @ExcelProperty("出生年月")
    private LocalDate birthday;

    @ColumnWidth(20)
    @ExcelProperty("性别")
    private String gender;

    @ColumnWidth(20)
    @ExcelProperty("机构备注信息")
    private String description;
}
