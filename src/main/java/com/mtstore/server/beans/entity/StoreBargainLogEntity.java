package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.mtstore.server.beans.enums.ActivityStatusEnum;
import com.mtstore.server.beans.mapper.UserMapper;
import com.mtstore.server.config.plugins.annotion.OneToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.time.*;
import java.math.BigDecimal;
import java.util.List;

/**
* @author songsir
* 砍价记录
*/
@Data
@TableName(value = "kz_store_bargain_log", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-砍价记录对象", description = "商城-砍价记录表")
public class StoreBargainLogEntity extends BaseEntity{

    /** 发起id */
    @ApiModelProperty("砍价活动id")
    private Integer bargainId;

    /** 发起id */
    @ApiModelProperty("发起id")
    private Integer parentId;

    /** 用户id */
    @ApiModelProperty("用户id")
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    /** 砍价商品id */
    @ApiModelProperty("砍价商品id")
    private Integer productId;

    /** 砍价商品id */
    @ApiModelProperty("砍价商品详情id")
    private Integer productDetailId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 实付价格 */
    @ApiModelProperty("实付价格")
    private BigDecimal payPrice;

    /** 原始价格 */
    @ApiModelProperty("原始价格")
    private BigDecimal price;

    /** 保护价 */
    @ApiModelProperty("保护价")
    private BigDecimal bargainPrice;

    /** 砍掉的金额 */
    @ApiModelProperty("砍掉的金额")
    private BigDecimal cutPrice;

    /** 每次砍价区间 */
    @ApiModelProperty("每次砍价区间")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<BigDecimal> priceRange;

    /** 活动开始时间 */
    @ApiModelProperty("活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 过期时间 */
    @ApiModelProperty("过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expiredTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private ActivityStatusEnum status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("砍价记录")
    @TableField(exist = false)
    List<StoreBargainLogEntity> details;

    @Version
    private Integer version;


    @TableField(exist = false)
    @OneToOne(from = "uid", to = "user", clazz = UserMapper.class, method = "selectById")
    private UserEntity user;
}
