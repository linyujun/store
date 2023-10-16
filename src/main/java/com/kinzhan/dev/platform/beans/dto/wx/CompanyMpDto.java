package com.kinzhan.dev.platform.beans.dto.wx;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "创建企业小程序")
public class CompanyMpDto {

    @ApiModelProperty(value = "企业名", required = true)
    private String name;

    @ApiModelProperty(value = "企业代码", required = true)
    private String code;

    @ApiModelProperty(value = "企业代码类型（1：统一社会信用代码， 2：组织机构代码，3：营业执照注册号）", required = true)
    private String codeType;

    @ApiModelProperty(value = "法人微信", required = true)
    private String legalPersonaWechat;

    @ApiModelProperty(value = "法人姓名", required = true)
    private String legalPersonaName;

    @ApiModelProperty(value = "第三方联系电话", required = true)
    private String componentPhone;

}
