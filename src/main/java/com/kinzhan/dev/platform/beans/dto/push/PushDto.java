package com.kinzhan.dev.platform.beans.dto.push;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import com.kinzhan.dev.platform.beans.dto.filter.Filter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class PushDto extends BaseDto {

    @NotNull(message = "条件不为空")
    private List<Filter> filters;

    @NotNull(message = "机构必选")
    private Integer tenantId;

    @NotBlank(message = "标题必填")
    private String title;

    @NotBlank(message = "内容必填")
    private String content;

    private String[] imgUrls;

    private String urlPath;

    private Map arguments;
}
