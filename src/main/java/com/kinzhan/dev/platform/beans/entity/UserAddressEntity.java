package com.kinzhan.dev.platform.beans.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.List;

/**
* @author songsir
* @date 2023-04-19
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
