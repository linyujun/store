package com.kinzhan.dev.platform.beans.entity;

import com.alibaba.fastjson.JSONObject;
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
* @date 2023-06-15
*/
@Data
@TableName(value = "kz_print_template", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "打印模板对象", description = "打印模板表")
public class PrintTemplateEntity extends BaseEntity{
    /** 所属门店 */
    @ApiModelProperty("所属门店")
    private Integer storeId;

    /** 模板名称 */
    @ApiModelProperty("模板名称")
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
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject details;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;

    /** 是否模板 */
    @ApiModelProperty("是否模板")
    private Boolean isDefault;

}
