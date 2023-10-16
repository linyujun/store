package com.kinzhan.dev.platform.beans.dto.mall;

import lombok.Data;
import java.time.*;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-13
*/
@Data
public class StoreServiceDto extends BaseDto{
    /** 图标 */
    @ApiModelProperty("图标")
    private String imgUrl;

    /** 服务名称 */
    @ApiModelProperty("服务名称")
    private String title;

    /** 概述 */
    @ApiModelProperty("概述")
    private String description;

    /** 是否默认(新增商品时) */
    @ApiModelProperty("是否默认(新增商品时)")
    private Boolean isDefault;

    /** 排序方式(数字越小越靠前) */
    @ApiModelProperty("排序方式(数字越小越靠前)")
    private Integer sort;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

}
