package ${package}.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.*;
<#if isNotNullColumns ??>
import javax.validation.constraints.*;
</#if>
<#if hasDateAnnotation>
</#if>
<#if hasDateTime>
import java.time.*;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>

/**
* @author ${author}
* @date ${date}
*/
@Data
@TableName(value = "${tableName}", autoResultMap = true)
@Accessors(chain = true)
@ApiModel(value = "${apiAlias}对象", description = "${apiAlias}表")
public class ${className}Entity <#if superClass??>extends BaseEntity<#else> </#if>{
<#if columns??>
    <#list columns as column>
    <#if column.changeColumnName != 'isDelete'
        && column.changeColumnName != 'id'
        && column.changeColumnName != 'createUser'
        && column.changeColumnName != 'enabled'
        && column.changeColumnName != 'tenantId'
        && column.changeColumnName != 'createTime'
        && column.changeColumnName != 'updateTime'>
    <#if column.remark != ''>
    /** ${column.remark} */
    </#if>
    <#if column.columnKey = 'PRI'>
    @TableId(type = IdType.AUTO)
    </#if>
    <#if column.remark != ''>
    @ApiModelProperty("${column.remark}")
    </#if>
    <#if column.columnType = 'LocalDateTime'>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    </#if>
    <#if column.columnType = 'LocalDate'>
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    </#if>
    private ${column.columnType} ${column.changeColumnName};

    </#if>
    </#list>
</#if>
}
