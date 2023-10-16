package com.kinzhan.dev.platform.beans.dto.store;

import lombok.Data;
import java.time.*;
import java.io.Serializable;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-04-27
*/
@Data
@ApiModel(value = "门店")
public class StoreDto extends BaseDto{
    private String uuid;

    /** 商户id */
    @ApiModelProperty("商户id")
    private Integer merchantId;

    /** 门店名称 */
    @ApiModelProperty("门店名称")
    @NotBlank
    private String storeName;

    /** 头像 */
    @ApiModelProperty("头像")
    private String avatarUrl;

    /** 头图 */
    @ApiModelProperty("头图")
    private String imgUrl;

    /** 轮播图 */
    @ApiModelProperty("轮播图")
    private String[] imgUrls;

    /** 店铺类型 local,delivery,mall */
    @ApiModelProperty("店铺类型 local,delivery,mall")
    @NotBlank
    private String storeType;

    /** 门店简称 */
    @ApiModelProperty("门店简称")
    private String shortName;

    /** 省市区 */
    @ApiModelProperty("省市区")
    private String[] address;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    private String addressDetail;

    /** 经纬度 */
    @ApiModelProperty("经纬度")
    private String latitude;

    /** 经纬度 */
    @ApiModelProperty("经纬度")
    private String longitude;

    /** 是否营业 */
    @ApiModelProperty("是否营业")
    private Boolean isOnline;

    /** 营业开始时间 */
    @ApiModelProperty("营业开始时间")
    private String startTime;

    /** 营业结束时间 */
    @ApiModelProperty("营业结束时间")
    private String endTime;

    /** 联系人 */
    @ApiModelProperty("联系人")
    private String contactUser;

    /** 联系方式 */
    @ApiModelProperty("联系方式")
    private String contactPhone;

    /** 备注信息 */
    @ApiModelProperty("备注信息")
    private String description;

    private String tags;

}
