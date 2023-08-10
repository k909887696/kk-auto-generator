<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" label-width="110px" label-position="right">
        <el-row>
          <el-form-item label="链接编号:">
            <el-input v-model="listQuery.dbConnectCode" placeholder="链接编号" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-form-item label="链接名称:">
            <el-input v-model="listQuery.dbConnectName" placeholder="链接名称" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-form-item label="数据库名:">
            <el-input v-model="listQuery.dbConnectDatabase" placeholder="数据库名" style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-form-item label="数据库类型:">
            <el-select v-model="listQuery.dbConnectType" placeholder="请选择">
              <el-option
                v-for="item in Object.keys(dbTypeList)"
                :key="item"
                :label="dbTypeList[item]"
                :value="item"
              />
            </el-select>
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
      <el-table-column align="center" label="链接编号" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectCode }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="链接地址" >
        <template slot-scope="scope">
          {{ scope.row.dbConnectUrl }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="链接名称" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectName }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="链接用户" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectUser }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="链接端口" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectPort }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="链接数据库名" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectDatabase }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="链接数据库类型" width="150">
        <template slot-scope="scope">
          {{ scope.row.dbConnectType }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" width="110">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ parseTime(new Date(scope.row.createTime) ,'{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建人" width="150">
        <template slot-scope="scope">
          {{ scope.row.createId }}
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
          <el-input v-model="temp.dbConnectPassword" placeholder="链接密码" show-password />
        </el-form-item>
        <el-form-item label="链接端口" prop="type">
          <el-input v-model="temp.dbConnectPort" placeholder="链接端口" />
        </el-form-item>
        <el-form-item label="链接数据库名" prop="type">
          <el-input v-model="temp.dbConnectDatabase" placeholder="链接数据库名" />
        </el-form-item>
        <el-form-item label="链接数据库类型" prop="type">
          <el-input v-model="temp.dbConnectType" placeholder="链接数据库类型【mysql/mssq】" />
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
  </div>
</template>

<script>
import { get_base_data_item_map } from '@/api/kkautogenerator/settings'
import { get_db_connect_page_list, deleteById, insert, update, get_details } from '@/api/kkautogenerator/db_connect_api'
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
      baseDataItemMapType: ['dbType'],
      dbTypeList: {},
      list: null,
      total: 0,
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
        pageSize: 10
      },
      dialogFormVisible: false,
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
      dialogStatus: 'insert'
    }
  },
  created() { // 初始化界面
    get_base_data_item_map({ queryTypeList: this.baseDataItemMapType }).then(response => {
      this.dbTypeList = response.data === null ? {} : response.data.queryTypeResult['dbType'.toLowerCase()]
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
    copyDataDialog(row) {
      this.dialogStatus = 'insert'
      get_details({ dbConnectCode: row.dbConnectCode }).then(response => {
        this.temp = Object.assign({}, response.data === null ? row : response.data) // copy obj
        this.temp.dbConnectCode = ''
        this.dialogFormVisible = true
      })
    },
    parseTime: parseTime,
    renderHeaderTip: renderHeaderTip
  }
}
</script>
