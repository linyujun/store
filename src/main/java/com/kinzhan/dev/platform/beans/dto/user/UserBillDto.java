package com.kinzhan.dev.platform.beans.dto.user;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class UserBillDto extends BaseDto {

    @ApiModelProperty("类型,积分，余额")
    private String category;

    @ApiModelProperty("操作类型，签到，邀请用户，购买商品")
    private String action;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("总额")
    private BigDecimal totalAmount;

    @ApiModelProperty("增加 -，或者减少 +")
    private String direction;

    @ApiModelProperty("账单原由，明细等等")
    private String title;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("用户")
    private Integer uid;
}
