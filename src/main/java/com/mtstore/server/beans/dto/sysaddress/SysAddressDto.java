package com.mtstore.server.beans.dto.sysaddress;

import lombok.Data;

import java.util.List;

import com.mtstore.server.beans.dto.BaseDto;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-13
*/
@Data
public class SysAddressDto extends BaseDto{
    /** 电话 */
    @ApiModelProperty("电话")
    private String phone;

    /** 收件人 */
    @ApiModelProperty("收件人")
    private String nickName;

    private String postalCode;

    /** 省市区 */
    @ApiModelProperty("省市区")
    private List<String> address;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    private String addressDetail;

    private String tags;

    /** 默认 */
    @ApiModelProperty("默认")
    private Boolean isDefault;

}
