import axios from 'axios'
import router from "@/router";
import Cookies from 'js-cookie'

const request = axios.create({
  baseURL: 'http://localhost:8888/api',
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8';

  const adminJson = Cookies.get('admin')
  if (adminJson) {
    // 设置请求头
    config.headers['token'] = JSON.parse(adminJson).token
  }
  return config
}, error => {
  return Promise.reject(error)
});

// 响应拦截器
request.interceptors.response.use(
  response => {
    let res = response.data;
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    if (res.code === '401') {
      router.push('/login')
    }
    return res;
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  }
)


export default request