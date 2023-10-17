package com.mtstore.server.beans.dto.user;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("账单查询实体")
public class UserBillQueryDto {

    @Query
    @ApiModelProperty("收入：+，消费：-")
    private String direction;
}
