<template>
  <div id="app">
    <img src="./assets/logo.png">
    <!--<HelloWorld/>-->
    <LoginForm/>
    <!--<login-form></login-form>-->
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'
import LoginForm from './components/LoginForm'

export default {
  name: 'app',
  components: {
    HelloWorld,
    LoginForm
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
