package com.mtstore.server.beans.dto.mall.bargain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "砍价助力")
public class BargainHelpDto {

    @ApiModelProperty(value = "砍价活动id", required =  true)
    @NotNull(message = "砍价活动id必填")
    private Integer bargainId;

    @ApiModelProperty(value = "砍价记录id", required = true)
    @NotNull(message = "砍价记录id必填")
    private Integer bargainLogId;
}
