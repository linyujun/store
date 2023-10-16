package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;
import java.math.BigDecimal;

/**
* @author songsir
* @date 2023-05-26
*/
@Data
@TableName(value = "kz_store_recharge", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城充值方案对象", description = "商城充值方案表")
public class StoreRechargeEntity extends BaseEntity{
    /** 显示名称 */
    @ApiModelProperty("显示名称")
    private String title;

    /** 充值金额 */
    @ApiModelProperty("充值金额")
    private BigDecimal price;

    /** 额外赠送 */
    @ApiModelProperty("额外赠送")
    private BigDecimal extraPrice;

    /** 概述 */
    @ApiModelProperty("概述")
    private String description;

    /** 排序方式(数字越小越靠前) */
    @ApiModelProperty("排序方式(数字越小越靠前)")
    private Integer sort;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

}
