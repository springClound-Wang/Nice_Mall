import VueRouter from "vue-router";
import container from './compotents/container/container.vue'
//登录注册 忘记密码
import login_phone from './compotents/login/login_phone.vue';
import login_name from './compotents/login/login_name.vue';
import signup from './compotents/login/signup.vue';
import login_sign from './compotents/login/login_sign.vue';
import forget from './compotents/login/forget.vue';

import login_shop_phone from './compotents/login/login_shopphone.vue';
import login_shop_name from './compotents/login/login_shop_name.vue';

import home from './compotents/container/home.vue'; //首页
import goods_list from './compotents/container/goods_list.vue'; //商品按大类型
import goods_detail from './compotents/container/goods_detail.vue'; //商品详情
import goods_item from  './compotents/container/goods_item.vue';//商品小类型


import other_container from './compotents/other/other_container.vue'//新导航内容
import goods_car from './compotents/other/goods_car.vue'//购物车
import goods_pay from './compotents/other/goods_pay.vue'; //提交    .
import goods_order from './compotents/other/goods_order.vue'; //结算

import shop_home from './compotents/shop/shop_create.vue';
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
                {path:'goods_item',component:goods_item}
            ]
        },
        {
            path:'/other_container',
            component:other_container,
            children:[
                {path:'goods_car',component:goods_car},
                {path:'goods_order',component:goods_order},
                {path:'goods_pay',component:goods_pay},
            ]
        },
        //用户
        {
            path:'/login_sign', //登录注册
            component:login_sign,
            children:[
                {path:'/',redirect:'/login_sign/login_phone'},
                {path:'login_phone', component:login_phone}, //手机号登录
                {path:'login_name', component:login_name}, //用户名登录
                {path:'signup',component:signup}, //注册
                {path:'forget',component:forget} //忘记密码
            ]
        },
        //商家
        {
            path:'/login_sign', //登录注册
            component:login_sign,
            children:[
                {path:'/',redirect:'/login_sign/login_shopphone'},
                {path:'login_shop_phone', component:login_shop_phone}, //手机号登录
                {path:'login_shop_name', component:login_shop_name}, //用户名登录
                {path:'signup',component:signup}, //注册
                {path:'forget',component:forget} //忘记密码
            ]
        },
        {
            path:'/shop_home',
            component:shop_home
        }

    ],
    linkActiveClass:'myactive', //自定义路由切换的类
});

//暴露路由对象
export default routerObj;