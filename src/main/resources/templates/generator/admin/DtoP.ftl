package ${package}.beans.dto.${changeClassName};

import lombok.Data;
<#if hasDateTime>
import java.time.*;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
import ${package}.beans.dto.BaseDto;
import javax.validation.constraints.*;
<#if !auto && pkColumnType == 'Long'>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>
import io.swagger.annotations.*;
/**
* @author ${author}
* @date ${date}
*/
@Data
public class ${className}Dto <#if superClass??>extends BaseDto<#else> </#if>{
<#if columns??>
    <#list columns as column>
    <#if column.changeColumnName != 'id'
        && column.changeColumnName != 'isDelete'
        && column.changeColumnName != 'createUser'
        && column.changeColumnName != 'enabled'
        && column.changeColumnName != 'tenantId'
        && column.changeColumnName != 'createTime'
        && column.changeColumnName != 'updateTime'>
    <#if column.remark != ''>
    /** ${column.remark} */
    @ApiModelProperty("${column.remark}")
    </#if>
    <#if column.istNotNull && column.columnKey != 'PRI'>
    <#if column.columnType = 'String'>
    @NotBlank
    <#else>
    @NotNull
    </#if>
    </#if>
    private ${column.columnType} ${column.changeColumnName};

    </#if>
    </#list>
</#if>
}
