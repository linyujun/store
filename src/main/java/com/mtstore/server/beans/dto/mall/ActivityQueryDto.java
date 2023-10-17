package com.mtstore.server.beans.dto.mall;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "拼团记录分页查询实体")
public class ActivityQueryDto {

    @Query()
    @ApiModelProperty(value = "拼团状态 0->进行中，1->成功，2->失败")
    private Integer status;
}
