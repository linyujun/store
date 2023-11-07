package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;

/**
* @author songsir
* 商品评价
*/
@Data
@TableName(value = "kz_store_comment", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城商品评价对象", description = "商城商品评价表")
public class StoreCommentEntity extends BaseEntity{
    /** 订单ID */
    @ApiModelProperty("订单ID")
    private String orderId;

    /** 产品id */
    @ApiModelProperty("产品id")
    private String productId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    /** 商品图片快照 */
    @ApiModelProperty("商品图片快照")
    private String imgUrl;

    /** 数量 */
    @ApiModelProperty("数量")
    private Integer cartNum;

    /** 商品单价 */
    @ApiModelProperty("商品单价")
    private BigDecimal price;

    /** 总价 */
    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

    /** 属性名称 */
    @ApiModelProperty("属性名称")
    private String attrKey;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Integer uid;

    @ApiModelProperty("昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty("头像")
    @TableField(exist = false)
    private String avatarUrl;

    /** 1好评，2中评，3差评 */
    @ApiModelProperty("1好评，2中评，3差评")
    private Integer level;

    /** 商品分数 */
    @ApiModelProperty("商品分数")
    private Integer productLevel;

    /** 物流分数 */
    @ApiModelProperty("物流分数")
    private Integer deliveryLevel;

    /** 服务分数 */
    @ApiModelProperty("服务分数")
    private Integer serviceLevel;

    /** 评论内容 */
    @ApiModelProperty("评论内容")
    private String description;

    /** 追评 */
    @ApiModelProperty("追评")
    private String append;

    /** 评论图片 */
    @ApiModelProperty("评论图片")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> imgUrls;

    /** 管理员回复内容 */
    @ApiModelProperty("管理员回复内容")
    private String replyContent;

    /** 管理员回复时间 */
    @ApiModelProperty("管理员回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime replyTime;

    /** 0未回复1已回复 */
    @ApiModelProperty("0未回复1已回复")
    private Boolean isReply;

    @ApiModelProperty("评论状态 0未1已")
    private Integer status;

    @ApiModelProperty("评论状态")
    private String statusDesc;

}
