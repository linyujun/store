<el-form ref="form" :model="form" <#if isNotNullColumns??>:rules="rules"</#if> size="small" label-width="80px">
  <#if columns??>
    <#list columns as column>
      <#if column.formShow>
        <el-form-item label="<#if column.remark != ''>${column.remark}<#else>${column.changeColumnName}</#if>"<#if column.istNotNull> prop="${column.changeColumnName}"</#if>>
          <#if column.formType = 'Input'>
            <el-input v-model="form.${column.changeColumnName}" style="width: 370px;" />
          <#elseif column.formType = 'Textarea'>
            <el-input :rows="3" v-model="form.${column.changeColumnName}" type="textarea" style="width: 370px;" />
          <#elseif column.formType = 'Radio'>
            <#if column.dictName??>
              <el-radio v-for="item in dict.${column.dictName}" :key="item.id" v-model="form.${column.changeColumnName}" :label="item.value">{{ item.label }}</el-radio>
            <#else>
              未设置字典，请手动设置 Radio
            </#if>
          <#elseif column.formType = 'Select'>
            <#if column.dictName??>
              <el-select v-model="form.${column.changeColumnName}" filterable placeholder="请选择">
                <el-option
                        v-for="item in dict.${column.dictName}"
                        :key="item.id"
                        :label="item.label"
                        :value="item.value" />
              </el-select>
            <#else>
              未设置字典，请手动设置 Select
            </#if>
          <#elseif column.formType = 'Imges'>
            <MaterialList
                    v-model="${column.changeColumnName}Arr"
                    style="width: 915px;"
                    type="image"
                    :num="1"
                    :width="150"
                    :height="150"
            />
          <#else>
            <el-date-picker v-model="form.${column.changeColumnName}" type="datetime" style="width: 370px;" />
          </#if>
        </el-form-item>
      </#if>
    </#list>
  </#if>
</el-form>
