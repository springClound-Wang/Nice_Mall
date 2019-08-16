import VueRouter from "vue-router";
import container from './compotents/container/container.vue'
import login from './compotents/login/login.vue';
import signup from './compotents/login/signup.vue';
import login_sign from './compotents/login/login_sign.vue';
import forget from './compotents/login/forget.vue';

import home from './compotents/container/home.vue';
import goods_list from './compotents/container/goods_list.vue';
import goods_detail from './compotents/container/goods_detail.vue';
import goods_item from  './compotents/container/goods_item.vue';
// 创建路由对象
let routerObj = new VueRouter({
    routes: [
        {
            path:'/',
            component:container,
            children:[
                {path:'/',redirect:'/home'},
                {path:'home',component:home},
                {path:'goods_list',component:goods_list},
                {path:'goods_detail',component:goods_detail},
                {path:'goods_item',component:goods_item},
            ]
        },
        {
            path:'/login_sign', //登录注册
            component:login_sign,
            children:[
                {path:'/',redirect:'/login_sign/login'},
                {path:'login', component:login}, //登录
                {path:'signup',component:signup}, //注册
                {path:'forget',component:forget} //忘记密码
            ]
        },

    ],
    linkActiveClass:'myactive', //自定义路由切换的类
});

//暴露路由对象
export default routerObj;