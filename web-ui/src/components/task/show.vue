<template>
  <div>
    <hr />
    <div class="container">
      <span>{{ '任务编号：' + taskId }}</span>
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/task' }">我的任务</el-breadcrumb-item>
        <el-breadcrumb-item>查看任务</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
  </div>t
</template>

<script>
  export default {
    data () {
      return {
        taskId: this.$route.params.id,
        task: null
      }
    },
    mounted () {
      this.fetchTask()
    },
    methods: {
      fetchTask () {
        this.$http.get('/flowable/task/' + this.taskId).then(resp => {
          if (resp.data.code === 200) {
            this.task = resp.data.data
          } else {
            this.$message.error('获取任务失败')
          }
        }, resp => {
          console.log(resp.data)
        })
      }
    }
  }
</script>
