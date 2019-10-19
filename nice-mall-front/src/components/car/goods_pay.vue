<template>
    <div id="pay-container">
        <div class="pay_title">确认收货地址 </div>
        <div class="address">
            <ul>
                <li class="addresslist" v-for="(item,index) in address"
                    :class="choose === index ? 'choose_address':''" @click="chooseAddress(index)">
                        <div class="address-left">
                            <div class="buy-address-detail">
                                <span>{{item.receiptName}} </span>
                                <span>{{item.receiptPhone}}</span>
                            </div>
                            <div class="default-address">
                                <span class="buy-line-title">收货地址：</span>
                                <span class="buy-address">
                                    {{item.receiptArea}}&nbsp;&nbsp;{{item.detailAddress}}
                                </span>
                            </div>
                            <span class="default" :class="choose === index ? 'deftip':''" @click="chooseTacit(index)">设为默认</span>
                            <span class="default" :class="isTacit === index ? 'deftip':''">默认地址</span>
                        </div>
                        <div>
                            <div class="handle">
                                <a href="#">编辑</a>
                                <span>|</span>
                                <a href="" @click="delAddress(item.addressId)">删除</a>
                            </div>
                        </div>
                    </li>
                    <li class="createaddress" title="新增地址">
                      <span @click="showPopup" style="margin-top: 15px;display: block;">+</span>
                      <popup v-show="isPopupVisible" @close="closePopup"></popup>
                    </li>
            </ul>

        </div>
        <div class="pay_title">商品清单
            <router-link to="/other_container/goods_car"><span>返回购物车修改商品</span></router-link>
        </div>
        <div class="goods">
            <table border="1">
                <tr>
                    <td class="goods_pay_td">Nice 发货订单</td>
                    <td>尺码</td>
                    <td>颜色</td>
                    <td>单价</td>
                    <td>数量</td>
                </tr>
                <tr v-for="(item,index) in pay_data" :key="item.goodsId">
                    <td  class="goods_pay_td">
                        <img :src="item.imageMain"/>
                        <span class="car_goods_name">{{item.goodsName}}</span>
                    </td>
                    <td>{{item.goodsSize}}</td>
                    <td>{{item.goodsColor}}</td>
                    <td>¥ {{item.goodsPrice}}</td>
                    <td>{{item.goodsNum}}</td>
                </tr>
            </table>
            <div class="pay_desc">
                <span>运费： 免运费</span>
                <span>已享受优惠： - ¥20</span>
                <span>本组商品金额：<span style="color: #fe5745;margin-left: 5px">¥ {{pay_money}}</span></span>
            </div>
        </div>
        <div class="pay_title" style="margin-top: 50px">支付信息 </div>
        <div class="pay">
            <span>支付方式</span>
            <div class="pay-type">
                <label>
                   <input type="checkbox" value="1" name="pay_type" checked class="check-type"/>
                </label>
                <div>在线支付</div>
                <div>
                    支持绝大多数银行卡、支付宝支付等，可在提交订单后选择。<br>
                    如中国建设银行、中国工商银行、中国农业银行、中国银行、支付宝支付等
                </div>
            </div>

            <div class="pay_order">
                <div>
                    <span>商品金额： ¥ {{pay_money + 20}}</span>
                    <span>优惠券优惠： - ¥ 20</span>
                    <span style="font-size: 20px;color: #fe5745"><b>待支付：</b> ¥ {{pay_money}}</span>
                    <span>送货至：{{toaddress}}</span>
                    <span>收货人: {{username}} {{telephone}}</span>
                </div>
                <div class="pay_money">
                    <el-button type="text" @click="open">提交订单</el-button>
                </div>
            </div>
        </div>
        <el-dialog title="确认支付商品" :visible.sync="dialogFormVisible" >
          <div slot="footer" class="dialog-footer">
            <el-button @click="exitBuy">取 消</el-button>
            <el-button type="primary" @click="toBuy">确 定</el-button>
          </div>
        </el-dialog>
    </div>
</template>
<script>
import popup from '../tools/popup.vue';
export default {
    inject:['reload'],
    components: {
        popup
    },
    created(){
        this.getAddress();
    },
    data(){
        return{
            //TODO 前面的组件传过来的  支付数据
            pay_data:JSON.parse(this.$route.query.pay_data),
            pay_money:JSON.parse(this.$route.query.pay_money),
            isPopupVisible: false, //弹窗
            toaddress:'',
            username:'',
            telephone:'',
            dialogFormVisible: false,
            choose:-1, //选择
            isTacit:-1, //是否设置成默认地址
            address:[]  //地址数组
        }
    },
    methods:{
        getAddress(){
          this.$http.get('/personal/getaddr',{
            params:{userId:window.localStorage.getItem('userId')},
            headers: {Authorization: window.localStorage.getItem('token')}
          }).then(res=>{
            this.address = res.data.data;
          }).catch(err=>{
            this.$message.error(err.data.message);
          })
        },
        //选择地址
        chooseAddress(index){
            this.choose = index;
            this.toaddress = this.address[index].receiptArea+this.address[index].detailAddress;
            this.username = this.address[index].receiptName;
            this.telephone = this.address[index].receiptPhone;
        },
        //TODO 删除地址
        delAddress(addressId){
          this.$http.delete('/personal/deladdr',{
            params:{
              addressId:addressId
            },
            headers:{Authorization: window.localStorage.getItem('token')}
          }).then(res=>{
            this.$message.success(res.data.message);
            this.reload();
          }).catch(err=>{
            this.$message.error(err.data.message)
          })
        },
        chooseTacit(index){
            this.isTacit = index;
        },
        showPopup(){
            this.isPopupVisible = true
        },
        closePopup(){
            this.isPopupVisible = false
        },
        //todo 支付订单
        open() {
          if(!window.localStorage.getItem('userId')){
            this.$message.error('请先登录');
            return;
          }
          if(!this.toaddress){
            this.$message.error('请先选择地址');
            return;
          }
          else{
            this.dialogFormVisible = true;
            this.$confirm('是否确认支付?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$message({
                type: 'success',
                message: '购买成功!'
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消购买'
              });
            });
          }

        },
        // TODO 购买失败  发送订单 信息 未支付
        exitBuy(){
          this.dialogFormVisible = false;
          this.$http.post('/deletegoods', {
            payData:this.pay_data,
            payMoney:this.pay_money,
            toAddress:this.toaddress,
            receiptName:this.username,
            receiptPhone:this.telephone,
            payStatus: 'unPaid'
          }, {
            headers: {Authorization: window.localStorage.getItem('token')}
          }).then(res => {

          }).catch(err => {

          });
        },
        toBuy(){
          // TODO 购买成功  发送订单 信息 已支付
          this.dialogFormVisible = true;
          this.$http.post('/deletegoods', {
            payData:this.pay_data,
            payMoney:this.pay_money,
            toAddress:this.toaddress,
            receiptName:this.username,
            receiptPhone:this.telephone,
            payStatus: 'paid'
          }, {
            headers: {Authorization: window.localStorage.getItem('token')}
          }).then(res => {
            this.$message({
              type: 'success',
              message: '购买成功!',
            });
          }).catch(err => {

          })
        }
    }

}
</script>
<style scoped>
    #pay-container{
        width: 80%;
        margin: 20px auto;
    }
    .address,.goods,.pay{
        position: relative;
        width: 96%;
        min-height: 200px;
        margin: 0 auto;
    }
    .address ul{
        display: inline-block;
    }
    .address ul:after{
        content: '';
        clear: both;
        display: block;
    }
    .address li{
        position: relative;
        float: left;
        margin-top: 5px;
    }
    /*标题*/
    .pay_title{
        position: relative;
        padding: 6px 15px;
        background: #f9f9f9;
        border: 1px solid #e2e2e2;
        border-left: 3px solid #fe5745;
        font-size: 17px;
        margin: 20px 0;
    }
    .pay_title span{
        font-size: 13px;
        float: right;
        color: #7bb3e5;
        cursor: pointer;
    }
    /*默认地址样式*/
    .addresslist,.createaddress{
        display: inline-block;
        width: 258px;
        height: 150px;
        margin-right: 10px;
        padding: 10px;
        border: 1px solid #dbdbdb;
    }
    .addresslist{
        opacity: .7;
        background: url("../../assets/image/addressbg2.jpg") 100% 100% no-repeat;
        background-size: cover;
    }
    /*选中的样式*/
    .address li.choose_address{
        opacity: 1;
        background: url("../../assets/image/addressbg.png") 100% 100% no-repeat;
        background-size: cover;
    }
    /*新增地址*/
    .createaddress{
        /*position: absolute;*/
        font-size: 80px;
        font-weight: bolder;
        color: #cccccc;
        text-align: center;
    }
    /*默认地址*/
    .default{
        display: none;
    }
    .deftip{
        display: block;
        position: absolute;
        top: 0;
        right: 5px;
        padding: 2px 3px;
        background: #dddddd;
        cursor: pointer;
    }
    .address-left {
        width: 100%;
        float: left;
        position: relative;
    }
    .buy-address-detail{
        font-size: 15px;
        font-weight: bolder;
    }
    .handle{
        display: inline-block;
        position:absolute;
        bottom: 20px;
        right: 15px;
    }
    .buy-address{
        display: block;
    }
    .buy-line-title{
        display: block;
        margin-top: 10px;
    }
    /*商品详情*/
    .goods table{
        width: 100%;
        border: none;
    }
    .goods table tr{
        border-bottom: 1px solid #d6d6d6;
        font-size: 14px;
        height: 100px;
    }
    .goods table tr:nth-child(1){
        height: 40px;
    }
    .goods table tr td{
        border: none;
        text-align: center;
    }
    .goods table tr td img{
        width: 80px;
        height: 80px;
    }
    .pay_desc{
        position: absolute;
        right: 0;
        margin: 10px 0;
    }
    .pay_desc span{
        margin-left: 20px;
        font-weight: bolder;
        font-size: 14px;
    }
    .pay span{
        font-size: 15px;
        display: inline-block;
        margin: 10px 0;
        font-weight: bolder;
    }
    /*支付方式按钮*/
    .check-type{
        width: 16px;
        height: 16px;
    }
    /*支付方式*/
    .pay-type{
        position: relative;
        border:1px solid #7cbf13;
        background-color: #f1f5eb;
        height: 80px;
        padding: 10px;
    }
    .pay-type div{
        display: inline-block;
        margin-left: 20px;
        color: #999;
    }
    .pay-type div:nth-child(2){
        position: absolute;
        top: 20px;
        left: 40px;
        font-size: 16px;
        color: black;
    }
    .pay-type div:nth-child(3){
        position: relative;
        left: 11%;
        margin-top: 10px;
    }
    .pay_order{
        position: relative;
        margin: 20px 0;
        border: 1px solid #cccccc;
    }
    .pay_order div{
       text-align: right;
        margin-right: 20px;
    }
    .pay_order div span{
        display:block;
        font-size: 15px;
        font-weight:initial;
        margin: 13px 0;
    }
    span b{
        color:black ;
    }
    /*提交订单按钮*/
    .pay_money{
        background-color: #f3f3f3;
        padding: 10px;
        width: 98.5%;
    }
    .pay_money button{
        width: 145px;
        height: 40px;
        background: linear-gradient(to right, #f1366d, #fe5745);
        color: white;
        border: none;
    }
    .goods_pay_td{
        text-align: left !important;
    }
    .car_goods_name{
        position: absolute;
        width: 250px;
        margin-left: 20px;
    }
</style>
<style>
  #pay-container .el-dialog__wrapper {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    overflow: auto;
    margin: 0;
    background: rgba(96, 96, 96, 0.5);
  }
  #pay-container .el-dialog {
    position: relative;
    margin: 0 auto 50px;
    border-radius: 2px;
    -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
    box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 30%;
  }
</style>
