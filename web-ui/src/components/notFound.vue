<template>
  <div class="notFound">
    <h1 class="text-center">{{ msg }}</h1>
    <div class="text-center">
      <router-link to="/" tag="el-button" type="success">{{ backToIndex }}</router-link>
      <el-button type="primary" icon="arrow-left" @click="goBack">{{ back }}</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'notFound',
    data () {
      return {
        msg: '找不到页面!',
        backToIndex: '返回首页',
        back: '返回上一页'
      }
    },
    methods: {
      goBack () {
        this.$router.back()
      }
    }
  }
</script>

<style scoped>
  .notFound {
    margin-top: 10%;
  }
</style>
