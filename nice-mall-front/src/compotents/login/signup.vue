<template id="signup">
    <form class="form-inline">
        <label>
            <span class="iconfont icon-yonghuming"></span>
            <input type="text" name="phoneNum" value="" placeholder="请输入手机号" class="form-control" v-model="phoneNum">
        </label>
        <br>
        <label>
            <span class="iconfont icon-querenmima"></span>
            <input type="password" name="password"  value="" placeholder="请输入密码" class="form-control" v-model="password">
        </label>
        <br>
        <label>
            <span class="iconfont icon-zaicishurumima" style="left: 2px"></span>
            <input type="text" name="code"  value="" placeholder="请输入验证码" class="form-control code-input"  v-model="code">
            <button class="code-btn" @click.prevent="handleCode">获取验证码</button>
        </label>
        <br>
        <div style="border-bottom: 1px solid #cccccc;margin-top: 35px"></div>
        <router-link to="/login_sign/login" class="to-login">返回登录</router-link>
        <button class="btn to-sign" @click="handleSignUp">注册</button>
    </form>
</template>
<script>
export  default {
    data(){
        return {
            phoneNum:'',
            password:'',
            code:''
        }
    },
    methods:{
        //注册
        handleSignUp(){
            if(this.phoneNum === '' || this.password === ''|| this.code === ''){
                alert('请填入完整信息');
            }
            else{
                this.$http.post('/signup',{
                    phoneNum:this.phoneNum,
                    password:this.password,
                    code:this.code
                }).then(res => {                   //请求成功后的处理函数
                    console.log(res);
                    if(res.data.status === 0){
                        alert('登录成功');
                    }
                }).catch(err => {                 //请求失败后的处理函数
                    console.log(err)
                })
            }
        },
        //验证码
        handleCode(){
            this.$http.post('/code',{
                phoneNum:this.phoneNum
            }).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err)
            })
        }
    }
}
</script>

<style>
    .to-login{
        margin-top: 20px;
        float: left;
    }
    .to-sign{
        margin-top: 15px !important;
        float: right;
    }
    .to-login:hover{
       text-decoration: none;
    }
    .code-btn{
        padding: 11px 5px;
        color: #f5f5f5;
        background: #889fcc;
        font-size: 14px;
        font-weight: normal;
    }
    .code-input{
        width: 50% !important;
        float: left;
    }
</style>