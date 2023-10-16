package com.kinzhan.dev.platform.beans.dto.store;

import com.kinzhan.dev.platform.beans.annotion.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreQueryDto {

    @ApiModelProperty(value = "所属商家id")
    @Query
    private Integer merchantId;

    private String longitude;

    private String latitude;
}
