import Vue from 'vue';


//导入 mint-UI组件
import MintUI from 'mint-ui';
import 'mint-ui/lib/style.css';

//导入mui样式
import './lib/mui/css/mui.min.css'

import './css/index.css'
import './font/font1/iconfont.css'
import './font/font2/iconfont.css'
import './font/font3/iconfont.css'

import '../node_modules/bootstrap/dist/css/bootstrap.css'
import '../node_modules/jquery/dist/jquery.min.js'
//轮播图 复选框 组件
import { Swipe, SwipeItem, Lazyload,Cell } from 'mint-ui';
Vue.use(Lazyload); //懒加载
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);
//引入 ElementUI组件
import ElementUI from 'element-ui';
Vue.use(ElementUI);

//切换框
import { TabContainer, TabContainerItem } from 'mint-ui';
Vue.component(TabContainer.name, TabContainer);
Vue.component(TabContainerItem.name, TabContainerItem);
Vue.component(Cell.name, Cell);


// 手动安装VueRouter
import VueRouter from 'vue-router';
Vue.use(VueRouter);
import routerObj from "./router";

// // 消息提示
import { Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.component(Message.name, Message);
Vue.prototype.$message = Message;


//发送请求
import app from './App.vue';
import Axios from 'axios';
Vue.prototype.$http = Axios;
Axios.defaults.baseURL='http://localhost:3030';
// http request 拦截器
// Axios.interceptors.request.use(
//     config => {
//         config.headers = {
//             'Content-Type': 'application/json'
//         };
//         return config;
//     },
//     err => {
//         return Promise.reject(err)
//     }
// );

//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(res =>{
    //对响应数据做些事
    console.log(res.data.status);
    if(!res.data.status){
        console.log('ok'+res.data.message);
        // return Promise.reject(res)
    }
    return res
}, error => {
    if(error.response.status === 401) {
        // 401 说明 token 验证失败
        // 可以直接跳转到登录页面，重新登录获取 token
        console.log(error.response.data.message);
    } else if (error.response.status === 500) {
        // 服务器错误
        // do something
        return Promise.reject(error.response.data)
    }
    // 返回 response 里的错误信息
    return Promise.reject(error.response.data)
});



//跳转到页面顶部
routerObj.afterEach(() => {
    window.scrollTo(0,0);
});
const vm = new Vue({
    el: '#app',
    data: {},
    render: c => c(app),
    // 将路由对象挂载到vm上
    router:routerObj
});