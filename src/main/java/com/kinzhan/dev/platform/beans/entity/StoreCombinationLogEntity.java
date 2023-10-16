package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kinzhan.dev.platform.beans.mapper.SysUserMapper;
import com.kinzhan.dev.platform.beans.mapper.UserMapper;
import com.kinzhan.dev.platform.config.plugins.annotion.OneToOne;
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
* @date 2023-06-02
*/
@Data
@TableName(value = "kz_store_combination_log", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商城-拼团记录对象", description = "商城-拼团记录表")
public class StoreCombinationLogEntity extends BaseEntity{
    /** 团id */
    @ApiModelProperty("团id")
    private Integer groupId;

    /** 拼团活动id */
    @ApiModelProperty("拼团活动id")
    private Integer combinationId;

    /** 活动名称 */
    @ApiModelProperty("活动名称")
    private String activityName;

    /** 商品 */
    @ApiModelProperty("商品")
    private Integer productId;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("订单id")
    private String orderId;

    /** 商品图片 */
    @ApiModelProperty("商品图片")
    private String imgUrl;

    /** 原价 */
    @ApiModelProperty("原价")
    private BigDecimal price;

    /** 拼团加 */
    @ApiModelProperty("拼团价")
    private BigDecimal combinationPrice;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /** 数量限制 */
    @ApiModelProperty("数量限制")
    private Integer limitNum;

    /** 成团人数 */
    @ApiModelProperty("成团人数")
    private Integer groupNum;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expiredTime;

    /** 虚拟成团 */
    @ApiModelProperty("虚拟成团")
    private Boolean isVirtual;

    /** 团长 */
    @ApiModelProperty("团长")
    private Boolean isLeader;

    /** 用户 */
    @ApiModelProperty("用户")
    private Integer uid;

    @TableField(exist = false)
    @OneToOne(from = "uid", to = "user", clazz = UserMapper.class, method = "selectById")
    private UserEntity user;

    @ApiModelProperty("拼团状态")
    private Integer status;

    @ApiModelProperty("拼团状态描述")
    private String statusDesc;

    /** 是否成功 */
    @ApiModelProperty("是否成功")
    private Boolean isSuccess;

    @TableField(exist = false)
    private List<StoreCombinationLogEntity> details;
}
