package com.kinzhan.dev.platform.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.google.gson.JsonArray;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import java.time.*;

/**
* @author songsir
* @date 2023-04-14
*/
@Data
@TableName(value = "kz_store_attr_template", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "规格模板对象", description = "规格模板表")
public class StoreAttrTemplateEntity extends BaseEntity{
    /** 规格名称 */
    @ApiModelProperty("规格名称")
    private String attrKey;

    /** 规格值 */
    @ApiModelProperty("规格值")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JsonArray values;

    /** 排序 */
    @ApiModelProperty("排序")
    private Integer sort;
}
