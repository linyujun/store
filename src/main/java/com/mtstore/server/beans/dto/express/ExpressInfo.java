/**
 * Copyright 2018 bejson.com
 */
/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package com.mtstore.server.beans.dto.express;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2018-07-19 22:27:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ExpressInfo {

    @ApiModelProperty(value = "物流运单号")
    private String LogisticCode;

    @ApiModelProperty(value = "快递公司编码")
    private String ShipperCode;


    @ApiModelProperty(value = "物流轨迹")
    private List<Traces> Traces;

    @ApiModelProperty(value = "物流状态：2-在途中,3-签收,4-问题件")
    private String State;

    @ApiModelProperty(value = "用户ID")
    private String EBusinessID;

    @ApiModelProperty(value = "成功与否")
    private boolean Success;

    @ApiModelProperty(value = "失败原因")
    private String Reason;

    private String ShipperName;

    @ApiModelProperty(value = "订单编号")
    private String OrderCode;
}
