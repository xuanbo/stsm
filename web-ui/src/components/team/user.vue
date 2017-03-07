<template>
  <div>
    <hr />
    <div class="container">
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>我的工作台</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/team' }">我的团队</el-breadcrumb-item>
        <el-breadcrumb-item>团队成员</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
    <div>
      <el-table :data="tableData" border>
        <el-table-column prop="id" label="ID" width="180"></el-table-column>
        <el-table-column prop="name" label="用户名" width="180">
          <template scope="scope">
            <div class="name-wrapper">
              <el-tag>{{ scope.row.name }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="username" label="账号"></el-table-column>
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
    name: 'teamUser',
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
    methods: {
      getData () {
        let params = {
          teamId: this.$route.params.id,
          current: this.page.current,
          size: this.page.size
        }
        this.$http.get('/user/search', {params: params}).then(resp => {
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
        console.log(index)
      }
    }
  }
</script>
