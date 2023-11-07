package com.mtstore.server.beans.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.math.BigDecimal;

/**
* @author songsir
* 用户等级
*/
@Data
@TableName(value = "kz_user_level", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户等级对象", description = "用户等级表")
public class UserLevelEntity extends BaseEntity{
    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 权益信息 */
    @ApiModelProperty("权益信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray description;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 等级名称 */
    @ApiModelProperty("等级名称")
    private String title;

    @ApiModelProperty("会员天数")
    private Integer days;

    /** 付费会员 */
    @ApiModelProperty("付费会员")
    private Boolean isVip;

    /** 升级条件 */
    @ApiModelProperty("升级条件")
    private String requirements;

    /** 折扣 */
    @ApiModelProperty("折扣")
    private String discount;

    /** 购买价格 */
    @ApiModelProperty("购买价格")
    private BigDecimal price;
}
