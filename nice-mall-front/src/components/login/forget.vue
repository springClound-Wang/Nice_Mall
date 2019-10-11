<template>
    <form class="form-inline" action="" method="post" @submit.prevent="onSubmit">
        <label>
            <span class="iconfont icon-yonghuming"></span>
            <input type="text" name="phoneNum"  value="" placeholder="请输入您的手机号" class="form-control" v-model="phoneNum">
        </label>
        <label>
            <span class="iconfont icon-zaicishurumima"></span>
            <input type="text" name="code"  value="" placeholder="请输入验证码" class="form-control code-input"  v-model="code">
            <button v-show="show" class="code-btn" @click.prevent="handleCode">获取验证码</button>
            <button v-show="!show" class="code-btn code-time">{{count}} s</button>
        </label>
        <label>
            <span class="iconfont icon-querenmima"></span>
            <input type="text" name="newPassword"  value="" placeholder="请输入新密码" class="form-control" v-model="newPassword">
        </label>
        <div id="footer">
            <div class="sign" style="margin-top: 0">
                <span id="forget-password">欢迎进入</span>
                <div class="link-to">
                    <button class="btn" @click="handleForget">确定</button>
                    <router-link to="/login_sign/login_phone" class="btn">返回登录</router-link>
                </div>
            </div>

        </div>
    </form>
</template>

<script>
export default {
    data(){
        return{
            phoneNum:'',
            code:'',
            newPassword:'',
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
                this.$message.err("您输入的手机号码不合法，请重新输入");
                return false;
            }
            return true;
        },
        //提交修改
        handleForget(){
            if(!this.enterAsscess()){
                return;
            }
            if(this.phoneNum === '' || this.code ===''){
                this.$message.warning("请填入完整信息");
                return;
            }
            if(this.newPassword < 6 || this.newPassword >15){
                this.$message.warning('密码位数应在6~15位之间');
                return;
            }
            else {
                this.$http.post('/forgetpass',{
                    //参数
                    telephone:this.phoneNum,
                    code:this.code,
                    newPassword:this.newPassword
                }).then(res => {                   //请求成功后的处理函数
                    console.log(res);
                    if(res.data.status === 0){
                        alert('登录成功');
                    }
                }).catch(err => {                 //请求失败后的处理函数
                  this.$message.error("登录失败")
                })
            }

        },
        //验证码
        handleCode(){
            this.getCode();
            this.$http.post('/code',{
                phoneNum:this.phoneNum
            }).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err)
            })
        },
        //验证码计时
        getCode(){
            if(!this.enterAsscess()){
                return;
            }
            else{
                if (!this.timer) {
                    this.count = 60;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= 60) {
                            this.count--;
                            console.log(this.count)
                        } else {
                            this.show = true;
                            clearInterval(this.timer);
                            this.timer = null;
                        }
                    }, 1000)
                }
            }
        }
    }
}
</script>
<style scoped>
    form label:nth-child(1){
        margin-top: 28px !important;
    }
    .link-to{
        position: absolute;
        top:5px;
        right: 10px;
    }
    #footer {
        width: 100%;
        /*height: 60px;*/
        margin: 0 auto;
        position: absolute;
        bottom: 10%;
    }
    #forget-password{
        position: absolute;
        left: 10px;
        top:3px;
        color: #787671;
    }
    .code-input{
      width: 100px !important;
      float: left;
    }
    .code-btn{
        margin-left: 56%;
        border: none;
        background: #889fcc;
        padding: 12px;
        margin-top: 2px;
        color: #ffffff;
        border-radius: 5px;
        width: 91px;
        font-size: 13px;
    }
</style>
