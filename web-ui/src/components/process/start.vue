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
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="180px" class="demo-ruleForm">
        <el-form-item label="测试清单名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="具体内容" prop="content">
          <el-input type="textarea" v-model="ruleForm.content"></el-input>
        </el-form-item>
        <el-form-item label="任务描述" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>
        <div>
          <input type="file" @change="fileChange" />
        </div>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'processStart',
    data () {
      return {
        ruleForm: {
          name: '',
          content: '',
          description: ''
        },
        file: null,
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '请填写活具体内容', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.startProcess()
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm (formName) {
        this.$refs[formName].resetFields()
      },
      fileChange (e) {
        let file = e.target.files[0]
        this.file = file
      },
      startProcess () {
        let formData = new FormData()
        formData.append('name', this.ruleForm.name)
        formData.append('content', this.ruleForm.content)
        formData.append('description', this.ruleForm.description)
        formData.append('file', this.file)
        console.log(formData)
        this.$http.post('/flowable/process/start', formData, {emulateJSON: true, headers: {'content-type': 'multipart/form-data'}}).then(resp => {
          console.log(resp.data)
        }, resp => {
          console.log(resp.data)
        })
      }
    }
  }
</script>
