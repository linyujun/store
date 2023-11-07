package com.mtstore.server.beans.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* 用户收货地址
*/
@Data
@TableName(value = "kz_user_address", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "用户收货地址对象", description = "用户收货地址表")
public class UserAddressEntity extends BaseEntity{
    @TableField(fill = FieldFill.INSERT)
    private Integer uid;

    /** 电话 */
    @ApiModelProperty("电话")
    private String phone;

    /** 收件人 */
    @ApiModelProperty("收件人")
    private String nickName;

    private String postalCode;

    /** 省市区 */
    @ApiModelProperty("省市区")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray address;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    private String addressDetail;

    @ApiModelProperty("标签")
    private String tags;

    private Boolean isDefault;
}
