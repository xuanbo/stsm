<template>
  <div id="app">
    <!-- top -->
    <top></top>
    <!-- main -->
    <transition>
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
  import header from './components/header'

  export default {
    name: 'app',
    components: {
      top: header
    }
  }
</script>

<style>
  a, a:hover {
    text-decoration: none;
  }
</style>
