package com.mtstore.server.beans.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertySimpleDto {
    private String attribute;
    private String attrValue;
}
