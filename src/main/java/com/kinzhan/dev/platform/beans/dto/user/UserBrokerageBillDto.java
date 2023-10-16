package com.kinzhan.dev.platform.beans.dto.user;

import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
import lombok.experimental.Accessors;

/**
* @author songsir
* @date 2023-06-19
*/
@Data
@Accessors(chain = true)
public class UserBrokerageBillDto extends BaseDto{
    /** 操作类型，签到，邀请用户，购买商品 */
    @ApiModelProperty("操作类型，签到，邀请用户，购买商品")
    private String action;

    /** 账单原由，明细等等 */
    @ApiModelProperty("账单原由，明细等等")
    private String title;

    /** 金额 */
    @ApiModelProperty("金额")
    @NotNull
    private BigDecimal amount;

    /** 总额 */
    @ApiModelProperty("总额")
    private BigDecimal totalAmount;

    /** 增加 -，或者减少 + */
    @ApiModelProperty("增加 -，或者减少 +")
    private String direction;

    /** 描述信息 */
    @ApiModelProperty("描述信息")
    private String description;

    /** 备用字段 */
    @ApiModelProperty("备用字段")
    private String attr;

    @ApiModelProperty("用户id")
    @NotNull
    private Integer uid;
}
