/**
 * Copyright 2018 bejson.com
 */
/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.kinzhan.dev.platform.beans.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Traces {
    @ApiModelProperty(value = "描述")
    private String acceptStation;

    @ApiModelProperty(value = "时间")
    private String acceptTime;
}
