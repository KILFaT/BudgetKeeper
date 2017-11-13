import Vue from "vue/dist/vue.esm";
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store(
  {
    state: {
      username: '',
      password: '',
      authorized: false
    },
    mutations: {
      SET_USERNAME(state, name) {
        state.username = name;
      },
      CLEAR_USERNAME(state) {
        state.username = '';
      },
      SET_PASSWORD(state, password) {
        state.password = password;
      },
      CLEAR_PASSWORD(state) {
        state.password = '';
      },
      SET_AUTHORIZED(state, status) {
        state.authorized = status;
      }
    },
    actions: {
      setUsername({commit}, name) {
        commit('SET_USERNAME', name)
      },
      clearUsername({commit}) {
        commit('CLEAR_USERNAME');
      },
      setPassword({commit}, password) {
        commit('SET_PASSWORD', password)
      },
      clearPassword({commit}) {
        commit('CLEAR_PASSWORD');
      },
      setAuthorized({commit}, status) {
        commit('SET_AUTHORIZED', status);
      }
    },
    getters: {
      username: state => state.username,
      password: state => state.password,
      authorized: state => state.authorized
    }
  }
);
