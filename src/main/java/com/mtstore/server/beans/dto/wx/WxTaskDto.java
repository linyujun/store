package com.mtstore.server.beans.dto.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import com.mtstore.server.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-05-23
*/
@Data
public class WxTaskDto extends BaseDto{
    /** 小程序类型，个人personal，企业company */
    @ApiModelProperty("小程序类型，个人personal，企业company")
    @NotBlank(message = "小程序类型必填")
    private String appType;

    private String appId;

    private String appSecret;

    /** 任务id */
    @ApiModelProperty("任务id")
    private String taskId;

    /** 给用户扫码认证的验证url */
    @ApiModelProperty("给用户扫码认证的验证url")
    private String authorizeUrl;

    /** 请求实体 */
    @ApiModelProperty("请求实体")
    private JSONObject payload;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;
}
