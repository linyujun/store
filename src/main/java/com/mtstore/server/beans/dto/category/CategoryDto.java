package com.mtstore.server.beans.dto.category;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "分类请求对象", description = "分类请求对象")
public class CategoryDto extends BaseDto {
    public Integer id;

    @NotBlank(message = "标题必填")
    private String title;

    private String icon;

    private Integer sort;

    private Integer parentId;

    private String imgUrl;

    private String urlPath;

    private Boolean enabled;
}
