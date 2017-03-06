<template>
  <div>
    <el-menu theme="dark" :default-active="activeIndex" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
      </el-submenu>
      <el-menu-item index="3">我的任务</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: 'index',
    data () {
      return {
        msg: 'Welcome to App',
        activeIndex: '1'
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>

<style scoped></style>
