package com.kinzhan.dev.platform.beans.dto.user;

import com.kinzhan.dev.platform.beans.annotion.Query;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author songsir
* @date 2023-05-08
*/
@Data
public class UserCouponQueryDto{
    /** 优惠券类型 */
    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    @Query
    private Integer status;
}
