<template>
    <div id="container" @mousewheel="rightShow">
        <!--中间导航-->
        <div class="middle-nav">
            <img src="../../image/logo.png">
            <ul class="icons">
                <li><span class="iconfont icon-anquan"></span>100% 正品</li>
                <li><span class="iconfont icon-yunshuzhong"></span>7 天退换</li>
                <li><span class="iconfont icon-baozhuanhuan"></span>年轻人的潮流</li>
            </ul>
            <label>
                <input type="text" value="" name="search" placeholder="连衣裙" v-model="search" @keydown.enter="handletoSearch"/>
                <!--搜索框-->
                <router-link :to="'/goods_item?goodsname='+search" @click.native="flushCom">
                    <span class="iconfont icon-sousuo"></span>
                </router-link>
            </label>
            <div class="car">
                <span class="iconfont icon-erweima" title="二维码" style="font-size:32px"></span>
                <router-link to="/other_container/goods_car">
                    <span class="iconfont icon-gouwuchekong" title="去购物车"></span>
                </router-link>
            </div>
        </div>
        <div class="bottom-nav">
            <ul>
                <li class="list-all" @mouseenter="show" @mouseleave="hide"><span class="iconfont icon-daohang"></span>&nbsp;&nbsp;商品分类
                    <div class="itemHover">
                            <ul id="list">
                                <li @mouseenter="show_goods" @mouseleave="hide_goods" v-for="goods_item in goods_type_list">
                                    <span>{{goods_item.name_list}}</span>
                                    <div class="all_goods_list">
                                       <div v-for="(item,index) in goods_item.goods_list"
                                            :key="index" class="all_goods_list_item">
                                           <div class="goods_list_name">{{item.goods_list_name}} ></div>
                                           <div class="goods_list_item">
                                               <span v-for="val in item.goods_all" class="val_item">
                                                   <router-link :to="'/goods_item?goodsname='+val" @click.native="flushCom">{{val}}</router-link>
                                               </span>
                                           </div>
                                       </div>

                                    </div>
                                </li>
                            </ul>
                        </div>
                </li>
                <li><router-link to="/home" @click.native="flushCom" >首页</router-link></li>
                <li>最后疯抢</li>
                <li><router-link to="/goods_list?type=女装" @click.native="flushCom">女装</router-link></li>
                <li><router-link to="/goods_list?type=男装" @click.native="flushCom">男装</router-link></li>
                <li><router-link to="/goods_list?type=母婴" @click.native="flushCom">母婴</router-link></li>
                <li><router-link to="/goods_list?type=居家" @click.native="flushCom">电子产品</router-link></li>
                <li><router-link to="/goods_list?type=美妆" @click.native="flushCom">男鞋</router-link></li>
                <li><router-link to="/goods_list?type=鞋包" @click.native="flushCom">包包</router-link></li>
                <li><router-link to="/goods_list?type=食品" @click.native="flushCom">女鞋</router-link></li>
                <li><router-link to="/goods_list?type=运动" @click.native="flushCom">更多</router-link></li>
            </ul>
        </div>
        <!--右侧导航条-->
        <div class="right-nav">
            <ul>
                <li  @mouseenter="show" @mouseleave="hide">
                    <div>
                        <span class="iconfont icon-user"></span>
                        <span>账号</span>
                    </div>
                    <div class="hide-right-nav">
                        <div style="margin-top: 20px;">
                            <img src="../../image/head.jpg" style="border: 1px solid #cccccc;border-radius:5px;width: 50px;height: 50px "/>
                        </div>
                        <router-link :to="url"><span v-text="isLogin" @click="isLoginTo"></span></router-link>
                        <div class="hide-con">
                            <div>我的订单</div>
                            <div>我的消息</div>
                        </div>
                    </div>
                </li>
                <li style="background-color: #fa5157">
                    <span class="iconfont icon-baozhuanhuan"></span>
                    <span style="display: inline-block;width: 1em;">购物车</span>
                </li>
                <li @mouseenter="show" @mouseleave="hide">
                    <span class="iconfont icon-youhuijuan-xianxing" ></span>
                    <div class="hide-icon">我的优惠券</div>
                </li>
                <li @mouseenter="show" @mouseleave="hide">
                    <span class="iconfont icon-shoucang" ></span>
                    <div class="hide-icon">商品收藏</div>
                </li>
                <li  @mouseenter="show" @mouseleave="hide">
                    <span class="iconfont icon-xinbaniconshangchuan-"></span>
                    <div class="hide-icon">店铺收藏</div>
                </li>
            </ul>
            <div class="last-icon" @mouseenter="show" @mouseleave="hide">
                <span class="iconfont icon-tubiaozhizuo- "></span>
                <div class="hide-icon">回到顶部</div>
            </div>

        </div>

        <!--正文内容-->
        <router-view v-if="routerAlive"></router-view>
    </div>
</template>
<script>
    export default {
        inject:['reload'],
        data() {
            return {
                isLogin:window.localStorage.getItem('username') ?window.localStorage.getItem('username'):'登录/注册',
                url:'/login_sign/login', //登录
                search:'', //搜索
                routerAlive:true,
                goods_type_list:'' //类型列表
             }
        },
        created(){
            this.getTypeGoodsList(); //得到这个页面请求的所有数据
        },
        methods:{
            //切换路由参数  刷新当前页面
            flushCom:function(){
                this.reload();
            },
            //个人中心
            isLoginTo(){
                if(this.isLogin === window.localStorage.getItem('username')){
                    //个人中心
                    this.url='#'
                }
            },
            //回车请求
            handletoSearch(){
                this.$router.push({ path: '/goods_item?goodsname='+this.search });
                this.flushCom();
            },
            //发出请求
            getTypeGoodsList(){
                this.$http.get('/').then(res=>{
                    this.goods_type_list = res.data.goods_type_list;
                }).catch(err=>{
                    console.log(err);
                })
            },
            show(e){
                e.target.firstChild.nextElementSibling.style.display = 'block'
            },
            hide(e){
                e.target.firstChild.nextElementSibling.style.display = 'none'
            },
            show_goods(e){
                e.target.lastChild.style.display = 'block'
            },
            hide_goods(e){
                e.target.lastChild.style.display = 'none'
            },
            //右侧导航栏
            rightShow(){
                let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
                if(scrollTop>=100){
                    document.getElementsByClassName('right-nav')[0].style.display ='block';
                }else{
                    document.getElementsByClassName('right-nav')[0].style.display ='none';
                }
            }
        }
    }
</script>
<style scoped>
    #container{
        width: 100%;
        min-width: 1260px;
        margin: 0 auto;
    }
    /*右侧导航条*/
    .right-nav{
        width: 35px;
        height: 100%;
        position: fixed;
        color: white;
        background-color: #262626;
        right: 0;
        top:0;
        z-index: 1000;
        display: none;
    }
    .right-nav ul{
        position: relative;
        width: 100%;
        top:25%;
        list-style: none;
    }
    .right-nav ul li{
        position: relative;
        text-align: center;
        margin-top: 10px;
        padding-bottom: 5px;
        font-size: 16px;
    }
    .right-nav ul li:hover{
        background-color: #fa5157;
    }
    .right-nav .iconfont{
        font-size: 25px;
    }
    .last-icon{
        position: absolute;
        right: 0;
        bottom: 10px;
        padding: 1px 5px;
        text-align: center;
    }
    .last-icon:hover{
        background-color: #fa5157;
    }
    /*右侧隐藏*/
    .hide-icon{
        position: absolute;
        top:0;
        right: 35px;
        width: 105px;
        height: 40px;
        line-height: 40px;
        background-color:#262626;
        color: white;
        z-index: -100;
        display: none;
    }
    .hide-right-nav{
        /*display: none;*/
        position: absolute;
        top:0;
        right: 35px;
        width: 200px;
        background-color:#262626;
        color: white;
        z-index: -100;
        display: none;
    }
    .hide-right-nav span{
        font-size: 16px;
        width: 60%;
        margin: 0 auto;
        border-bottom: 1px solid #cccccc;
        display: block;
        padding: 10px 0 5px 0;
    }
    .hide-right-nav span:hover{
        color: #ff5125;
    }
    .hide-con div{
        width: 70px;
        height: 40px;
        line-height: 40px;
        font-size: 14px;
        border-right: 1px solid #cccccc;
        display: inline-block;
        margin: 5px;
    }
    .hide-con div:nth-child(2){
        border: none;
    }
    .hide-con div:hover{
        background-color: #ff727c;
        color: white;
    }
    /*商品分类下拉菜单*/
    .all_goods_list{
        width: 1220px;
        height: 450px;
        position: absolute;
        border-top: 1px solid #cccccc;
        background: #fff5ee url("../../image/list_bg.png") no-repeat 100% 100%;
        top: 5px;
        left: 160px;
        text-align: left;
        display: none;
    }
    .all_goods_list_item{
       width: 80%;
    }
    .goods_list_name{
        padding-left: 20px;
        display: inline-block;
        width: 110px;
        float: left;
    }
    .goods_list_item{
        position: relative;
        margin-left: 110px;
    }
    .all_goods_list_item{
        display: inline-block;
        position: relative;
        width: 80%;
        padding: 5px;
        margin-left: 10px;
        border-bottom: 1px solid #f4edf2;
    }
    .val_item{
        margin:0 5px;
    }
    /*中间导航*/
    .middle-nav{
        width: 92%;
        position: relative;
        height: 100px;
        margin: 10px auto;

    }
    .middle-nav img{
        width: 170px;
        height: 100px;
        margin-top: 1%;
    }
    .icons{
        display: inline-block;
        position: relative;
        top: 42%;
        left: 1%;
    }
    .icons li{
        list-style: none;
        float: left;
        margin-left: 25px;
        font-size: 16px;
    }
    .icons li span{
        font-size: 20px;
        font-weight: bolder;
        margin-right: 10px;
        color:#fa5f62;
    }
    label{
        position: absolute;
        right: 16%;
        top: 47%;
    }
    label input{
        width: 400px;
        position: relative;
        font-weight: normal;
        height:40px !important;
        margin-right: 10px;
        border: 2px solid  #fa5f62;
    }
    label .icon-sousuo{
        position: relative;
        display: inline-block;
        background-color:  #fa5f62;
        padding: 2px 10px;
        font-size: 26px;
        top: 5px;
        right: 17px;
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
        color: white !important;
    }
    .car{
        position: absolute;
        right: 5%;
        bottom: 0;
        display: inline-block;
    }
    .car .iconfont{
        font-size: 30px;
        color: #fa5f62 !important;
        margin-right: 20px;
    }
    /*底部导航*/
    .bottom-nav{
        position: sticky;
        position: -webkit-sticky;
        top: 0;
        background-color: white;
        z-index: 1000;
        width: 90%;
        height: 48px;
        margin: 15px auto;
        border-bottom: 1px solid #cccccc;
    }
    .icon-daohang{
        font-size: 20px;
    }
    .list-all{
        position: relative;
        display: inline-block;
        width: 160px;
        text-align: center;
        color: white;
        background-color: #fa5f62;
        z-index: 200;
    }
    .bottom-nav li{
        line-height: 48px;
        list-style: none;
        margin-right: 4%;
        font-size: 17px;
        float: left;
    }
    .item{
        border: none;
    }
    .itemHover{
        position: absolute;
        top: 30px;
        width: 160px;
        height: auto;
        border-top: none;
        background-color:white;
        margin: 10px 0;
        display: none;
        z-index: 100;
    }
    #list{
        border: none;
        width:100%;
        height: 454px;
        color: white;
        background: linear-gradient(to right, #f1487f, #fe6e5a);
    }
    #list li{
        width: 100%;
        padding-left: 10px;
    }
    #list li:hover{
        background-color: white;
        color: #6d6d72;
    }
    #list li:nth-child(1){
        margin-top: 20px;
    }
</style>