import axios from 'axios'
import {Message} from 'view-design';
import 'view-design/dist/styles/iview.css';

// axios.defaults.baseURL = 'http://47.115.46.57:9999/erhuo/'
axios.defaults.baseURL = 'http://localhost:9999/erhuo';


axios.interceptors.request.use(config => {
  // 设置统一的请求头
  if(window.sessionStorage.getItem('token')){
    config.headers.Authorization = window.sessionStorage.getItem('token');
  }
  return config
},err => {
  throw err
})

axios.interceptors.response.use(res => {
  const {code, message} = res.data;
  if(code === 200)
    return res;
  
  Message.error(message);
  // window.sessionStorage.removeItem('token');
  // router.push('/login');
}, error => {
  throw error
})

export default axios;
