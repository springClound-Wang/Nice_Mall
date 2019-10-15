<template>
  <div id="app">
    <!--导航-->
    <div id="header-container">
      <nav class="top-nav">
        <ul class="first">
          <li>
            <router-link to="/" class="link">Nice 商城</router-link>
          </li>
          <li>随心所欲</li>
        </ul>
        <ul class="second">
          <li><router-link to="/"><span class="iconfont icon-qiyemenhu" style="font-size: 18px"></span>首页</router-link></li>
          <li @mouseenter="show(4)" @mouseleave="hide">
            <span class="iconfont icon-daohang"></span>更多
            <span class="iconfont icon-jiantouxia"></span>
            <div class="item" :class="{itemHover:itemIndex===4}">
              <ul class="list">
                <li>合作专区</li>
                <li>关于我们</li>
              </ul>
            </div>
          </li>
          <li @mouseenter="show(3)" @mouseleave="hide">
            <span>联系客服</span>
            <span class="iconfont icon-jiantouxia"></span>
            <div class="item" :class="{itemHover:itemIndex===3}">
              <ul class="list">
                 <li>消费者客服</li>
                 <li>帮助中心</li>
                 <li>卖家客服</li>
              </ul>
            </div>
          </li>
          <li @mouseenter="show(2)" @mouseleave="hide">
            <span>卖家中心</span>
            <span class="iconfont icon-jiantouxia"></span>
            <div class="item" :class="{itemHover:itemIndex===2}">
              <ul class="list" style="width: 100px">
                <router-link :to="other_url" >
                  <li @click="myShop">我的店铺</li>
                </router-link>
                <li>已卖出的宝贝</li>
                <li>出售中的宝贝</li>
                <li>问商友</li>
              </ul>
            </div>
          </li>
          <li>
            <router-link to="/other_container/goods_sel" >我的订单</router-link>
          </li>
          <li @mouseenter="show(1)" @mouseleave="hide">
            <span class="iconfont icon-shoucangxing2"></span>我的Nice
            <span class="iconfont icon-jiantouxia"></span>
            <div class="item" :class="{itemHover:itemIndex===1}">
              <ul class="list">
                <li>收藏的宝贝</li>
                <li>收藏的店铺</li>
              </ul>
            </div>
          </li>
          <li>
            <router-link to="/other_container/goods_car"><span class="iconfont icon-gouwuchekong"></span>购物车</router-link>
            <span class="iconfont icon-jiantou"></span>
          </li>
          <li @mouseenter="show(5)" @mouseleave="hide">
            <router-link :to="url" class="link" >
              <span class="iconfont icon-user"></span>
              <span style="font-size: 17px" v-text="isLogin" @click="handleIsLogin"></span>
            </router-link>
            <div class="item" :class="{itemHover:itemIndex===5}" v-if="isExit">
              <ul class="list" style="width: 100px">
                <li @click="exitLogin">退出登录</li>
              </ul>
            </div>
          </li>
        </ul>
      </nav>

    </div>
    <!--中间内容-->
    <router-view v-if="isRouterAlive"></router-view>
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
  name: 'App',
  provide () {
    return {
      reload: this.reload
    }
  },
  data(){
    return{
      count: 0,
      isRouterAlive:true,
      isExit: false,
      itemIndex: null,
      url: '/login_sign/login_phone',
      other_url: '',
      isLogin: window.localStorage.getItem('username') ?window.localStorage.getItem('username'):'请登录'
    }
  },
  created(){
    this.isLoginTime(); //检测登录过期
    this.isLoginTo();
  },
  methods:{
    reload () {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      })
    },
    show(index) {
      this.itemIndex = index;
    },
    hide() {
      this.itemIndex = null;
    },
    //登陆后切换导航
    isLoginTo() {
      //若已经登录 则导航切换到去个人中心  且 显示退出登录
      if (this.isLogin === window.localStorage.getItem('username')) {
        this.isLogin =window.localStorage.getItem('username');
        this.isExit = true;
        this.url = '/personal_home/person_info'
      }
      else {
        this.isExit = false;
        this.url = '/login_sign/login_phone'
      }
    },
    handleIsLogin(){
      if(window.localStorage.getItem('userId')){
        this.url = '/personal_home/person_info';
      }
      else{
        this.url =  '/login_sign/login_phone'
      }
    },
    //判断登录过期 时间
    isLoginTime(){
      if(window.localStorage.getItem('logintime')){
        if(new Date().getTime() - window.localStorage.getItem('logintime') > 86400000){
          this.$message.warning('登录过期');
          this.removeStorage();
          this.isLogin = '请登录';
          window.location.reload()
        }
      }
    },
    //退出登录
    exitLogin() {
      let token = window.localStorage.getItem('token');
      this.$http.delete('http://120.78.64.17:8086/nice-mall-backend/logout', {
        params: {},
        headers: {Authorization: token}
      }).then(res => {
        if (res.data.status === true) {
          this.removeStorage();
          this.isLogin = '请登录';
          this.isExit = false;
        }
      }).catch(err => {
        alert("退出登录"+err); //退出登录错误
      });
    },
    //清除信息
    removeStorage(){
      window.localStorage.removeItem('userId');
      window.localStorage.removeItem('token');
      window.localStorage.removeItem('username');
      window.localStorage.removeItem('isshop');
      window.localStorage.removeItem('logintime');
      window.localStorage.removeItem('userAvatar');
      this.$router.push({path: '/login_sign/login_phone'});
    },
    //我的店铺
    myShop() {
      if (!window.localStorage.getItem('token')) {
        this.other_url = '/login_sign/login_shop_phone';
      }
      else {
        if (window.localStorage.getItem('isshop') === 'false') {
          //创建店铺路径
          this.other_url = '/shop_create';
        } else {
          this.other_url = '/shop_home';
        }
      }
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
    background: linear-gradient(to right, #fe3a42, #f1366d);
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
  /*下拉菜单*/
  .item{
    position: absolute;
    width: 90px;
    height: auto;
    background-color:white;
    margin: 2px 0;
    padding: 5px;
    display: none;
  }
  .itemHover{
    position: absolute;
    top: 40px;
    width:100px;
    height: auto;
    border-top: none;
    background: linear-gradient(to bottom, #f1366d, #fe5745);
    margin: 8px 0;
    display: block;
    z-index: 1100;
  }
  .list{
    list-style-type: none;
    width: 100px;
    text-align: left;
    float: left;
    display:block;
    position: relative;
  }
  .list li{
    padding: 0 8px;
    height: 3em;
    color: white;
    cursor: pointer;
  }
</style>
