<template>
  <form v-on:submit.prevent v-if="$store.getters.authorized !== true && this.errorMessage!=''">
    <p class="error" v-if="errorMessage">{{ errorMessage }}</p>
    <input id="username" type="text" v-model="user.username" placeholder="Username"/>
    <input id="password" v-model="user.password" type="password" placeholder="Password"/>
    <button type="submit" v-on:click="doLoginAction()">Login</button>
    <hr/>
  </form>
</template>
<script>
  import http from '../utils/http'

  export default {
    data() {
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
        this.setUsername(this.user.username);
        this.setPassword(this.user.password);
        http.get("user/", this.user).then((message) => this.checkSuccessStatus(message.response)).catch(
          (error) => this.checkErrorStatus(error.response));
        if (this.errorMessage && this.errorMessage === '') {
          this.setAuthorized(true);
        }
      },
      checkErrorStatus: function (response) {
        this.errorMessage = '';
        if (!response) {
          return response;
        }
        if (response) {
          if (!response.status) {
            this.errorMessage = "Connection problems! "
          }
          if (response.status === 403) {
            this.errorMessage = "Incorrect login or password! "
          }
        }
        return response;
      },
      checkSuccessStatus: function (response) {
        this.errorMessage = '';
        return response;
      },
      setUsername(username) {
        this.$store.dispatch('setUsername', username);
      },
      setPassword(password) {
        this.$store.dispatch('setPassword', password);
      },
      setAuthorized: function (status) {
        this.$store.dispatch('setAuthorized', status);
      }
//      ,
//      getAuthorized: function () {
//        return this.$store.getters.authorized;
//      }
    },
    components: {}
  }
</script>

<style>
  .error {
    color: red;
  }
</style>
