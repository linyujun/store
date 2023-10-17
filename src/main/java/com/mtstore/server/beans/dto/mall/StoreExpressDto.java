package com.mtstore.server.beans.dto.mall;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-31
*/
@Data
public class StoreExpressDto extends BaseDto{
    /** 运费模板 */
    @ApiModelProperty("运费模板")
    private Integer parentId = 0;

    /** 模板名称 */
    @ApiModelProperty("模板名称")
    private String title;

    /** 计费方式，按件数 num，按重量weight，按体积volume */
    @ApiModelProperty("计费方式，按件数 num，按重量weight，按体积volume")
    private String calcType;

    /** 地区 */
    @ApiModelProperty("地区")
    private List<String> address;

    /** 首件 */
    @ApiModelProperty("首件")
    private BigDecimal num;

    /** 运费 */
    @ApiModelProperty("运费")
    private BigDecimal price;

    /** 续件 */
    @ApiModelProperty("续件")
    private BigDecimal extraNum;

    /** 续件费 */
    @ApiModelProperty("续件费")
    private BigDecimal extraPrice;

    /** 概述 */
    @ApiModelProperty("概述")
    private String description;

    /** 是否默认 */
    @ApiModelProperty("是否默认")
    private Boolean isDefault;

    /** 开启满包邮 */
    @ApiModelProperty("开启满包邮")
    private Boolean isFree;

    /** 满包邮金额 */
    @ApiModelProperty("满包邮金额")
    private BigDecimal fullPrice;

    /** 是否配送 */
    @ApiModelProperty("是否配送")
    private Boolean isDelivery = true;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("模板详情")
    private List<StoreExpressDto> details;
}
