package com.mtstore.server.beans.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ImportRequestDto {
    @NotBlank(message = "文件必须上传")
    private String file;
}
