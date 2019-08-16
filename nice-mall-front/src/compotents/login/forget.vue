<template>
    <form class="form-inline">
        <label>
            <span class="iconfont icon-yonghuming"></span>
            <input type="text" name="phoneNum"  value="" placeholder="请输入您的手机号" class="form-control" v-model="phoneNum">
        </label>
        <br>
        <label>
            <span class="iconfont icon-zaicishurumima" style="left: 2px"></span>
            <input type="text" name="code"  value="" placeholder="请输入验证码" class="form-control code-input"  v-model="code">
            <button class="code-btn" @click.prevent="handleCode">获取验证码</button>
        </label>
        <label>
            <span class="iconfont icon-querenmima"></span>
            <input type="text" name="newPassword"  value="" placeholder="请输入新密码" class="form-control" v-model="newPassword">
        </label>
        <br>
        <div id="footer">
            <div class="sign">
                <span id="forget-password">欢迎进入</span>
                <div class="link-to">
                    <button class="btn" @click="handleForget">确定</button>
                    <router-link to="/login_sign/login" class="btn">返回登录</router-link>
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
            newPassword:''
        }
    },
    methods:{
        //提交修改
        handleForget(){
            if(this.phoneNum === '' || this.code ===''){
                alert("请填入完整信息")
            }
            else {
                this.$http.post('https://localhsot:8080/forget',{
                    //参数
                    phoneNum:this.phoneNum,
                    code:this.code,
                    newPassword:this.newPassword
                }).then(res => {                   //请求成功后的处理函数
                    console.log(res);
                }).catch(err => {                 //请求失败后的处理函数
                    console.log(err)
                })
            }

        },
        //验证码
        handleCode(){
            this.$http.post('https://localhsot:8080/code').then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err)
            })
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
        border-top:1px solid #cccccc;
        position: absolute;
        bottom: 20%;
    }
    #forget-password{
        position: absolute;
        left: 10px;
        top:18px;
        color: #787671;
    }
</style>