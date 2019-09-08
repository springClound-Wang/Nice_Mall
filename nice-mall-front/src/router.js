import VueRouter from "vue-router";
import container from './compotents/container/container.vue'
//用户登录注册 忘记密码
import login_phone from './compotents/login/login_phone.vue';
import login_name from './compotents/login/login_name.vue';
import signup from './compotents/login/signup.vue';
import login_sign from './compotents/login/login_sign.vue';
import forget from './compotents/login/forget.vue';

//商家 登录注册
import login_shop_phone from './compotents/login/login_shopphone.vue';
import login_shop_name from './compotents/login/login_shop_name.vue';

import home from './compotents/container/home.vue'; //首页
import goods_list from './compotents/container/goods_list.vue'; //商品按大类型
import goods_detail from './compotents/container/goods_detail.vue'; //商品详情
import goods_item from  './compotents/container/goods_item.vue';//商品小类型


import other_container from './compotents/car/other_container.vue'//新导航内容
import goods_car from './compotents/car/goods_car.vue'//购物车
import goods_pay from './compotents/car/goods_pay.vue'; //提交    .
import goods_order from './compotents/car/goods_order.vue'; //结算


import shop_home from './compotents/shop/shop_home.vue'; //店铺首页导航
import shop_create from './compotents/shop/shop_create.vue'; //创建店铺
import shop_curd from './compotents/shop/shop_curd.vue'; //添加商品
import shop_index from  './compotents/shop/shop_index.vue'; //店铺首页
import shop_hot from './compotents/shop/shop_hot.vue';//热门分类

import goods_sel from './compotents/car/goods_sel.vue'; //订单

//个人中心
import personal_home from './compotents/personal/personal_home.vue';
import person_info from './compotents/personal/person_info.vue'
import person_address from './compotents/personal/person_address.vue'

//404
import not_found from './compotents/404.vue';

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
                {path:'not_found',component:not_found} //404
            ]
        },
        {
            path:'/other_container',
            component:other_container,
            children:[
                {path:'goods_car',component:goods_car},//购物车
                {path:'goods_order',component:goods_order}, //结算
                {path:'goods_pay',component:goods_pay}, //去支付
                {path:'goods_sel',component:goods_sel}, //订单
                {
                    path:'/personal_home',
                    component: personal_home,
                    children:[
                        {path:'person_info',component:person_info},
                        {path:'person_address',component:person_address},
                        {path:'not_found',component:not_found} //404
                    ]
                }, //个人中心
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
        {path:'/shop_create', component:shop_create},//创建店铺
        {
            path:'/shop_home',
            component:shop_home,
            children:[
                {path:'/',redirect:'/shop_home/shop_index'},
                {path:'shop_curd',component:shop_curd},//店铺管理
                {path:'shop_index',component:shop_index},//店铺首页
                {path:'shop_hot',component:shop_hot},//热门分类
            ]
        },

    ],
    linkActiveClass:'myactive', //自定义路由切换的类
});

//暴露路由对象
export default routerObj;