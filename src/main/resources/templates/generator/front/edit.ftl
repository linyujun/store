<template>
    <div class="edit-container">
        <el-page-header :content="'编辑'" @back="goBack" />
        <el-row>
            <el-col :lg="14" :md="24" :sm="24" :xl="14" :xs="24">
                <ele-form
                        v-model="formData"
                        ref="form"
                        :span="24"
                        :form-desc="formConfig"
                        :request-fn="handleSubmit"
                        @request-success="handleSuccess"
                >
                    <template v-slot:form-btn="{ btns }">
                        <el-button @click="triggerRequest" size="medium" type="primary">
                            保存
                        </el-button>
                    </template>
                </ele-form>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import * as httpClient  from '@/api/${changeClassName}';
import edit from '@/mixins/edit'
const configs = {
    httpClient,
}
export default {
    mixins: [edit(configs)],
    components: {},
    data() {
        return {
            formData: {
                enabled: true,
            },
            formConfig: {
                <#if columns??>
                <#list columns as column>
                <#if column.formShow>
                ${column.changeColumnName}: {
                    type: '${column.formType}',
                    label: '<#if column.remark != ''>${column.remark}<#else>${column.changeColumnName}</#if>',
                    <#if column.formType == "select">
                    //手动配齐options信息{ text: '选项一', value: '1' },
                    options: []
                    </#if>
                },
                </#if>
                </#list>
                </#if>
            },
        }
    },
    created() {
    },
    methods: {
        handleSuccess() {
            this.goBack()
        },
    },
}
</script>

<style lang="scss" scoped>
</style>
