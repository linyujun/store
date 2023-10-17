package com.mtstore.server.beans.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String field;
    private Object value;
    private String link = "and";
}
