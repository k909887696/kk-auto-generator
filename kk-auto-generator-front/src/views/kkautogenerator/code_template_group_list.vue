<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" label-width="110px" label-position="right">
        <el-row>
          <el-form-item label="模板分组编号:">
            <el-input v-model="listQuery.templateGroupId" placeholder="模板分组编号" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-form-item label="模板分组名称:">
            <el-input v-model="listQuery.templateGroupName" placeholder="模板分组名称" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-form-item label="模板语言:">
            <el-select v-model="listQuery.templateLanguage" placeholder="请选择" @keyup.enter.native="handleFilter">
              <el-option
                v-for="item in Object.keys(SysCodeTemplateLanguage)"
                :key="item"
                :label="SysCodeTemplateLanguage[item]"
                :value="item"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间:">
            <el-date-picker v-model="listQuery.createTimeStart" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="width: 150px;" placeholder="选择时间" /> -
            <el-date-picker v-model="listQuery.createTimeEnd" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" style="width: 150px;" placeholder="选择时间" />
          </el-form-item>
        </el-row>
        <el-row justify="center" type="flex">
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getPageList">
            查询
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-document-add" @click="addDataDialog">
            添加
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
      <el-table-column align="center" label="模板分组编号" width="150">
        <template slot-scope="scope">
          {{ scope.row.templateGroupId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="模板分组名称">
        <template slot-scope="scope">
          {{ scope.row.templateGroupName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="模板语言" width="150">
        <template slot-scope="scope">
          {{ scope.row.templateLanguage }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="模板分组创建人" width="150">
        <template slot-scope="scope">
          {{ scope.row.createId }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="创建时间" width="110">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ parseTime(new Date(scope.row.createTime) ,'{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" width="300" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="{row}">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="editDataDialog(row)">
            编辑
          </el-button>
          <el-button type="primary" icon="el-icon-news" size="mini" @click="copyDataDialog(row)">
            复制
          </el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteData(row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageIndex" :limit.sync="listQuery.pageSize" @pagination="getPageList" />
    <el-dialog title="代码模板分组编辑" :visible.sync="dialogFormVisible" :close-on-click-modal="false" top="50px">
      <el-form ref="dataForm" :model="temp" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
        <el-form-item label="模板分组编号" prop="type">
          <el-input v-model="temp.templateGroupId" readonly disabled placeholder="模板分组编号" />
        </el-form-item>
        <el-form-item label="模板分组名称" prop="type">
          <el-input v-model="temp.templateGroupName" placeholder="模板分组名称" />
        </el-form-item>
        <el-form-item label="模板语言" prop="type">
          <el-select v-model="temp.templateLanguage" placeholder="请选择" :disabled="true">
            <el-option
              v-for="item in Object.keys(SysCodeTemplateLanguage)"
              :key="item"
              :label="SysCodeTemplateLanguage[item]"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="模板分组创建人" prop="type">
          <el-input v-model="temp.createId" placeholder="模板分组创建人" disabled />
        </el-form-item>

        <el-form-item label="创建时间" prop="type">
          <el-date-picker v-model="temp.createTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择时间" disabled />
        </el-form-item>

      </el-form>
      <span>模板明细</span>
      <el-divider />
      <el-form
        :inline="true"
        style="width: 100%; "
        label-position="right"
        label-width="110px"
      >
        <el-row>
          <el-form-item v-for="o in Object.keys(sysCodeTemplateType)" :key="o" :label="o" prop="type">
            <el-select v-model="tempGroupSub[o]" value-key="templateId" placeholder="请选择" style="width:100px;">
              <el-option
                v-for="item in codeTemplataList[o]"
                :key="item.templateId"
                :label="item.templateName"
                :value="item.templateId"
              />
            </el-select>
          </el-form-item>
        </el-row>
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
import { get_base_data_item_map } from '@/api/kkautogenerator/settings'
import { get_code_template_group_page_list, deleteById, insert, update, get_details } from '@/api/kkautogenerator/code_template_group_api'
import { get_code_template_group_by_type } from '@/api/kkautogenerator/code_template_api'
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
      baseDataItemMapType: ['SysCodeTemplateLanguage', 'SysCodeTemplateType', 'SysCodeTemplateClass'],
      sysCodeTemplateType: {},
      SysCodeTemplateLanguage: {},
      SysCodeTemplateClass: {},
      codeTemplataList: {},
      sysCodeTemplateTypeList: {},
      list: null,
      total: 0,
      listQuery: {
        templateGroupId: undefined,
        templateGroupName: undefined,
        templateGroupClass: 'per',
        createId: undefined,
        templateLanguage: undefined,
        createTimeStart: undefined,
        createTimeEnd: undefined,
        pageIndex: 1,
        pageSize: 10
      },
      dialogFormVisible: false,
      dialogGroupSubFormVisible: false,
      temp: {
        templateGroupId: undefined,
        templateGroupName: undefined,
        templateGroupClass: undefined,
        createId: undefined,
        templateLanguage: 'ftl',
        createTime: undefined,
        codeTemplateGroupSubList: []
      },
      tempGroupSub: {},
      dialogStatus: 'insert'
    }
  },
  created() { // 初始化界面
    get_base_data_item_map({ queryTypeList: this.baseDataItemMapType }).then(response => {
      this.SysCodeTemplateLanguage = response.data === null ? {} : response.data.queryTypeResult['SysCodeTemplateLanguage'.toLowerCase()]
      this.sysCodeTemplateType = response.data === null ? {} : response.data.queryTypeResult['sysCodeTemplateType'.toLowerCase()]
      this.SysCodeTemplateClass = response.data === null ? {} : response.data.queryTypeResult['SysCodeTemplateClass'.toLowerCase()]
      this.tempGroupSub = this.sysCodeTemplateType
    })
    get_code_template_group_by_type({ pageIndex: 1, pageSize: 100 }).then(response => {
      this.codeTemplataList = response.data === null ? [] : response.data
    })
    this.getPageList()
  },
  methods: {
    getPageList() { // 查询列表分页
      get_code_template_group_page_list(this.listQuery).then(response => {
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
      this.copyTempGroupSubToTemp()
      this.temp.createTime = new Date(this.temp.createTime)
      update(this.temp).then(response => {
        this.dialogFormVisible = false
        this.getPageList()
      })
    },
    addData() {
      this.copyTempGroupSubToTemp()
      this.temp.createTime = new Date(this.temp.createTime)
      insert(this.temp).then(response => {
        this.dialogFormVisible = false
        this.getPageList()
      })
    },
    editDataDialog(row) {
      this.dialogStatus = 'update'
      get_details(row).then(response => {
        this.temp = Object.assign({}, response.data === null ? row : response.data) // copy obj
        this.temp.createTime = parseTime(new Date(row.createTime), '{y}-{m}-{d} {h}:{i}:{s}')
        this.copyTempToTempGroupSub(this.temp.codeTemplateGroupSubList)
        this.dialogFormVisible = true
      })
    },
    addDataDialog(row) {
      this.dialogStatus = 'insert'
      // this.temp = Object.assign({}, row) // copy obj
      this.dialogFormVisible = true
    },
    editGroupSubDialog(row) {
      this.dialogGroupSubFormVisible = true
    },
    copyTempGroupSubToTemp() {
      this.temp.codeTemplateGroupSubList = []
      for (const tem in this.tempGroupSub) {
        const tempSub = {}
        tempSub.templateId = this.tempGroupSub[tem]
        tempSub.templateType = tem
        this.temp.codeTemplateGroupSubList.push(tempSub)
      }
    },
    copyTempToTempGroupSub(subList) {
      for (const i in subList) {
        if (this.tempGroupSub[subList[i].templateType]) {
          this.tempGroupSub[subList[i].templateType] = subList[i].templateId
        }
      }
    },
    copyDataDialog(row) {
      this.dialogStatus = 'insert'
      get_details({ templateGroupId: row.templateGroupId }).then(response => {
        this.temp = Object.assign({}, response.data === null ? row : response.data) // copy obj
        this.temp.templateGroupId = ''
        this.dialogFormVisible = true
      })
    },
    parseTime: parseTime,
    renderHeaderTip: renderHeaderTip
  }
}
</script>
