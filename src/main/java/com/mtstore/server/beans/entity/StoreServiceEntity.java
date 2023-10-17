package com.mtstore.server.beans.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-05-13
*/
@Data
@TableName(value = "kz_store_service", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城商品服务对象", description = "商城商品服务表")
public class StoreServiceEntity extends BaseEntity{
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
