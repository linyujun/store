package com.mtstore.server.beans.dto.mall.product;

import lombok.Data;

import java.util.List;

@Data
public class AttrDto {
    private String attr;
    private List<String> values;
}



