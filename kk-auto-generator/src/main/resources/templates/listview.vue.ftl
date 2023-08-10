<template>
    <div class="app-container">
        <div class="filter-container">
            <el-form :inline="true" label-width="110px" label-position="right">
                <#-- ----------  BEGIN 字段循环遍历  ---------->
                <#list table.fields as field>
                    <#if (field_index+1) %3 ==1 || field_index == 0>
                        <el-row>
                    </#if>
                    <#if field.propertyType?lower_case == "date" >
                        <el-form-item label="${field.comment}:">
                            <el-date-picker v-model="listQuery.${field.propertyName}Start" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="width: 150px;" placeholder="选择时间" /> -
                            <el-date-picker v-model="listQuery.${field.propertyName}End" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="width: 150px;" placeholder="选择时间" />
                        </el-form-item>
                    <#elseif field.propertyName ?lower_case?contains("date") >
                        <el-form-item label="${field.comment}:">
                            <el-date-picker v-model="listQuery.${field.propertyName}Start" type="date" format="yyyyMMdd" value-format="yyyyMMdd" style="width: 150px;" placeholder="选择时间" /> -
                            <el-date-picker v-model="listQuery.${field.propertyName}End" type="date" format="yyyyMMdd" value-format="yyyyMMdd" style="width: 150px;" placeholder="选择时间" />
                        </el-form-item>
                    <#else>
                        <el-form-item label="${field.comment}:">
                            <el-input v-model="listQuery.${field.propertyName}" placeholder="${field.comment}" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
                        </el-form-item>
                    </#if>
                    <#if ((field_index+1) %3 ==0 && field_index > 0) || !field?has_next>
                        </el-row>
                    </#if>
                </#list>
                <#------------  END 字段循环遍历  ---------->
                <el-row justify="center" type="flex">
                    <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getPageList">
                        查询
                    </el-button>
                    <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-document-add" @click="addDataDialog">
                        添加
                    </el-button>
                    <el-button v-waves class="filter-item" type="primary" icon="el-icon-download" @click="getPageList">
                        导出
                    </el-button>
                </el-row>
            </el-form>
        </div>
        <el-table
                :data="list"
                element-loading-text="Loading"
                border
                fit
                highlight-current-row
                style="width: 100%;"
        >
            <template slot="empty">
                {{ empty_tip }}
            </template>
            <#list table.fields as field>
                <#if field.keyFlag>
                    <el-table-column align="center" label="${field.comment}" width="150">
                        <template slot-scope="scope">
                            {{ scope.row.${field.propertyName} }}
                        </template>
                    </el-table-column>
                <#elseif field.propertyType?lower_case == "date">
                    <el-table-column align="center" label="${field.comment}" width="110">
                        <template slot-scope="scope">
                            <i class="el-icon-time" />
                            <span>{{ parseTime(new Date(scope.row.${field.propertyName}) ,'{y}-{m}-{d} {h}:{i}:{s}') }}</span>
                        </template>
                    </el-table-column>
                <#else >
                    <el-table-column align="center" label="${field.comment}" width="150">
                        <template slot-scope="scope">
                            {{ scope.row.${field.propertyName} }}
                        </template>
                    </el-table-column>
                </#if>
            </#list>
            <el-table-column label="" align="center" width="200" class-name="small-padding fixed-width" fixed="right">
                <template slot-scope="{row}">
                    <el-button type="primary" icon="el-icon-edit" size="mini" @click="editDataDialog(row)">
                        编辑
                    </el-button>
                    <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteData(row)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageIndex" :limit.sync="listQuery.pageSize" @pagination="getPageList" />
        <el-dialog title="${table.comment!}编辑" :visible.sync="dialogFormVisible" :close-on-click-modal="false" top="50px">
            <el-form ref="dataForm" :model="temp" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
                <#list table.fields as field>
                    <#if field.keyFlag>
                        <el-form-item label="${field.comment}" prop="type">
                            <el-input v-model="temp.${field.propertyName}" readonly disabled placeholder="${field.comment}"/>
                        </el-form-item>
                    <#elseif field.propertyType?lower_case == "date" >
                        <el-form-item label="${field.comment}" prop="type">
                            <el-date-picker v-model="temp.${field.propertyName}" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择时间" />
                        </el-form-item>
                    <#elseif  field.propertyName ?lower_case?contains("date")>
                        <el-form-item label="${field.comment}" prop="type">
                            <el-date-picker v-model="temp.${field.propertyName}" type="date" format="yyyyMMdd" value-format="yyyyMMdd" placeholder="选择时间" />
                        </el-form-item>
                    <#elseif field.propertyType?lower_case == "int" || field.propertyType?lower_case == "integer">
                        <el-form-item label="${field.comment}" prop="type">
                            <el-input v-model="temp.${field.propertyName}" oninput="value=value.replace(/[^0-9]/g,'')" />
                        </el-form-item>
                    <#else>
                        <el-form-item label="${field.comment}" prop="type">
                            <el-input v-model="temp.${field.propertyName}"  placeholder="${field.comment}" />
                        </el-form-item>
                    </#if>
                </#list>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    取消
                </el-button>
                <el-button type="primary" @click="dialogStatus==='insert'?addData():updateData()">
                    确定
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
// import { get_base_data_item_map } from '@/api/${package.ModuleName}/settings'
import { get_${table.name}_page_list, deleteById, insert, update, get_details } from '@/api/${package.ModuleName}/${table.name}_api'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { parseTime, renderHeaderTip } from '@/utils/index.js'
import waves from '@/directive/waves' // waves directive
import config from '@/config'
export default {
  components: { Pagination },
  directives: { waves },
  filters: {

  },
  data() {
      return {
          empty_tip: config.table_list_empty_tip,
          list: null,
          total: 0,
          listQuery: {
              <#list table.fields as field>
              <#if field.propertyType?lower_case == "date" || field.propertyName ?lower_case?contains("date") >
              ${field.propertyName}Start: undefined,
              ${field.propertyName}End: undefined,
      <#else >
      ${field.propertyName}: undefined,
          </#if>
          </#list>
          pageIndex: 1,
          pageSize: 10
  },
      dialogFormVisible: false,
          temp: {
          <#list table.fields as field>
          ${field.propertyName}: undefined<#if field_has_next>,</#if>
          </#list>
      },
      dialogStatus: 'insert'
  }
  },
  created() { // 初始化界面
      this.getPageList()
  },
  methods: {
      getPageList() { // 查询列表分页
          get_${table.name}_page_list(this.listQuery).then(response => {
              this.list = response.data === null ? [] : response.data.result
              this.total = response.data === null ? 0 : response.data.totalCount
          })
      },
      deleteData(row) {
          this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
          }).then(() => {
              deleteById(row).then(response => {
                  this.dialogFormVisible = false
                  this.getPageList()
              })
          }).catch(() => {
              this.$message({
                  type: 'info',
                  message: '已取消删除!'
              })
          })
      },
      updateData() {
          <#list table.fields as field>
          <#if field.propertyType?lower_case == "date">
          this.temp.${field.propertyName} = new Date(this.temp.${field.propertyName})
          </#if>
          </#list>
          update(this.temp).then(response => {
              this.dialogFormVisible = false
              this.getPageList()
          })
      },
      addData() {
          <#list table.fields as field>
          <#if field.propertyType?lower_case == "date">
          this.temp.${field.propertyName} = new Date(this.temp.${field.propertyName})
          </#if>
          </#list>
          insert(this.temp).then(response => {
              this.dialogFormVisible = false
              this.getPageList()
          })
      },
      editDataDialog(row) {
          this.dialogStatus = 'update'
          get_details(row).then(response => {
              this.temp = Object.assign({}, response.data === null ? row : response.data) // copy obj
              <#list table.fields as field>
              <#if field.propertyType?lower_case == "date">
              this.temp.${field.propertyName} = parseTime(new Date(row.${field.propertyName}), '{y}-{m}-{d} {h}:{i}:{s}')
              </#if>
              </#list>

              this.dialogFormVisible = true
          })
      },
      addDataDialog(row) {
          this.dialogStatus = 'insert'
          this.temp = Object.assign({}, row) // copy obj
          this.dialogFormVisible = true
      },
      parseTime: parseTime,
      renderHeaderTip: renderHeaderTip
  }
}
</script>
