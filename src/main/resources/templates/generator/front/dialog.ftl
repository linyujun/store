<template>
    <el-dialog
            :title="title"
            :close-on-press-escape="false"
            :close-on-click-modal="false"
            :wrapperClosable="false"
            width="40%"
            custom-class="custom-dialog"
            :visible.sync="visible"
    >
        <div class="dialog__content">
            <ele-form
                    :span=24
                    v-model="formData"
                    ref="form"
                    :form-desc="formConfig"
                    :request-fn="handleSubmit"
                    @request-success="handleSuccess"
            >
                <template v-slot:form-btn="{ btns }">
                    <div></div>
                </template>
            </ele-form>
        </div>
        <template #footer>
            <el-button size="medium" @click="dialogVisible = false">取 消</el-button>
            <el-button size="medium" type="primary" @click="triggerRequest">
                确 定
            </el-button>
        </template>
    </el-dialog>
</template>
<script>
import * as httpClient  from '@/api/${changeClassName}';
const configs = {
    httpClient,
}
import edit from '@/mixins/edit'
export default {
    mixins: [edit(configs)],
    components: {},
    data() {
        return {
            title: "编辑",
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
            this.visible = false
            this.$refs.form.resetForm()
            this.$emit('p-refresh')
        },
    },
}
</script>
<style lang="scss"  scoped>
:deep() {
    .el-dialog__header {
        padding-bottom: 10px;
        border-bottom: 1px solid #e8eaec;
        display: flex;
    }

    .el-dialog__body {
        padding: 0 20px;
        display: flex;
    }

    & .dialog__content {
          display: flex;
          flex-direction: column;
          height: 100%;
          margin-top: 20px;
      }

    .ele-form {
        flex: 1;
    }

    & .el-dialog__footer {
          border-top: 1px solid #e8eaec;
    }
}
</style>
