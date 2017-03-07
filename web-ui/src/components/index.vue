<template>
  <div>
    <h3>代办任务</h3>
    <hr />
    <el-row>
      <el-col :span="8" v-for="(task, index) in tasks" :key="task.id">
        <el-card>
          <div>
            <span>{{ task.name }}</span>
            <div class="bottom clearfix">
              <time class="time">{{ task.createTime | parseToDate }}</time>
              <el-button type="text" class="button">查看任务</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script>
  export default {
    name: 'index',
    data () {
      return {
        msg: '你有以下任务需要办理',
        activeNames: ['1'],
        tasks: []
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
        let username = this.$core.getUser().username
        this.$http.get('/flowable/task/' + username).then(resp => {
          console.log(resp.data)
          if (resp.data.code === 200) {
            this.tasks = resp.data.data.list
          }
        }, resp => {
          console.log(resp.data)
        })
      },
      handleChange (activeNames) {
        console.log(activeNames)
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
  }
</style>
