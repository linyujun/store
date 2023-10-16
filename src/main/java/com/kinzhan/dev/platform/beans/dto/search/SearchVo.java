package com.kinzhan.dev.platform.beans.dto.search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchVo {
    Integer id;
    String title;
    String imageUrl;
    String type;
}
