import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import loadingObj from '@/utils/loadingObj'

// create an axios instance
const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 60000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    config.isLoading = config.isLoading === undefined ? true : config.isLoading
    config.isPrompt = config.isPrompt === undefined ? true : config.isPrompt
    if (config.isLoading) {
      loadingObj.loadingShow()
    }
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['sid'] = getToken()
      // config.headers['Access-Control-Allow-Origin'] = '*'
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    if (response.config.isLoading) {
      loadingObj.loadingHide()
    }
    const res = response.data
    // console.log(res)
    // if the custom code is not 20000, it is judged as an error.
    if (res.result !== '200') {
      Message({
        message: res.desc || 'Error',
        type: 'error',
        duration: 5 * 1000,
        customClass: 'messageIndex'
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.result === '50008' || res.result === '50012' || res.result === '50014') {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.desc || 'Error'))
    } else {
      if (response.config.isPrompt) {
        Message({
          message: res.desc || '请求成功！',
          type: 'success',
          duration: 2 * 1000,
          customClass: 'messageIndex'
        })
      }
      return res
    }
  },
  error => {
    if (error.config.isLoading) {
      loadingObj.loadingHide()
    }
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000,
      customClass: 'messageIndex'
    })
    return Promise.reject(error)
  }
)

export default service
