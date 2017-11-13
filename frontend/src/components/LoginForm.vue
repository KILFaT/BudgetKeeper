<template>
  <form v-on:submit.prevent>
    <p class="error" v-if="errorMessage">{{ errorMessage }}</p>
    <input id="username" type="text" v-model="user.username" placeholder="Username"/>
    <input id="password" v-model="user.password" type="password" placeholder="Password"/>
    <button type="submit" v-on:click="doLoginAction()">Login</button>
    <hr/>
  </form>
</template>

<script>
  import http from '../utils/http'
  import env from '../utils/env'

  export default {
    data () {
      return {
        user: {
          username: null,
          password: null
        },
        errorMessage: null
      }
    },
    methods: {
      doLoginAction: function () {
        env.USERNAME = this.user.username;
        env.PASSWORD = this.user.password;
        http.get("user/").then((response) => this.checkStatus(response));
      },
      checkStatus: function (response) {
        if (response) {
          if (response.status === 401 || response.status === 403) {
            this.errorMessage = "Incorrect login or password!"
          }
          if (response.status === 404) {
            this.errorMessage = "Connection problems!"
          }
        }
        return response;
      }
    },
    components: {}
  }
</script>

<style>
  .error {
    color: red;
  }
</style>
