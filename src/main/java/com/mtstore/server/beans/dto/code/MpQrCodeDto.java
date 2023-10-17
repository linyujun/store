package com.mtstore.server.beans.dto.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@ApiModel(value = "生成二维码对象")
public class MpQrCodeDto {

    @ApiModelProperty(value = "路径必填()", required = true, notes = "根路径前不要填加 /,不能携带参数", example = "pages/index/index")
    @NotBlank(message = "路径必填")
    private String page;

    @ApiModelProperty(value = "小程序版本",
            notes = "要打开的小程序版本。正式版为 release，体验版为 trial，开发版为 develop。默认是正式版",
            example = "release")
    private String envVersion = "release";

    @ApiModelProperty(value = "二维码的宽度", notes = "默认430，二维码的宽度，单位 px，最小 280px，最大 1280px", example = "430")
    private Integer width = 430;

    @ApiModelProperty(value = "请求参数", notes = "请求参数，不支持中文", example = "{id:\"1\"}")
    private Map params;
}
