<template>
  <div>
    <hr />
    <div class="container">
      <el-breadcrumb separator="/" class="pull-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/process' }">流程</el-breadcrumb-item>
        <el-breadcrumb-item>开启流程</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr />
    <div class="container">
      <el-form :model="ruleForm" :rules="rules" label-width="180px" ref="ruleForm" class="demo-ruleForm">
        <el-form-item label="测试清单名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <!-- 具体内容 -->
        <el-form-item label="具体内容" prop="content">
          <el-input type="textarea" v-model="ruleForm.content"></el-input>
        </el-form-item>
        <!-- 任务描述 -->
        <el-form-item label="任务描述" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>
        <!-- 指派团队成员 -->
        <el-form-item label="指派团队成员" prop="assignee">
          <el-cascader id="searchAssignee" placeholder="试试搜索：zhangsan" :options="assignees"
                       filterable :debounce=1000 @change="setAssignee"></el-cascader>
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
    name: 'processStart',
    data () {
      return {
        ruleForm: {
          name: '',
          content: '',
          description: '',
          assignee: ''
        },
        assignees: [],
        file: null,
        fileName: '',
        rules: {
          name: [
            { required: true, message: '请填写测试清单名称', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '请填写具体内容', trigger: 'blur' }
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
        self.assigneeChange(searchAssignee)
      })
    },
    methods: {
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.startProcess()
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
        this.assignees = [{label: '李四', value: 'lisi'}, {label: '王五', value: 'wangwu'}]
      },
      setAssignee (val) {
        this.ruleForm.assignee = val[0]
      },
      startProcess () {
        let formData = new FormData()
        // 开启的流程
        formData.append('processDefinitionId', this.$route.params.id)
        formData.append('name', this.ruleForm.name)
        formData.append('content', this.ruleForm.content)
        formData.append('description', this.ruleForm.description)
        // 指派下一个人
        formData.append('assignee', this.ruleForm.assignee)
        formData.append('file', this.file)
        console.log(formData)
        let options = {
          emulateJSON: true,
          headers: {'content-type': 'multipart/form-data'}
        }
        this.$http.post('/flowable/process/start', formData, options).then(resp => {
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
