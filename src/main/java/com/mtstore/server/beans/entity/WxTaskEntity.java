package com.mtstore.server.beans.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* @date 2023-05-23
*/
@Data
@TableName(value = "kz_wx_task", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "应用管理对象", description = "应用管理表")
public class WxTaskEntity extends BaseEntity{

    /** 小程序类型，个人personal，企业company */
    @ApiModelProperty("小程序类型，个人personal，企业company")
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
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject payload;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 状态描述 */
    @ApiModelProperty("状态描述")
    private String statusDesc;

}
