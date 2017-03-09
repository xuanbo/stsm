<template>
  <div>
    <hr />
    <div class="container">
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>流程</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
    <div>
      <div v-if="show">
        <el-row class="processDefinition">
          <el-col :span="6" v-for="(processDefinition, index) in page.list" :key="processDefinition.id">
            <el-card>
              <img :src="processDefinition | processDefinitionDiagramUrlAdapter" class="image">
              <hr />
              <div>
                <span>{{ processDefinition.name }}</span>
                <div class="bottom clearfix">
                  <time class="time">{{ '流程定义version：' + processDefinition.version }}</time>
                  <el-button type="text" class="button" @click="start(processDefinition.id)">启动流程</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <div class="text-right" v-if="show">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="page.current"
                       :page-sizes="[8, 12, 16, 20, 40, 80]"
                       :page-size="page.size"
                       layout="total, sizes, prev, pager, next, jumper" :total="page.count">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'processIndex',
    data () {
      return {
        page: {
          current: 1,
          size: 8,
          count: 0
        }
      }
    },
    mounted () {
      this.getData()
    },
    computed: {
      show () {
        return !!this.page.list
      }
    },
    filters: {
      processDefinitionDiagramUrlAdapter (processDefinition) {
        return '/flowable/processDefinition/' + processDefinition.id + '/diagram'
      }
    },
    methods: {
      getData () {
        let params = {
          userId: this.$core.getUser().id,
          current: this.page.current,
          size: this.page.size
        }
        this.$http.get('/flowable/processDefinition', {params: params}).then(resp => {
          if (resp.data.code === 200) {
            console.log(resp.data.data)
            this.page = resp.data.data
          }
        }, resp => {
          console.log(resp.data.data)
        })
      },
      start (id) {
        this.$router.push({path: '/process/' + id + '/start'})
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
      }
    }
  }
</script>

<style scoped>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .clearfix:before, .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .el-card {
    margin: 5px;
    cursor: pointer;
    border: 3px solid #dfe5a6;
  }

  .el-card:hover {
    border: 3px solid #8492A6;
  }

  .image {
    width: 100%;
  }
</style>
