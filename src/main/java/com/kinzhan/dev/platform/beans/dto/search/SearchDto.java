package com.kinzhan.dev.platform.beans.dto.search;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchDto {
    @NotBlank(message = "请输入想搜索的内容")
    String title;
}
