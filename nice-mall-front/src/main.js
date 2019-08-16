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

//轮播图 组件
import { Swipe, SwipeItem } from 'mint-ui';
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);

// 手动安装VueRouter
import VueRouter from 'vue-router';
Vue.use(VueRouter);
import routerObj from "./router";

import app from './App.vue';
import Axios from 'axios';
Vue.prototype.$http = Axios;
const vm = new Vue({
    el: '#app',
    data: {},
    render: c => c(app),
    // 将路由对象挂载到vm上
    router:routerObj
});