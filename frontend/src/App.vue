<template>
  <div id="app" class="container">
    <img src="./assets/logo.png">
    <LoginForm></LoginForm>
    <HelloWorld></HelloWorld>
  </div>
</template>

<script>
  import HelloWorld from './components/HelloWorld.vue'
  import LoginForm from './components/LoginForm.vue'

  export default {
    name: 'app',
    components: {
      LoginForm,
      HelloWorld
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
