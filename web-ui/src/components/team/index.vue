<template>
  <div>
    <hr />
    <div class="container">
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item>我的工作台</el-breadcrumb-item>
        <el-breadcrumb-item>我的团队</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
    <div>
      <el-table :data="tableData" border>
        <el-table-column prop="id" label="ID" width="180"></el-table-column>
        <el-table-column prop="name" label="团队名称" width="180"></el-table-column>
        <el-table-column prop="description" label="团队描述" width="250"></el-table-column>
        <el-table-column prop="createDate" label="创建时间" width="200">
          <template scope="scope">
            <el-icon name="time"></el-icon>
            <span style="margin-left: 10px">{{ scope.row.createDate | parseToDate }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateDate" label="最后一次更新时间" width="200">
          <template scope="scope">
            <el-icon name="time"></el-icon>
            <span style="margin-left: 10px">{{ scope.row.updateDate | parseToDate }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template scope="scope">
            <el-button type="success" size="small" @click="showTeamUsers(scope.$index)" icon="el-icon-view">
              查看团队成员 <i class="el-icon-view"></i>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="text-right" v-if="show">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="page.current"
                       :page-sizes="[10, 15, 20, 25]"
                       :page-size="page.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="page.count">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'teamIndex',
    data () {
      return {
        page: {
          current: 1,
          size: 10,
          count: 0
        },
        tableData: []
      }
    },
    computed: {
      show () {
        return this.tableData.length !== 0
      }
    },
    mounted () {
      this.getData()
    },
    filters: {
      parseToDate (val) {
        return !val ? '无' : new Date(val).toLocaleString()
      }
    },
    methods: {
      getData () {
        let params = {
          userId: this.$core.getUser().id,
          current: this.page.current,
          size: this.page.size
        }
        this.$http.get('/team/search', {params: params}).then(resp => {
          console.log(resp.data.data)
          if (resp.data.code === 200) {
            this.page = resp.data.data
            this.tableData = this.page.list
          }
        }, resp => {
          console.log(resp.data)
        })
      },
      handleSizeChange (val) {
        this.page.size = val
        console.log(`每页 ${val} 条`)
        this.getData()
      },
      handleCurrentChange (val) {
        this.page.current = val
        console.log(`当前页: ${val}`)
        this.getData()
      },
      showTeamUsers (index) {
        let id = this.tableData[index].id
        this.$router.push({path: '/team/' + id})
      }
    }
  }
</script>
