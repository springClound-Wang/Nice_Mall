import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);
import container from '../components/container/container.vue'
//用户登录注册 忘记密码
import login_phone from '../components/login/login_phone.vue';
import login_name from '../components/login/login_name.vue';
import signup from '../components/login/signup.vue';
import login_sign from '../components/login/login_sign.vue';
import forget from '../components/login/forget';

//商家 登录注册
import login_shop_phone from '../components/login/login_shopphone';
import login_shop_name from '../components/login/login_shop_name';

import home from '../components/container/home'; //首页
import goods_list from '../components/container/goods_list'; //商品按大类型
import goods_detail from '../components/container/goods_detail'; //商品详情
import goods_item from  '../components/container/goods_item';//商品小类型


import other_container from '../components/car/other_container'//新导航内容
import goods_car from '../components/car/goods_car'//购物车
import goods_pay from '../components/car/goods_pay'; //提交    .
import goods_order from '../components/car/goods_order'; //结算
import goods_sel from '../components/car/goods_sel'; //订单

import shop_home from '../components/shop/shop_home'; //店铺首页导航
import shop_create from '../components/shop/shop_create'; //创建店铺
import shop_curd from '../components/shop/shop_curd'; //添加商品
import shop_index from  '../components/shop/shop_index'; //店铺首页
import shop_hot from '../components/shop/shop_hot';//热门分类
import shop_goods from  '../components/shop/shop_goods'; //商品列表


//个人中心
import personal_home from '../components/personal/personal_home';
import person_info from '../components/personal/person_info'
import person_address from '../components/personal/person_address'

//404
import not_found from '../components/404';
export default new Router({
  routes: [
    {
      path:'/',
      name:'container',
      component:container,
      children:[
        {path:'/',redirect:'/home'},
        {path:'home',name:'home',component:home},
        {path:'goods_list',name:'goods_list',component:goods_list},
        {path:'goods_detail',name:'goods_detail',component:goods_detail},
        {path:'goods_item',name:'goods_item',component:goods_item},
        {path:'not_found',name:'not_found',component:not_found} //404
      ]
    },
    {
      path:'/other_container',
      name:'other_container',
      component:other_container,
      children:[
        {path:'goods_car',name:'goods_list',component:goods_car},//购物车
        {path:'goods_order',name:'goods_list',component:goods_order}, //结算
        {path:'goods_pay',name:'goods_list',component:goods_pay}, //去支付
        {path:'goods_sel',name:'goods_list',component:goods_sel}, //订单
        {
          path:'/personal_home',
          name:'goods_list',
          component: personal_home,
          children:[
            {path:'person_info',name:'person_info',component:person_info},
            {path:'person_address',name:'person_address',component:person_address},
            {path:'not_found',name:'not_found',component:not_found} //404
          ]
        }, //个人中心
      ]
    },
    //用户
    {
      path:'/login_sign', //登录注册
      name:'login_sign',
      component:login_sign,
      children:[
        {path:'/',redirect:'/login_sign/login_phone'},
        {path:'login_phone',name:'login_phone', component:login_phone}, //手机号登录
        {path:'login_name',name:'login_name', component:login_name}, //用户名登录
        {path:'signup',name:'signup',component:signup}, //注册
        {path:'forget',name:'forget',component:forget} //忘记密码
      ]
    },
    //商家
    {
      path:'/login_sign', //登录注册
      name:'login_sign',
      component:login_sign,
      children:[
        {path:'/',redirect:'/login_sign/login_shopphone'},
        {path:'login_shop_phone', name:'login_shop_phone',component:login_shop_phone}, //手机号登录
        {path:'login_shop_name',name:'login_shop_name', component:login_shop_name}, //用户名登录
        {path:'signup',name:'signup',component:signup}, //注册
        {path:'forget',name:'forget',component:forget} //忘记密码
      ]
    },
    {path:'/shop_create', name:'shop_create',component:shop_create},//创建店铺
    {
      path:'/shop_home',
      name:'shop_home',
      component:shop_home,
      children:[
        {path:'/',redirect:'/shop_home/shop_index'},
        {path:'shop_curd',name:'shop_curd',component:shop_curd},//店铺管理
        {path:'shop_index',name:'shop_index',component:shop_index},//店铺首页
        {path:'shop_hot',name:'shop_hot',component:shop_hot},//热门分类
        {path:'shop_goods',name:'shop_goods',component:shop_goods},//热门分类
      ]
    },
  ]
})
