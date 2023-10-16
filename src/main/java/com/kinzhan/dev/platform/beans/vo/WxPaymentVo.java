package com.kinzhan.dev.platform.beans.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WxPaymentVo {
    @ApiModelProperty(value = "wxTradeId", required = true)
    private Long wxTradeId;

    @ApiModelProperty(value = "小程序id", required = true)
    private String appId;

    @ApiModelProperty(value = "时间戳", required = true)
    private String timeStamp;

    @ApiModelProperty(value = "随机字符串", required = true)
    private String nonceStr;

    @ApiModelProperty(value = "订单详情扩展字符串", required = true)
    private String _package;

    @ApiModelProperty(value = "签名方式", required = true)
    private String signType;

    @ApiModelProperty(value = "签名", required = true)
    private String paySign;
}
