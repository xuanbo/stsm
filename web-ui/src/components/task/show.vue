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
    <div class="container">
      <el-collapse v-model="activeNames" v-if="hasTask">
        <!-- 发起流程内容 -->
        <el-collapse-item title="发起流程内容" name="1" v-if="!hasNoTest">
          <div>
            <div>
              <label>测试清单名称：</label>
              <span>{{ test.name }}</span>
            </div>
            <div>
              <label>流程发起人：</label>
              <span>{{ test.userId }}</span>
            </div>
            <div>
              <label>流程发起时间：</label>
              <span>{{ test.createDate | parseToDate }}</span>
            </div>
            <div>
              <label>测试具体内容：</label>
              <pre>{{ test.content | noData }}</pre>
            </div>
            <div>
              <label>测试清单描述：</label>
              <pre>{{ test.description | noData }}</pre>
            </div>
            <div>
              <label>附件：</label>
              <span>{{ testFile.originFileName }}</span>
              <a :href="'/file/'+ testFile.id + '/download'" class="btn btn-info btn-sm">
                下载附件
              </a>
            </div>
          </div>
        </el-collapse-item>
        <!-- 上一节点任务 -->
        <el-collapse-item title="上一节点任务" name="2" v-if="!hasNoBeforeTaskForm">
          <div>
            <div>
              <label>任务办理人：</label>
              <span>{{ beforeTaskForm.userId }}</span>
            </div>
            <div>
              <label>任务状态：</label>
              <span>{{ beforeTaskForm.code | processCode }}</span>
            </div>
            <div>
              <label>具体描述：</label>
              <span>{{ beforeTaskForm.description }}</span>
            </div>
            <div>
              <label>完成时间：</label>
              <span>{{ beforeTaskForm.createDate | parseToDate }}</span>
            </div>
            <div>
              <label>附件：</label>
              <span>{{ beforeTaskFormFile.originFileName }}</span>
              <a :href="'/file/'+ beforeTaskFormFile.id + '/download'" class="btn btn-info btn-sm">
                下载附件
              </a>
            </div>
          </div>
        </el-collapse-item>
        <!-- 当前任务 -->
        <el-collapse-item title="当前任务" name="3">
          <div>
            <div>
              <label>任务名称：</label>
              <span>{{ task.name }}</span>
            </div>
            <div>
              <label>任务执行者：</label>
              <span>{{ task.assignee }}</span>
            </div>
            <div>
              <label>任务分类：</label>
              <span>{{ task.category }}</span>
            </div>
            <div>
              <label>任务优先级：</label>
              <span>{{ task.priority }}</span>
            </div>
            <div>
              <div>
                <img src="../../assets/logo.png" class="img-responsive">
              </div>
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
      <div class="container" style="margin-top: 10px;" v-if="hasTask">
        <div class="text-center">
          <el-button type="primary" @click="inputTaskForm">填写任务</el-button>
        </div>
      </div>
      <div v-else>
        <h3 class="text-center">任务不存在或已办理</h3>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        activeNames: ['3'],
        taskId: this.$route.params.id,
        hasTask: true,
        task: {name: '', assignee: '', category: '', priority: ''},
        test: null,
        testFile: {originFileName: ''},
        beforeTaskForm: null,
        beforeTaskFormFile: {originFileName: ''}
      }
    },
    computed: {
      hasNoTest () {
        return !this.test
      },
      hasNoBeforeTaskForm () {
        return !this.beforeTaskForm
      }
    },
    mounted () {
      this.fetchTask()
    },
    filters: {
      noData (val) {
        return !val ? '无' : val
      },
      parseToDate (val) {
        return !val ? '无' : new Date(val).toLocaleString()
      },
      processCode (val) {
        let code
        switch (val) {
          case '200' :
            code = '正常'
            break
          case '500' :
            code = '不正常'
            break
          default:
            code = '不正常'
        }
        return code
      }
    },
    methods: {
      fetchTask () {
        this.$http.get('/flowable/task/' + this.taskId).then(resp => {
          console.log(resp.data)
          if (resp.data.code === 200) {
            this.task = resp.data.data
            if (this.task) {
              this.test = this.task.testDTO
              this.beforeTaskForm = this.task.beforeTaskFormDTO
              // 获取附件
              this.fetchFile()
            } else {
              this.hasTask = false
            }
          } else {
            this.$message.error('获取任务失败')
          }
        }, resp => {
          console.log(resp.data)
        })
      },
      fetchFile () {
        if (!this.hasNoTest) {
          this.$http.get('/file/findByTestId?testId=' + this.test.id).then(resp => {
            console.log(resp.data)
            if (resp.data.code === 200) {
              this.testFile = resp.data.data
            } else {
              this.$message.error('获取附件失败')
            }
          }, resp => {
            console.log(resp.data)
          })
        }
        if (!this.hasNoBeforeTaskForm) {
          this.$http.get('/file/findByTaskFormId?taskFormId=' + this.beforeTaskForm.id).then(resp => {
            console.log(resp.data)
            if (resp.data.code === 200) {
              this.beforeTaskFormFile = resp.data.data
            } else {
              this.$message.error('获取附件失败')
            }
          }, resp => {
            console.log(resp.data)
          })
        }
      },
      inputTaskForm () {
        this.$router.push({path: '/task/' + this.taskId + '/confirm'})
      }
    }
  }
</script>
