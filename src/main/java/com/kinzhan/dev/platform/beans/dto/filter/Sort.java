package com.kinzhan.dev.platform.beans.dto.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页实体")
public class Sort {

    @ApiModelProperty(value = "排序字段")
    private String key;

    @ApiModelProperty(value = "排序方法，[asc,desc]")
    private String value;
}
