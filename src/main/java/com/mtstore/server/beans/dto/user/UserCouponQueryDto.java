package com.mtstore.server.beans.dto.user;

import com.mtstore.server.beans.annotion.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
