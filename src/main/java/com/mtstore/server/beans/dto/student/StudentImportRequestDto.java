package com.mtstore.server.beans.dto.student;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentImportRequestDto {
    @NotBlank(message = "文件必须上传")
    private String file;
}
