<template>
  <div class="app-container">
    <div class="filter-container">

      <el-form ref="dataGeneratorForm" :inline="true" :model="generatorParam" label-position="right" label-width="110px" style="width: 100%; margin-left:20px;">

        <el-row>
          <el-col :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                数据源

                <el-button v-waves class="filter-item" style="float: right;margin-left:5px;" type="primary" size="mini" icon="el-icon-search" @click="getTablesInfo">
                  获取表信息
                </el-button>
                <el-button class="filter-item" style="float: right;margin-left:5px;" size="mini" type="primary" icon="el-icon-document-add" @click="addDataDialog">
                  添加
                </el-button>
                <el-button v-waves class="filter-item" style="float: right;margin-left:5px;" type="primary" size="mini" icon="el-icon-edit" @click="editDataDialog">
                  编辑
                </el-button>
                <el-button v-waves class="filter-item" style="float: right;margin-left:5px;" type="primary" size="mini" icon="el-icon-link" @click="testConnect">
                  测试链接
                </el-button>

              </div>
              <el-form :inline="true" label-width="80px" label-position="center">
                <el-row>
                  <el-form-item label="数据源:">
                    <el-select v-model="currentDbConnect" value-key="dbConnectCode" placeholder="请选择" style="width:150px;" @change="changeDbConnnetSelect">
                      <el-option
                        v-for="item in list"
                        :key="item.dbConnectCode"
                        :label="item.dbConnectName"
                        :value="item"
                      />
                    </el-select>
                    <el-form-item label="表名:" prop="type">
                      <el-input v-model="tablesInfoQuery.tableName" placeholder="表名" style="width:150px;" />
                    </el-form-item>
                  </el-form-item>
                </el-row>
                <el-row />
              </el-form>
              <el-table
                ref="tablesInfoTable"
                :data="tablesInfo"
                element-loading-text="Loading"
                border
                fit
                highlight-current-row
                @row-click="handleRowClickTablesChange"
                @selection-change="handleSelectionTablesChange"
              >
                <template slot="empty">
                  {{ empty_tip }}
                </template>

                <el-table-column
                  type="selection"
                  width="55"
                />
                <el-table-column align="center" label="数据表名" width="200">
                  <template slot-scope="scope">
                    {{ scope.row.TABLE_NAME }}
                  </template>
                </el-table-column>
                <el-table-column align="center" label="数据表备注">
                  <template slot-scope="scope">
                    {{ scope.row.TABLE_COMMENT }}
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </el-col>
          <el-col :span="15">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>代码模板</span>
                <el-button v-waves class="filter-item" style="float: right;margin-left:5px;" type="primary" size="mini" icon="el-icon-video-play" @click="generateCode">
                  生成代码
                </el-button>
              </div>
              <el-collapse v-model="activeCollapseNames">
                <el-collapse-item title="基础信息" name="1">
                  <el-row>
                    <el-form-item label="作者" prop="type">
                      <el-input v-model="generatorParam.auth" placeholder="作者" style="width:150px;" />
                    </el-form-item>
                    <el-form-item label="包名" prop="type">
                      <el-input v-model="generatorParam.packageName" placeholder="链接地址" style="width:150px;" />
                    </el-form-item>
                    <el-form-item label="模块名称" prop="type">
                      <el-input v-model="generatorParam.moduleName" placeholder="链接名称" style="width:150px;" />
                    </el-form-item>
                    <el-form-item label="启用Lombok" prop="type">
                      <el-switch
                        v-model="generatorParam.lombok"
                        active-text=""
                        inactive-text=""
                      />

                    </el-form-item>

                  </el-row>
                </el-collapse-item>
                <el-collapse-item title="模板选择" name="2">
                  <el-form
                    :inline="true"
                    style="width: 100%; "
                    label-position="right"
                    label-width="110px"
                  >
                    <el-row>
                      <el-form-item label="模板分组" prop="type">
                        <el-select v-model="codeTemplateGroupTemp" placeholder="请选择" style="width:200px;" @change="codeTemplateGroupTempChange">
                          <el-option
                            v-for="item in codeTemplateGroupList"
                            :key="item.templateGroupId"
                            :label="item.templateGroupName"
                            :value="item.templateGroupId"
                          />
                        </el-select>
                      </el-form-item>
                    </el-row>

                    <el-row>
                      <el-form-item v-for="o in Object.keys(sysCodeTemplateTypeList)" :key="o" :label="o" prop="type">
                        <el-select v-model="generatorParam.codeTemplateMap[o]" value-key="templateId" placeholder="请选择" style="width:100px;">
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
                </el-collapse-item>

              </el-collapse>

            </el-card>
          </el-col>
        </el-row>
      </el-form>
      <el-dialog title="数据库链接编辑" :visible.sync="dialogFormVisible" :close-on-click-modal="false" top="50px">
        <el-form ref="dataForm" :model="temp" label-position="right" label-width="110px" style="width: 400px; margin-left:50px;">
          <el-form-item label="链接编号" prop="type">
            <el-input v-model="temp.dbConnectCode" readonly disabled placeholder="链接编号" />
          </el-form-item>
          <el-form-item label="链接地址" prop="type">
            <el-input v-model="temp.dbConnectUrl" placeholder="链接地址" />
          </el-form-item>
          <el-form-item label="链接名称" prop="type">
            <el-input v-model="temp.dbConnectName" placeholder="链接名称" />
          </el-form-item>
          <el-form-item label="链接用户" prop="type">
            <el-input v-model="temp.dbConnectUser" placeholder="链接用户" />
          </el-form-item>
          <el-form-item label="链接密码" prop="type">
            <el-input v-model="temp.dbConnectPassword" placeholder="链接密码" />
          </el-form-item>
          <el-form-item label="链接端口" prop="type">
            <el-input v-model="temp.dbConnectPort" placeholder="链接端口" />
          </el-form-item>
          <el-form-item label="链接数据库名" prop="type">
            <el-input v-model="temp.dbConnectDatabase" placeholder="链接数据库名" />
          </el-form-item>
          <el-form-item label="链接数据库类型" prop="type">
            <el-select v-model="temp.dbConnectType" placeholder="请选择">
              <el-option
                v-for="item in Object.keys(dbTypeList)"
                :key="item"
                :label="dbTypeList[item]"
                :value="item"
              />
            </el-select>
          </el-form-item>

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

      <el-dialog title="代码生成" custom-class="generatorDialog" :visible.sync="dialogGeneratorFormVisible" :close-on-click-modal="false" top="50px">

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogGeneratorFormVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="generateCode">
            确定
          </el-button>
        </div>
      </el-dialog>
    </div>
  </div></template>

  <style>
       .generatorDialog{
        width:80%;
        font-size: 12px;
    }
    .box-card {
    margin-left: 10px;
  }

</style>

<script>
import { get_base_data_item_map, download_file } from '@/api/kkautogenerator/settings'
import { get_db_connect_page_list, deleteById, insert, update, get_details } from '@/api/kkautogenerator/db_connect_api'
import { get_code_template_group_by_type } from '@/api/kkautogenerator/code_template_api'
import { generate_code, test_connect, get_tables_info } from '@/api/kkautogenerator/db_information_schema_api'
import * as code_template_group_api from '@/api/kkautogenerator/code_template_group_api'
// import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { parseTime, renderHeaderTip } from '@/utils/index.js'
import waves from '@/directive/waves' // waves directive
import config from '@/config'
export default {
  // components: { Pagination },
  directives: { waves },
  filters: {

  },
  data() {
    return {
      empty_tip: config.table_list_empty_tip,
      baseDataItemMapType: ['dbType', 'SysCodeTemplateType', 'SystemSettingConfig'],
      yesOrNo: { 'true': '是', 'false': '否' },
      dbTypeList: {},
      currentDbConnect: undefined,
      tablesInfoQuery: {
        tableName: undefined,
        dbConnectCode: undefined
      },
      list: null,
      sysCodeTemplateTypeList: {},
      systemSettingConfig: {},
      tablesInfo: null,
      codeTemplataList: null,
      total: 0,
      activeCollapseNames: ['1', '2'],
      listQuery: {
        dbConnectCode: undefined,
        dbConnectUrl: undefined,
        dbConnectName: undefined,
        dbConnectUser: undefined,
        dbConnectPassword: undefined,
        dbConnectPort: undefined,
        dbConnectDatabase: undefined,
        createTimeStart: undefined,
        createTimeEnd: undefined,
        createId: undefined,
        dbConnectType: undefined,
        pageIndex: 1,
        pageSize: 100
      },
      dialogFormVisible: false,
      dialogGeneratorFormVisible: false,
      temp: {
        dbConnectCode: undefined,
        dbConnectUrl: undefined,
        dbConnectName: undefined,
        dbConnectUser: undefined,
        dbConnectPassword: undefined,
        dbConnectPort: undefined,
        dbConnectDatabase: undefined,
        createTime: undefined,
        createId: undefined,
        dbConnectType: undefined
      },
      generatorParam: {
        lombok: true,
        tablesInfo: [],
        tables: [],
        packageName: 'com.kk',
        moduleName: 'xx',
        auth: 'kk',
        dbConnect: {},
        codeTemplateMap: {}
      },
      codeTemplateGroupTemp: undefined,
      codeTemplateGroupList: [],
      dialogStatus: 'insert'
    }
  },
  created() { // 初始化界面
    get_code_template_group_by_type({ pageIndex: 1, pageSize: 100 }).then(response => {
      this.codeTemplataList = response.data === null ? [] : response.data
    })
    get_base_data_item_map({ queryTypeList: this.baseDataItemMapType }).then(response => {
      this.dbTypeList = response.data === null ? {} : response.data.queryTypeResult['dbType'.toLowerCase()]
      this.sysCodeTemplateTypeList = response.data === null ? {} : response.data.queryTypeResult['sysCodeTemplateType'.toLowerCase()]
      this.systemSettingConfig = response.data === null ? {} : response.data.queryTypeResult['systemSettingConfig'.toLowerCase()]
      this.generatorParam.codeTemplateMap = this.sysCodeTemplateTypeList
      this.generatorParam.auth = this.systemSettingConfig.auth
      this.generatorParam.packageName = this.systemSettingConfig.packageName
      this.generatorParam.moduleName = this.systemSettingConfig.moduleName
    })
    code_template_group_api.get_code_template_group_page_list({ pageIndex: 1, pageSize: 1000 }).then(response => {
      this.codeTemplateGroupList = response.data === null ? [] : response.data.result
      // this.total = response.data === null ? 0 : response.data.totalCount
    })
    this.getPageList()
  },
  methods: {
    getPageList() { // 查询列表分页
      get_db_connect_page_list(this.listQuery).then(response => {
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
      this.temp.createTime = new Date(this.temp.createTime)
      update(this.temp).then(response => {
        this.dialogFormVisible = false
        this.getPageList()
      })
    },
    addData() {
      this.temp.createTime = new Date(this.temp.createTime)
      insert(this.temp).then(response => {
        this.dialogFormVisible = false
        this.getPageList()
      })
    },
    editDataDialog(row) {
      let isReturn = false
      if (this.currentDbConnect == null) {
        isReturn = true
        this.$alert('请选择数据链接', '提示', {
          confirmButtonText: '确定',
          callback: action => {

          }
        })
      }
      if (isReturn) return false
      row = this.currentDbConnect
      this.dialogStatus = 'update'
      get_details(row).then(response => {
        this.temp = Object.assign({}, response.data === null ? row : response.data) // copy obj
        this.temp.createTime = parseTime(new Date(row.createTime), '{y}-{m}-{d} {h}:{i}:{s}')

        this.dialogFormVisible = true
      })
    },
    addDataDialog(row) {
      this.dialogStatus = 'insert'
      this.temp = Object.assign({}, row) // copy obj
      this.dialogFormVisible = true
    },
    testConnect() {
      test_connect(this.currentDbConnect).then(response => {

      })
    },
    getTablesInfo() {
      const vo = this.tablesInfoQuery
      vo.pageSize = 100
      vo.pageIndex = 1
      get_tables_info(vo).then(response => {
        this.tablesInfo = response.data === null ? [] : response.data
      })
    },
    changeDbConnnetSelect(e) {
      this.generatorParam.dbConnect = e
      this.tablesInfoQuery.dbConnectCode = e.dbConnectCode
      this.generatorParam.moduleName = e.dbConnectDatabase.replace(/_/g, '').toLowerCase()
    },
    handleSelectionTablesChange(val) {
      this.generatorParam.tablesInfo = val
      this.generatorParam.tables = []
      for (var t in val) {
        this.generatorParam.tables.push(val[t].TABLE_NAME)
      }
    },
    handleRowClickTablesChange(row, cloums, event) {
      this.$refs.tablesInfoTable.toggleRowSelection(row)
    },
    generatorDialog() {
      this.dialogGeneratorFormVisible = true
    },
    generateCode() {
      generate_code(this.generatorParam).then(response => {
        download_file('path=' + response.data.relativePath + '&fileName=' + response.data.fileName).then(response => {
        })
      })
    },
    codeTemplateGroupTempChange(e) {
      code_template_group_api.get_details({ templateGroupId: this.codeTemplateGroupTemp }).then(response => {
        const subList = response.data.codeTemplateGroupSubList
        for (const i in subList) {
          if (this.generatorParam.codeTemplateMap[subList[i].templateType]) {
            this.generatorParam.codeTemplateMap[subList[i].templateType] = subList[i].templateId
          }
        }
      })
    },
    parseTime: parseTime,
    renderHeaderTip: renderHeaderTip
  }
}
</script>
