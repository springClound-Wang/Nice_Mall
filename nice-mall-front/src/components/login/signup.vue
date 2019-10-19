<template id="signup">
    <form class="form-inline" @submit.prevent="onSubmit">
        <label>
            <span class="iconfont icon-yonghuming"></span>
            <input type="text" name="phoneNum" value="" placeholder="请输入手机号" class="form-control" v-model="phoneNum">
        </label>
        <label>
            <span class="iconfont icon-querenmima"></span>
            <input type="password" name="password"  value="" placeholder="请输入密码" class="form-control" v-model="password">
        </label>
        <label>
            <span class="iconfont icon-zaicishurumima"></span>
            <input type="text" name="code"  value="" placeholder="请输入验证码" class="form-control code-input"  v-model="code">
            <button v-show="show" class="code-btn" @click.prevent="handleCode">获取验证码</button>
            <button v-show="!show" class="code-btn code-time">{{count}} s</button>
        </label>
        <div style="border-bottom: 1px solid #cccccc;margin-top: 35px"></div>
        <router-link to="/login_sign/login_phone" class="to-login">返回登录</router-link>
        <button class="btn to-sign" @click="handleSignUp">注册</button>
    </form>
</template>
<script>

export  default {
   inject: ['reload'],
    data(){
        return {
            phoneNum:'',
            password:'',
            code:'',
            show: true,
            count: '',
            timer: null
        }
    },
    methods:{
        onSubmit(){return false;},
        //输入检测
        enterAsscess(){
            let reg=11&& /^((13|14|15|17|18)[0-9]{1}\d{8})$/;//手机号正则验证
            let phoneNum = this.phoneNum;
            if(!phoneNum){//未输入手机号
                this.$message.warning("请输入手机号码");
                return false;
            }
            if(!reg.test(phoneNum)){//手机号不合法
                this.$message.error("您输入的手机号码不合法，请重新输入");
                return false;
            }
            return true;
        },
        //注册
        handleSignUp(){
            if(!this.enterAsscess()){
                return;
            }
            if(this.phoneNum === '' || this.password === ''|| this.code === ''){
                this.$message.warning('请填入完整信息');
                return;
            }
            if(this.password.length < 6 || this.password.length >15){
                this.$message.warning('密码位数应在6~15位之间');
                return;
            }
            else{
                this.$http.post('/register',{
                  telephone:this.phoneNum,
                  password:this.password,
                  code:this.code
                }).then(res => {
                    this.$message.success(res.data.message); //请求成功后的处理函数
                    this.$router.push({path:'/login_sign/login_phone'});
                }).catch(err => {     //请求失败后的处理函数
                  this.$message.error(err.data.message)
                });
            }
        },
        //验证码计时
        getCode(){
            if(!this.enterAsscess()){
                return;
            }
            else {
                if (!this.timer) {
                    this.count = 60;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= 60) {
                            this.count--;
                            // console.log(this.count)
                        } else {
                            this.show = true;
                            clearInterval(this.timer);
                            this.timer = null;
                        }
                    }, 1000)
                }
            }

        },
        // TODO 获取验证码
        handleCode(){
            this.getCode();
            this.$http.post('/code',{
                telephone:this.phoneNum
            }).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err)
            })
        }
    }
}
</script>

<style scoped>
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
        margin-left: 56%;
        border: none;
        background: #889fcc;
        padding: 11px;
        margin-top: 2px;
        color: #ffffff;
        border-radius: 5px;
    }
    .code-time{
        width: 90px;
        margin-left: 56%;
        height: 42px;
        font-size: 16px;
    }
    .code-input{
        width: 100px !important;
        float: left;
    }
</style>
