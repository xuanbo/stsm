<template>
  <div>
    <hr />
    <div class="container">
      <el-button size="small" icon="arrow-left" @click="goBack">{{ back }}</el-button>
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/task' }">我的任务</el-breadcrumb-item>
        <el-breadcrumb-item>填写任务</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
    <div class="container">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="180px" class="demo-ruleForm">
        <el-form-item label="状态" prop="code">
          <el-select v-model="ruleForm.code" placeholder="请选择状态">
            <el-option label="正常" value="200"></el-option>
            <el-option label="不正常" value="500"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="具体描述" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>
        <!-- 指派团队成员 -->
        <el-form-item label="指派团队成员" prop="assignee">
          <el-cascader id="searchAssignee" placeholder="搜索账号：zhangsan" :options="assignees"
                       filterable :debounce=1000 :clearable="true" @change="setAssignee"></el-cascader>
        </el-form-item>
        <!-- 选择附件 -->
        <el-form-item>
          <el-button @click="filePicker">选择附件<i class="el-icon-upload el-icon--right"></i></el-button>
          <span>{{ fileName }}</span>
          <input type="file" id="file" @change="fileChange" style="display:none" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import $ from 'jquery'

  export default {
    name: 'taskConfirm',
    data () {
      return {
        back: '返回上一页',
        taskId: this.$route.params.id,
        ruleForm: {
          code: '',
          description: '',
          assignee: ''
        },
        assignees: [],
        file: null,
        fileName: '',
        rules: {
          code: [
            { required: true, message: '请选择状态', trigger: 'change' }
          ],
          description: [
            { required: true, message: '请输入具体描述', trigger: 'blur' }
          ],
          assignee: [
            { required: true, message: '请选择团队成员', trigger: 'blur' }
          ]
        }
      }
    },
    mounted () {
      const self = this
      $('#searchAssignee .el-input__inner').on('input', (e) => {
        let searchAssignee = e.target.value
        console.log(searchAssignee)
        if (searchAssignee) {
          self.assigneeChange(searchAssignee)
        }
      })
    },
    methods: {
      goBack () {
        this.$router.back()
      },
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.completeTaskForm()
          } else {
            return false
          }
        })
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      filePicker () {
        $('#file').click()
      },
      fileChange (e) {
        const file = e.target.files[0]
        this.file = file
        this.fileName = file.name
      },
      // 搜索团队成员改变
      assigneeChange (searchAssignee) {
        console.log(searchAssignee)
        let params = {
          usernameLike: searchAssignee
        }
        this.$http.get('/user/searchByUsernameLike', {params: params}).then(resp => {
          let users = resp.data.data
          users = !users ? [] : users
          let assignees = []
          for (let i = 0; i < users.length; ++i) {
            assignees.push({value: users[i].username, label: users[i].name + '(' + users[i].username + ')'})
          }
          this.assignees = assignees
        }, resp => {
          console.log(resp.data)
          this.assignees = []
        })
      },
      setAssignee (val) {
        this.ruleForm.assignee = val[0]
      },
      completeTaskForm () {
        let formData = new FormData()
        // 开启的流程
        formData.append('code', this.ruleForm.code)
        formData.append('description', this.ruleForm.description)
        formData.append('taskId', this.taskId)
        // 指派下一个人
        formData.append('assignee', this.ruleForm.assignee)
        // 附件
        formData.append('file', this.file)
        let options = {
          emulateJSON: true,
          headers: {'content-type': 'multipart/form-data'}
        }
        this.$http.post('/flowable/process/completeTask', formData, options).then(resp => {
          console.log(resp.data)
          if (resp.data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            // 重置表单
            this.resetForm('ruleForm')
            this.file = null
            this.fileName = ''
            this.assignees = []
          }
        }, resp => {
          console.log(resp.data)
          this.$message.error('操作失败')
        })
      }
    }
  }
</script>
