<#--noinspection ALL-->
<template>
  <div class="app-container">
    <el-page-header @back="goBack" :content="title"></el-page-header>
    <vab-table
        v-loading="loading"
        :column="tableData.column"
        :data="tableData.data"
        :formData="formData"
        :formConfig="formConfig"
        background
        stripe
        filter
        pagination
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5, 10, 20, 30]"
        :total="tableData.total"
        default-expand-all
        @size-change="handleSizeChange"
        @selection-change="handleSelectionChange"
        @p-filter-request="handleRequest"
        @p-add="handleAdd"
        @p-current-change="handleCurrentChange"
    >
      <template #filter>
        <ele-form
                ref="form"
                v-model="formData"
                v-bind="formConfig"
                :request-fn="handleRequest"
                width="100%"
        >
          <template v-slot:form-btn="{}">
            <el-button
                    icon="el-icon-search"
                    native-type="submit"
                    type="primary"
            >
              查询
            </el-button>
            <el-button icon="el-icon-refresh" type="warning" @click="resetForm">
              重置
            </el-button>
          </template>
        </ele-form>
      </template>
      <template #actions>
        <div style="display: flex">
          <el-button type="primary" icon="el-icon-plus" @click="handleAdd">
            添加
          </el-button>
        </div>
      </template>
    </vab-table>
    <#if pageType?? && pageType == "dialog">
    <Dialog ref="dialog" @p-refresh="fetchData"/>
    </#if>
  </div>
</template>
<script>
  import * as httpClient from '@/api/${changeClassName}'
  <#if pageType?? && pageType == "dialog">
  import Dialog from './dialog';
  </#if>
  import list from '@/mixins/list'
  const configs = {
    httpClient,
  }
  export default {
    name: 'index',
    mixins: [list(configs)],
    components: {
      <#if pageType?? && pageType == "dialog">
      Dialog,
      </#if>
    },
    data() {
      return {
        batchMode: false,
        formConfig: {
          labelPosition: 'left',
          inline: true,
          formDesc: {
            title_like: {
              layout: 12,
              type: 'input',
              label: '名称',
            },
          },
        },
        tableData: {
          total: 0,
          column: [
            {
              type: 'selection',
            },
            <#if columns??>
            <#list columns as column>
            <#if column.columnShow>
            {
              prop: '${column.changeColumnName}',
              label: '${column.remark}',
              align: 'center',
              <#if column?? && column.changeColumnName?contains("avatar")>
              render: (h, scope) => {
                if (scope.row.${column.changeColumnName}) {
                  return (
                  <el-image
                    src={scope.row.${column.changeColumnName}}
                    preview-src-list={[scope.row.${column.changeColumnName}]}
                  />
                )
                }
              },
              </#if>
              <#if column?? && column.changeColumnName?contains("enabled")>
              render: (h, scope) => {
                return (
                 <el-switch
                  v-model={scope.row.enabled}
                  onChange={() => this.handleDisable(scope.row)}
                />
              )
              },
              </#if>
            },
            </#if>
            </#list>
            </#if>
            {
              prop: 'action',
              label: '操作',
              width: '150',
              fixed: 'right',
              render: (h, scope) => {
                return (
                  <div>
                    <el-button type="text"  onClick={ () => { this.handleEdit(scope.row) } }><span>编辑</span></el-button>
                    <span style="margin: 0 10px; color: #ddd;">|</span>
                    <el-dropdown trigger="click">
                      <el-button type="text">
                      更多<i class="el-icon-arrow-down el-icon--right"></i>
                      </el-button>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                        <el-button type="text" style="display:block; color: #ff4d4f;" onClick={()=>{ this.handleDelete(scope.row)} }>删除</el-button>
                        </el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </div>
                )
              },
            },
          ],
          data: [],
        },
      }
    },
    methods: {
      handleEdit(row){
        <#if pageType?? && pageType == "dialog">
        this.$refs['dialog'].showEdit(row)
        </#if>
        <#if pageType?? && pageType == "drawer">
        this.$refs['drawer'].showEdit(row)
        </#if>
        <#if pageType?? && pageType == "page">
        if (row.id)
          this.$router.push({
            path: '/${changeClassName}/edit',
            query: { id: row.id },
          })
        </#if>
      },
      /**
       * @override
       * @param row
       */
      handleAdd(){
        <#if pageType?? && pageType == "dialog">
        this.$refs['dialog'].showAdd()
        </#if>
        <#if pageType?? && pageType == "drawer">
        this.$refs['drawer'].showAdd()
        </#if>
        <#if pageType?? && pageType == "page">
        this.$router.push({
          path: '/${changeClassName}/edit',
        })
        </#if>
      },
    },
  }
</script>

<style scoped>
</style>
