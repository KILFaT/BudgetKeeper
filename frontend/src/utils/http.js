'use strict';

import axios from "axios";
import {APP_URL} from '../store/env'

axios.defaults.xsrfCookieName = 'csrftoken';
axios.defaults.xsrfHeaderName = 'X-CSRFToken';

const JSON_TYPE = 'application/json';

export default {
  post(url, USER, data) {
    return axios({
                   method: 'post',
                   baseURL: APP_URL,
                   url,
                   data: data,
                   timeout: 10000,
                   withCredentials: true,
                   headers: {
                     'Content-Type': JSON_TYPE
                   },
                   auth: {
                     username: USER.username,//'admin',//env.USERNAME,
                     password: USER.password//'password123'//env.PASSWORD
                   }
                 }).then(
      function (response) {
        console.log(response);
        return response;
      })
  },
  get(url, USER, params) {
    return axios({
                   method: 'get',
                   baseURL: APP_URL,
                   url,
                   params,
                   withCredentials: true,
                   timeout: 10000,
                   headers: {
                     Accept: JSON_TYPE
                   },
                   auth: {
                     username: USER.username,//'admin',//env.USERNAME,
                     password: USER.password//'password123'//env.PASSWORD
                   }

                 }).then(
      function (response) {
        console.log(response);
        return response;
      })
  }
}
