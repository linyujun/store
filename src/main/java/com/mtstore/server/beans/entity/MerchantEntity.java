package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

import java.time.*;

/**
* @author songsir
* @date 2023-04-06
*/
@Data
@TableName(value = "kz_merchant", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "商家对象", description = "商家表")
public class MerchantEntity extends BaseEntity{
    /** 上级代理商 */
    @ApiModelProperty("上级代理商")
    private Integer agentId;

    /** 商家名称 */
    @ApiModelProperty("商家名称")
    private String merchantName;

    /** 商户简称 */
    @ApiModelProperty("商户简称")
    private String shortName;

    /** 头像地址 */
    @ApiModelProperty("头像地址")
    private String avatarUrl;

    /** 商家头图 */
    @ApiModelProperty("商家头图")
    private String frontImgUrl;

    /** 图片 */
    @ApiModelProperty("图片")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] imgUrls;

    /** 描述信息 */
    @ApiModelProperty("描述信息")
    private String description;

    /** 分类 */
    @ApiModelProperty("分类")
    private String categoryId;

    /** 省市区 */
    @ApiModelProperty("省市区")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] address;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    private String addressDetail;

    /** 营业执照 */
    @ApiModelProperty("营业执照")
    private String licenseUrl;

    /** 经纬度 */
    @ApiModelProperty("经纬度")
    private String latitude;

    /** 经纬度 */
    @ApiModelProperty("经纬度")
    private String longitude;

    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contactUser;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String contactPhone;

    /** 到期时间 */
    @ApiModelProperty("到期时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate expiredTime;

    /** 是否上线 */
    @ApiModelProperty("是否上线")
    private Boolean isOnline;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态 */
    @ApiModelProperty("状态")
    private String statusDesc;

}
