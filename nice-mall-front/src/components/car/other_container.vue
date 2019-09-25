<template>
    <div id="other_container">
        <div class="middle-nav">
            <img src="../../assets/image/logo.png">
            <ul class="icons">
                <li><span class="iconfont icon-anquan"></span>100% 正品</li>
                <li><span class="iconfont icon-yunshuzhong"></span>7 天退换</li>
                <li><span class="iconfont icon-baozhuanhuan"></span>年轻人的潮流</li>
            </ul>
            <label>
              <input type="text" value="" name="search" placeholder="搜索 ..." style="border-radius: 3px;"
                     v-model="search" @keydown.enter="handletoSearch"/>
              <!--搜索框-->
              <router-link :to="{path:'/goods_item',query: {goodsname:search,urls:'home/'}}" @click.native="flushCom">
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
       <router-view></router-view>
    </div>
</template>
<script>
export default {
    data(){
        return{
            search:''
        }
    },
    methods:{
        //回车请求 搜索框
        handletoSearch(){
            this.$router.push({ path: '/goods_item',query:{goodsname:this.search,urls:'home/' }});
            this.flushCom();
        },
    }
}
</script>
<style scoped>
    /*中间导航*/
    #other_container{
        width: 100%;
    }
    .middle-nav{
      width: 92%;
      position: relative;
      height: 100px;
      margin: 10px auto;
      padding-bottom: 20px;
      border-bottom: 1px solid #cccccc;
    }
    .middle-nav img{
      width: 170px;
      height: 100px;
      margin-top: 1%;
    }
    .icons{
      display: inline-block;
      position: absolute;
      top: 52%;
      left: 14%;
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
      top: 36%;
    }
    label input{
      width: 400px;
      position: relative;
      font-weight: normal;
      height:40px !important;
      margin-right: 10px;
      border: 2px solid  #fa5f62;
      font-size: 14px;
    }
    label .icon-sousuo{
      position: relative;
      display: inline-block;
      background-color: #fa5f62;
      padding: 6px 10px 10px;
      font-size: 26px;
      top: 3px;
      right: 7px;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      color: white !important;
    }
    .car{
      position: absolute;
      right: 5%;
      top: 45%;
      display: inline-block;
    }
    .car .iconfont{
      font-size: 30px;
      color: #fa5f62 !important;
      margin-right: 20px;
    }
</style>
