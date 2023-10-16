package com.kinzhan.dev.platform.beans.dto.user;

import com.kinzhan.dev.platform.beans.annotion.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@ApiModel("分销员分页查询")
public class UserPromoterQueryDto {
    @Query
    @ApiModelProperty(value = "分销等级,取值范围[1,2]", required = true)
    private Integer level;

    @Query(type = Query.Type.INNER_LIKE)
    @ApiModelProperty("用户昵称")
    private String nickName;
}
