<template>
    <div id="other_container">
        <div class="middle-nav">
            <img src="../../image/logo.png">
            <ul class="icons">
                <li><span class="iconfont icon-anquan"></span>100% 正品</li>
                <li><span class="iconfont icon-yunshuzhong"></span>7 天退换</li>
                <li><span class="iconfont icon-baozhuanhuan"></span>年轻人的潮流</li>
            </ul>
            <label>
                <input type="text" value="" name="search" placeholder="搜索历史订单..."/>
                <span class="iconfont icon-sousuo"></span>
            </label>
        </div>
       <router-view></router-view>
    </div>
</template>
<script>

</script>
<style scoped>
    /*中间导航*/
    #other_container{
        width: 100%;
    }
    .middle-nav{
        width: 90%;
        position: relative;
        height: 120px;
        margin: 10px auto;
        border-bottom: 2px solid #fe6e5a;
    }
    .middle-nav img{
        width: 170px;
        height: 100px;
        margin-top: 1%;
        margin-left: 1%;
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
        right: 5%;
        top: 47%;
    }
    label input{
        width: 400px;
        position: relative;
        font-weight: normal;
        height:40px !important;
        margin-right: 10px;
        border: 1.5px solid  #fa5f62;
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
</style>