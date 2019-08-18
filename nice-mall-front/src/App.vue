<template>
    <div id="app">
        <!--导航-->
        <div id="header-container">
            <nav class="top-nav">
                <ul class="first">
                    <li>
                        <router-link to="/" class="link">Nice 商城</router-link>
                    </li>
                    <li >
                       随心所欲
                    </li>
                </ul>
                <ul class="second">
                    <li><router-link to="/"><span class="iconfont icon-icon4" style="font-size: 20px"></span></router-link></li>
                    <li @mouseenter="show(4)" @mouseleave="hide">
                        <span class="iconfont icon-daohang"></span>更多
                        <span class="iconfont icon-jiantouxia"></span>
                        <div class="item" :class="{itemHover:itemIndex===4}">
                            <ul class="list">
                                <a href="#"><li>合作专区</li></a>
                                <hr>
                                <a href="#"><li>关于我们</li></a>
                            </ul>
                        </div>
                    </li>
                    <li @mouseenter="show(3)" @mouseleave="hide">
                        <span>联系客服</span>
                        <span class="iconfont icon-jiantouxia"></span>
                        <div class="item" :class="{itemHover:itemIndex===3}">
                            <ul class="list">
                                <a href="#"><li>消费者客服</li></a>
                                <a href="#"><li>帮助中心</li></a>
                                <a href="#"><li>卖家客服</li></a>
                            </ul>
                        </div>
                    </li>
                    <li @mouseenter="show(2)" @mouseleave="hide">
                        <span>买家中心</span>
                        <span class="iconfont icon-jiantouxia"></span>
                        <div class="item" :class="{itemHover:itemIndex===2}">
                            <ul class="list" style="width: 100px">
                                <a href="#"><li>免费开店</li></a>
                                <a href="#"><li>已卖出的宝贝</li></a>
                                <a href="#"><li>出售中的宝贝</li></a>
                                <a href="#"><li>问商友</li></a>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#">我的订单</a>
                    </li>
                    <li @mouseenter="show(1)" @mouseleave="hide">
                        <span class="iconfont icon-shoucangxing2"></span>我的Nice
                        <span class="iconfont icon-jiantouxia"></span>
                        <div class="item" :class="{itemHover:itemIndex===1}">
                            <ul class="list">
                                <a href="#"><li>收藏的宝贝</li></a>
                                <a href="#"><li>收藏的店铺</li></a>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <router-link to="/other_container/goods_car"><span class="iconfont icon-gouwuchekong"></span>购物车</router-link>
                        <span class="iconfont icon-jiantou"></span>
                    </li>
                    <li @mouseenter="show(5)" @mouseleave="hide">
                        <router-link to="/login_sign/login" class="link">
                            <span class="iconfont icon-user"></span>
                            <span style="font-size: 17px">请登录</span>
                        </router-link>
                        <div class="item" :class="{itemHover:itemIndex===5}">
                            <ul class="list" style="width: 100px">
                                <a href="#"><li>退出登录</li></a>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>

        </div>
        <!--中间内容-->
        <router-view></router-view>

        <!--底部内容-->
        <footer>
            阿里巴巴集团|阿里巴巴国际站|阿里巴巴中国站|全球速卖通|淘宝网|天猫|聚划算|一淘|阿里妈妈|飞猪|虾米|阿里云计算|云OS|万网|支付宝|来往 <br>
            关于淘宝合作伙伴营销中心廉正举报联系客服开放平台诚征英才联系我们网站地图法律声明隐私权政策© 2019 Taobao.com 版权所有<br>
            网络文化经营许可证：浙网文[2016]0132-032号|增值电信业务经营许可证：浙B2-20080224-1|信息网络传播视听节目许可证：1109364号<br>
        </footer>
    </div>

</template>

<script>
    export default {
        data(){
            return {
                itemIndex:null
            }
        },
        methods:{
            show(index){
                this.itemIndex = index;
            },
            hide(){
                this.itemIndex = null;
            }
        }
    }
</script>

<style scoped>
    #app{
        width: 100%;
        min-width: 1080px;
        height: 100%;
    }
    footer{
        position: relative;
        width: 100%;
        border-top:1px solid #787671;
        text-align: center;
        padding: 20px 0;
        background: white;
        height: 150px;
    }
    *{
        margin: 0;
        padding: 0;
    }
    #header-container{
        width: 100%;
    }
    .first{
        margin-left: 40px;
        font-size: 18px;
        font-weight: bolder
    }
    .second{
        margin-right: 40px;
    }
    .first >li{
        list-style: none;
        float: left;
        margin-left: 20px;
        line-height:55px;
    }
    .second >li{
        list-style: none;
        float: right;
        margin-right: 20px;
        line-height: 55px;
    }
    .top-nav{
        width: 100%;
        height:55px;
        border: none;
        font-size: 14px;
        color: white;
        background: linear-gradient(to right, #fe5745, #f1366d);
        z-index: 1000;
    }
    .iconfont{
        color: white!important;
        margin-right: 5px;
    }
    .icon-jiantouxia{
        font-size: 14px;
        margin-left: 10px;
    }
    .menu >li{
        position:relative;
        line-height:45px;
        color:black;
        float:left;
        text-align:left;
        cursor:pointer;
        display:inline;
        margin-left: 20px;
    }
    a{
        text-decoration : none;
        color: white;
    }
    a:hover{
        color: #030303;
    }

</style>
