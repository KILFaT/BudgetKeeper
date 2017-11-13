'use strict'

import axios from "axios";

axios.defaults.xsrfCookieName = 'csrftoken';
axios.defaults.xsrfHeaderName = 'X-CSRFToken';

function checkCode(res) {
  if (res.status === -404) {
    alert(res.msg)
  }
  if (res.data && (!res.data.success)) {
    alert(res.data.error_msg)
  }
  return res
}

const JSON_TYPE = 'application/json';

export default {
  post (url, data) {
    return axios({
                   method: 'post',
                   baseURL: APP_URL,
                   url,
                   data: data,
                   timeout: 10000,
                   withCredentials: false,
                   headers: {
                     'Access-Control-Allow-Origin:': '*',
                     'Content-Type': JSON_TYPE
                   },
                   auth: {
                     username: 'admin',//env.USERNAME,
                     password: 'password123'//env.PASSWORD
                   }
                 }).then(
      (response) => {
        return checkStatus(response)
      }
    ).then(
      (res) => {
        return checkCode(res)
      }
    )
  },
  get (url, params) {
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
                     username: 'admin',//env.USERNAME,
                     password: 'password123'//env.PASSWORD
                   }

                 }).then(
      function (response) {
        console.log(response);
      })
    // ).then(
    //   (res) => {
    //     return checkCode(res)
    //   }
    // )
  }
}
