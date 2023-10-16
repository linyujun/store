package com.kinzhan.dev.platform.beans.dto.user;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import java.time.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-25
*/
@Data
public class UserLevelDto extends BaseDto{
    /** 图标 */
    @ApiModelProperty("图标")
    private String icon;

    /** 图片 */
    @ApiModelProperty("图片")
    private String imgUrl;

    /** 权益信息 */
    @ApiModelProperty("权益信息")
    private JSONArray description;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 等级名称 */
    @ApiModelProperty("等级名称")
    private String title;

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

    @ApiModelProperty("有效期/天")
    private Integer days;
}
