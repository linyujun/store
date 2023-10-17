package com.mtstore.server.beans.dto.mall.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "发货信息，自提/发货共用")
@Accessors(chain = true)
public class OrderAddressInfoDto {

    @NotBlank(message = "收件人必填")
    @ApiModelProperty("收件人")
    private String nickName;

    @NotBlank(message = "手机号必填")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank(message = "省市区必填")
    @ApiModelProperty("省市区")
    private List<String> address;

    @ApiModelProperty("门店id")
    private Integer storeId;

    @ApiModelProperty("自提时间")
    private String pickupTime;

    @NotBlank(message = "详细地址必填")
    @ApiModelProperty("详细地址，具体到门牌号")
    private String addressDetail;
}
