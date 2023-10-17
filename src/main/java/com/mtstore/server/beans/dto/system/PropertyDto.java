package com.mtstore.server.beans.dto.system;

import com.mtstore.server.beans.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class PropertyDto extends BaseDto {
    private Integer parentId;
    private String groups;
    private String label;
    private String k;
    private String v;
    private String type;
    private Boolean isHidden;
    private Integer sort;
    private List<PropertyChildDto> attributes;
}
