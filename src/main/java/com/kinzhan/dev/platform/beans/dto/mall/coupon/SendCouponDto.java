package com.kinzhan.dev.platform.beans.dto.mall.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author songsir
* @date 2023-05-06
*/
@Data
@ApiModel(value = "定向发券接口")
public class SendCouponDto{
    //优惠券
    @ApiModelProperty(value = "优惠券", required =true)
    @NotNull(message = "优惠券必填")
    private Integer couponId;
    //用户
    @NotNull(message = "用户必填")
    private List<Integer> userIds;
    //数量
    @NotNull(message = "数量必填")
    private Integer num = 1;

    private String description;
}
