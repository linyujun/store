package com.kinzhan.dev.platform.beans.dto.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页专用DTO
 */
@Data
@ApiModel(value = "分页实体")
public class QueryDto<T> {
    @ApiModelProperty(value = "当前页面")
    private Integer page = 0;
    @ApiModelProperty(value = "返回条数")
    private Integer size = 10;
    @ApiModelProperty(value = "排序")
    private List<Sort> sort;
    @ApiModelProperty(value = "查询条件")
    private T filter;
}
