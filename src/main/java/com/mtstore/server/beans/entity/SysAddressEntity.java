package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

import java.util.List;

/**
* @author songsir
* @date 2023-06-13
*/
@Data
@TableName(value = "kz_sys_address", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "系统售后收货地址对象", description = "系统售后收货地址表")
public class SysAddressEntity extends BaseEntity{
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
    private List<String> address;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    private String addressDetail;

    private String tags;

    /** 默认 */
    @ApiModelProperty("默认")
    private Boolean isDefault;

}
