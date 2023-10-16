package com.kinzhan.dev.platform.beans.dto.printer.template;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import java.time.*;
import java.io.Serializable;
import java.util.List;

import com.kinzhan.dev.platform.beans.dto.BaseDto;
import javax.validation.constraints.*;
import io.swagger.annotations.*;
/**
* @author songsir
* @date 2023-06-15
*/
@Data
public class PrintTemplateDto extends BaseDto{
    /** 所属门店 */
    @ApiModelProperty("所属门店")
    private Integer storeId;

    /** 模板名称 */
    @ApiModelProperty("模板名称")
    @NotBlank
    private String templateName;

    /** 模板类型 */
    @ApiModelProperty("模板类型")
    private String templateType;

    /** 描述信息 */
    @ApiModelProperty("描述信息")
    private String description;

    /** 模板内容 */
    @ApiModelProperty("模板内容")
    private String template;

    /** 配置详情 */
    @ApiModelProperty("配置详情")
    private JSONObject details;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 是否模板 */
    @ApiModelProperty("是否模板")
    private Boolean isDefault;

}
