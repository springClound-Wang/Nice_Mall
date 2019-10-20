<!--弹框-->
<template>
    <div class="container">
        <div class="popup">
            <header class="popup-header">
                <div name="header">
                    <span>收货信息</span>
                    <span class="close" @click="close">×</span>
                </div>
            </header>
            <div class="popup-body">
                <form name="body" @submit.prevent="onSubmit">
                    <span><i>*</i>收货人: </span>
                    <input type="text" size="15" value="" v-model="receiptName" name="name" placeholder="收货人姓名"/><br>
                    <span><i>*</i>手机号码: </span>
                    <input type="text" class="phone" size="15" value="" v-model="receiptPhone" name="telephone" placeholder="请填写手机号码"/><br>
                    <span><i>&nbsp;&nbsp;</i>备用号码: </span>
                    <input type="text" class="phone" size="15" value="" v-model="sparePhone" name="othertelephone" placeholder="手机号/固定号码"/><br>
                    <span><i>*</i>收货时间: </span>
                    <select name="time" v-model="receiptTime">
                        <option value="one" selected>周一至周日均可收货</option>
                        <option value="two">周六日、节假日均可收货</option>
                        <option value="three">周一至周五收货</option>
                    </select><br>
                    <span><i>*</i>邮政编码: </span>
                    <input type="text" name="address" value="" v-model="postalCode" placeholder="请填写邮政编码"><br>
                    <span><i>*</i>所在地区: </span>
                    <input type="text" name="address" value="" v-model=" receiptArea" placeholder="省/直辖市 市 县/区 乡镇/街道"><br>
                    <span><i>*</i>详细地址: </span>
                    <input type="text" name="addressinfo" v-model="detailAddress" value="" placeholder="请填写详细地址"><br>

                    <div class="btn-submit">
                        <button class="btn-green" @click="addAddress">保存</button>
                        <button class="btn-green" @click="close">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        inject:['reload'],
        name: 'popup',
        data(){
          return{
            receiptName:"",
            receiptArea:'',
            detailAddress:'',
            postalCode:'',
            receiptPhone:'',
            sparePhone:'',
            receiptTime:''
          }
        },
        methods:{
            close (){
                this.$emit('close');
            },
            onSubmit(){
              return false;
            },
            // TODO  发送请求  添加地址 然后 关闭弹框  刷新页面
            addAddress(){
                this.$http.post('/personal/address',{
                  userId:window.localStorage.getItem('userId'),
                  receiptName:this.receiptName,
                  receiptArea:this.receiptArea,
                  detailAddress:this.detailAddress,
                  postalCode:this.postalCode,
                  receiptPhone:this.receiptPhone,
                  sparePhone:this.sparePhone,
                  receiptTime:this.receiptTime
                }, {
                    headers: {Authorization: window.localStorage.getItem('token')}
                }).then(res=>{
                    this.close();
                    this.reload();
                }).catch(err=>{
                    this.$message.success(err.data.message);
                })
            }
        }

    }
</script>

<style scoped>
    .container {
        position: fixed;
        height: 100%;
        width: 100%;
        background-color: rgba(62, 63, 63, 0.6);
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        z-index: 1000;
        font-size: 15px;
        color: black;
        text-align: left;
        font-weight: 500;
    }
    .popup {
        background: #FFFFFF;
        position: relative;
        top:15%;
        margin: 0 auto;
        height: 540px;
        width: 800px;
        box-shadow: 2px 2px 20px 1px;
        overflow-x: auto;
    }
    .popup-header{
        padding: 15px;
        display: flex;
        font-size: 17px;
    }
    .popup-header {
        border-bottom: 1px solid #eeeeee;
        color: #fe6139;
    }
    .popup-body {
        position: relative;
        margin-left: 10%;
        padding:  10px;
    }
    .btn-green {
        color: white;
        border: 1px solid #4AAE9B;
        border-radius: 2px;
        padding: 5px 25px;
        margin-left: 5%;
    }
    form span{
        display: inline-block;
        margin-right: 10px;
    }
    form span i{
        color: #fe6139;
        display: inline-block;
        margin-right: 5px;
        font-size: 18px;
    }
    .close{
        position: absolute;
        right: 20px;
        top:10px;
        font-size: 28px;
        cursor: pointer;
        color: #fe6139;
        opacity: 1;
    }
    input[type='text']{
        width: 50%;
        color: #2a2a2a;
        height: 35px;
        margin: 10px 0;
        border-radius: 4px;
        padding-left: 20px;
        border:1px solid #cccccc;
        -webkit-appearance:none;/*去除阴影边框*/
        outline: none;
        -webkit-tap-highlight-color:rgba(0,0,0,0);/*点击高亮的颜色*/

    }
    input[type='radio']{
        margin-right: 10px;
    }
    input:focus{
        border: 1px solid #fe8d5c;
        background-color: rgba(254, 125, 79, 0.2);
    }
    .phone{
        width: 35% !important;
    }
    select{
        width: 50%;
        height: 35px;
        padding: 5px 15px;
        color: #2a2a2a;
        border: 1px solid #c4c4c6 !important;
        margin-bottom: 10px;
        margin-left: 2px;
        border-radius: 4px;
    }
    .btn-submit button:nth-child(1){
        margin-left: 30%;
        background:linear-gradient(to right, #f1366d, #fe5745);
        border: none;
    }
    .btn-submit button:nth-child(2){
        background-color: white;
        color: #6d6d72;
        margin: 20px 0;
    }
    .radio_item{
      margin: 10px 0;
      padding-bottom: 10px;
      border-bottom: 1px solid #cccccc;
    }
</style>
