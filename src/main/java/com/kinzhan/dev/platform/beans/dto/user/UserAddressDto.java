package com.kinzhan.dev.platform.beans.dto.user;

import com.alibaba.fastjson.JSONArray;
import com.kinzhan.dev.platform.beans.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ww
 * @date 2021/7/29
 **/
@Data
@ApiModel(value = "用户地址信息实体", description = "用户地址信息实体")
public class UserAddressDto extends BaseDto {
    /** 电话 */
    @ApiModelProperty(value = "电话", required = true)
    @NotBlank(message = "手机号必填")
    private String phone;

    /** 收件人 */
    @ApiModelProperty(value = "收件人", required = true)
    @NotBlank(message = "收件人必填")
    private String nickName;

    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    /** 省市区 */
    @ApiModelProperty(value = "省市区", required = true)
    @NotNull(message = "省市区必填")
    private JSONArray address;

    /** 详细地址 */
    @ApiModelProperty(value = "详细地址", required = true)
    @NotBlank(message = "详细地址必填")
    private String addressDetail;

    @ApiModelProperty("标签，如家，公司等")
    private String tags;

    private Boolean isDefault = false;
}
