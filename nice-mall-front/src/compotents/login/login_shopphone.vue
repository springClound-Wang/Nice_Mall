<template id="login">
    <div>
        <form class="form-inline">
            <label>
                <span class="iconfont icon-yonghuming"></span>
                <input type="text" name="phoneNum"  value="" placeholder="请输入您的手机号" class="form-control" v-model="phoneNum">
            </label>
            <br>
            <label>
                <span class="iconfont icon-querenmima"></span>
                <input type="password" name="password" value="" placeholder="请输入您的密码" class="form-control" v-model="password">
            </label>
            <br>
            <div id="footer">
                <div class="other-login">
                    <router-link to="/login_sign/login_shop_name"><span>用户名登录</span></router-link>
                </div>
                <div class="sign">
                    <router-link to="/login_sign/forget" id="forget-password">忘记密码?</router-link>
                    <div class="link-to">
                        <button class="btn" @click="handleLogin">登录</button>
                        <router-link to="/login_sign/signup" class="btn" style="margin-left:20px;">注册</router-link>
                    </div>
                </div>

            </div>
        </form>
    </div>
</template>
<script>
    export  default {
        data(){
            return{
                phoneNum:'',
                password:''
            }
        },
        methods:{
            handleLogin(){
                let datas ={
                    telephone: this.phoneNum,
                    password: this.password
                };
                if(this.phoneNum === '' || this.password === ''){
                    alert('请填入完整信息')
                }
                else{
                    let that =this;
                    this.$http.post('http://120.78.64.17:8086/nice-mall-backend/login/phone',datas).then(res => {                   //请求成功后的处理函数
                        that.$message({
                            message:res.data.message,
                            type:'success',
                            duration:1500
                        });
                        if(res.data.status === true){
                            //检测token
                            window.localStorage["token"] = res.data.data.token;
                            window.localStorage["userId"] = res.data.data.userid;
                            window.localStorage["username"] = res.data.data.username;
                            window.localStorage['isshop'] = res.data.data.isshop;
                            window.localStorage['logintime'] = new Date().getTime();
                            this.$router.push({path: '/home'});

                        }
                        else {
                            return;
                        }
                    }).catch(err => {                 //请求失败后的处理函数
                        console.log(err)
                    })
                }

            }
        }
    }
</script>

<style>

</style>
