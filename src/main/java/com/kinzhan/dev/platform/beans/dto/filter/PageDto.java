package com.kinzhan.dev.platform.beans.dto.filter;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 分页专用DTO
 */
@Data
public class PageDto {
    private Integer page = 0;
    private Integer size = 10;
    private List<Sort> sort;
    private List<Filter> filter;
    private Map<String, String> extra;
}
