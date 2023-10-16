package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
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
import java.util.List;

/**
* @author songsir
* @date 2023-05-31
*/
@Data
@TableName(value = "kz_store_express", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城运费模板对象", description = "商城运费模板表")
public class StoreExpressEntity extends BaseEntity{
    /** 运费模板 */
    @ApiModelProperty("运费模板")
    private Integer parentId;

    /** 模板名称 */
    @ApiModelProperty("模板名称")
    private String title;

    /** 计费方式，按件数 num，按重量weight，按体积volume */
    @ApiModelProperty("计费方式，按件数 num，按重量weight，按体积volume")
    private String calcType;

    /** 地区 */
    @ApiModelProperty("地区")
    @TableField(typeHandler = FastjsonTypeHandler.class)
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
    private Boolean isDelivery;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 店铺id */
    @ApiModelProperty("店铺id")
    private Integer storeId;

    @TableField(exist = false)
    List<StoreExpressEntity> details;
}
