<template>
    <div id="content">
        <img src="../../image/big.png">
        <div class="container">
            <div class="title">欢迎进入</div>
            <transition mode="out-in">
                <!--放置登录注册 忘记密码组件-->
                <router-view ></router-view>
            </transition>
        </div>
    </div>
</template>
<script>

export default {
    data(){
      return{

      }
    },
    methods: {
    }
}
</script>
<style scoped>
    #content{
        width: 100%;
        height: 75%;
        background-color: #f4d1d2;
    }
    img{
        position: relative;
        left: 17%;
        margin-top:5%
    }
    .container{
        width: 355px;
        height: 410px;
        position: absolute;
        right: 200px;
        top: 20%;
        background: rgba(255,255,255,.8);
        border: 1px solid #cccccc;
    }
    .title{
        width: 80%;
        text-align: center;
        font-size: 18px;
        margin: 15px auto;
        border-bottom: 1px solid #787671;
        padding-bottom: 15px;
    }
</style>