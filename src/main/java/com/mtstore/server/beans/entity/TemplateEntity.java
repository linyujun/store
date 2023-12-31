package com.mtstore.server.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author songsir
 * 点评模板
 */
@Getter
@Setter
@TableName(value = "kz_template", autoResultMap = true)
@ApiModel(value = "TemplateEntity对象", description = "点评模板")
public class TemplateEntity extends BaseEntity {

    private String title;

    private String content;

    @ApiModelProperty("点评星级")
    private Integer level;

    @ApiModelProperty("附件")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String[] attachment;
}
