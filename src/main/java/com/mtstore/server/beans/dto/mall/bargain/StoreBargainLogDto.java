package com.mtstore.server.beans.dto.mall.bargain;

import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-07
*/
@Data
public class StoreBargainLogDto extends BaseDto{
    /** 发起id */
    @ApiModelProperty("发起id")
    private Integer parentId;

    /** 用户id */
    @ApiModelProperty("用户id")
    private Integer uid;

    /** 砍价商品id */
    @ApiModelProperty("砍价商品id")
    private Integer productId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 当前价格 */
    @ApiModelProperty("当前价格")
    private BigDecimal price;

    /** 保护价 */
    @ApiModelProperty("保护价")
    private BigDecimal bargainPrice;

    /** 砍掉的金额 */
    @ApiModelProperty("砍掉的金额")
    private BigDecimal cutPrice;

    /** 每次砍价区间 */
    @ApiModelProperty("每次砍价区间")
    private List<BigDecimal> priceRange;

    /** 活动开始时间 */
    @ApiModelProperty("活动开始时间")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
