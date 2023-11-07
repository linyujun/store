package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.google.gson.JsonArray;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

/**
* @author songsir
* 规格模板
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
