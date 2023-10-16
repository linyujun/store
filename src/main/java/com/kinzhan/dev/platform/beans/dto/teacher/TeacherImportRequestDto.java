package com.kinzhan.dev.platform.beans.dto.teacher;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TeacherImportRequestDto {

    @NotBlank(message = "文件必须上传")
    private String file;
}
