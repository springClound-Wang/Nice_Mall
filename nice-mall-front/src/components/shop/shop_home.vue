<template>
    <div id="shop_home_container">
        <div  class="nav_list">
            <el-menu default-active="1-4-1"
                     style="min-height: 1200px;"
                     class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
                <el-menu-item index="1" >
                    <template>
                        <i class="iconfont icon-qiyemenhu" :label="false"></i>
                        <router-link to="/shop_home/shop_index"><span>首页</span></router-link>
                    </template>
                </el-menu-item>

                <el-submenu index="2" >
                    <template slot="title">
                        <i class="iconfont icon-shangpin"></i>
                        <span slot="title">商品</span>
                    </template>
                    <el-menu-item index="2-1">
                        <router-link to="/shop_home/shop_goods"><i class="iconfont icon-liebiao"></i>商品列表</router-link>
                    </el-menu-item>
                    <el-menu-item index="2-2">
                        <router-link to="/shop_home/shop_curd"><i class="iconfont icon-new"></i>添加商品</router-link>
                    </el-menu-item>
                    <el-menu-item index="2-3">
                        <router-link to="/shop_home/shop_hot"><i class="iconfont icon-leimupinleifenleileibie"></i>热门分类</router-link>
                    </el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="iconfont icon-dingdan"></i>
                        <span slot="title">订单</span>
                    </template>
                    <el-menu-item index="3-1">
                      <router-link to="/shop_home/shop_order"><i class="iconfont icon-liebiao"></i>订单列表</router-link>
                    </el-menu-item>
                    <el-menu-item index="3-2"><i class="iconfont icon-shezhi"></i>订单设置</el-menu-item>
                    <el-menu-item index="3-3"><i class="iconfont icon-tuihuofenlei"></i>退货申请处理</el-menu-item>
                    <el-menu-item index="3-4"><i class="iconfont icon-shenqing"></i>退货原因设置</el-menu-item>
                </el-submenu>
                <el-submenu index="4">
                    <template slot="title">
                        <i class="iconfont icon-yingxiao"></i>
                        <span slot="title">营销</span>
                    </template>
                    <el-menu-item index="4-1"><i class="iconfont icon-miaosha"></i>秒杀活动列表</el-menu-item>
                    <el-menu-item index="4-2"><i class="iconfont icon-youhuijuan-xianxing"></i>优惠劵列表</el-menu-item>
                    <el-menu-item index="4-3"><i class="iconfont icon-hot"></i>新品推荐</el-menu-item>
                    <el-menu-item index="4-4"><i class="iconfont icon-hot-o"></i>人气推荐</el-menu-item>
                </el-submenu>
            </el-menu>
        </div>
        <div class="shop_home_content">
          <transition name="el-zoom-in-center">
            <router-view></router-view>
          </transition>
        </div>
    </div>

</template>

<style>
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 1000px;
    }
    .el-menu {
        list-style: none;
        position: relative;
        margin: 0;
        padding-left: 0;

    }
    .el-submenu__title {
        font-size: 14px;
        color: #4d4f51;
        padding: 0 20px;
        cursor: pointer;
        -webkit-transition: border-color .3s,background-color .3s,color .3s;
        transition: border-color .3s,background-color .3s,color .3s;
        box-sizing: border-box;
    }
    .el-radio-button__orig-radio:checked+.el-radio-button__inner {
        color: #404040 !important;
        background-color:transparent !important;
        border-color: transparent !important;
    }
    .el-radio-button__inner {
        background: transparent;
        border: none;
    }
    .el-radio-button {
        position: relative;
        display: inline-block;
        outline: none;
    }
    #shop_home_container{
        width: 100%;
        display: flex;
    }
    .shop_home_content{
        width: 85%;
        float: left;
        margin: 10px;
        min-width: 1036px;
    }
    .nav_list{
        margin-bottom: 10px;
        /*border-right: solid 1px #d7dfdb;*/
        color: #404040;
        cursor: pointer;
        transition: all .28s;
        float: left;
        font-size: 0;
        z-index:500;
    }
    .iconfont{
        display: inline-block;
        margin-right: 10px;
        font-size: 21px;
        color: #666767;
    }
    .el-breadcrumb {
      font-size: 15px;
      line-height: 1;
      font-weight: bold;
      margin-left: -10px;
    }
</style>

<script>
    export default {
        data() {
            return {
                isCollapse: false
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                // console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                // console.log(key, keyPath);
            }
        }
    }
</script>

