<template>
    <div id="pay-container">
        <div class="pay_title">确认收货地址 </div>
        <div class="address">
            <ul>
                <li class="addresslist" v-for="(item,index) in address"
                    :class="choose === index ? 'choose_address':''" @click="chooseAddress(index)">
                        <div class="address-left">
                            <div class="buy-address-detail">
                                <span>{{item.name}} </span>
                                <span>{{item.telephone}}</span>
                            </div>
                            <div class="default-address">
                                <span class="buy-line-title">收货地址：</span>
                                <span class="buy-address">
                                    {{item.address}}
                                </span>
                            </div>
                            <span class="default" :class="choose === index ? 'deftip':''" @click="chooseTacit(index)">设为默认</span>
                            <span class="default" :class="isTacit === index ? 'deftip':''">默认地址</span>
                        </div>
                        <div>
                            <div class="handle">
                                <a href="#">编辑</a>
                                <span>|</span>
                                <a href="" onclick="delClick(this);">删除</a>
                            </div>
                        </div>
                    </li>
            </ul>
            <div class="createaddress" title="新增地址">
                <span @click="showPopup">+</span>
                <popup v-show="isPopupVisible" @close="closePopup"></popup>
            </div>
        </div>
        <div class="pay_title">商品清单
            <router-link to="/other_container/goods_car"><span>返回购物车修改商品</span></router-link>
        </div>
        <div class="goods">
            <table border="1">
                <tr>
                    <td class="goods_pay_td">Nice 发货订单</td>
                    <td>尺码</td>
                    <td>单价</td>
                    <td>数量</td>
                </tr>
                <tr v-for="(item,index) in pay_data" :key="item.car_goods_id">
                    <td  class="goods_pay_td">
                        <img :src="item.car_goods_img"/>
                        <span>{{item.car_goods_name}}</span>
                    </td>
                    <td>{{item.car_goods_size}}</td>
                    <td>¥ {{item.car_goods_price}}</td>
                    <td>{{item.car_goods_num}}</td>
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
                    <button @click="handleBuy">提交订单</button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import popup from '../tools/popup.vue';
export default {
    components: {
        popup
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
            choose:-1, //选择
            isTacit:-1, //是否设置成默认地址
            address:[
                {
                    address_id:1,
                    name:'李丹',
                    telephone:'1223323232',
                    address:'吉林省 长春市 南关区 卫星广场财富领域5A16室'
                },
                {
                    address_id:2,
                    name:'王二',
                    telephone:'1223323232',
                    address:'吉林省 长春市 南关区 卫星广场财富领域5A16室'
                }
            ]
        }
    },
    methods:{

        //选择地址
        chooseAddress(index){
            this.choose = index;
            this.toaddress = this.address[index].address;
            this.username = this.address[index].name;
            this.telephone = this.address[index].telephone;
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
        //支付购买
        handleBuy(){
            this.$http.post('/buy',{
                userId:1,
                goodsId:this.$route.query.id,
                goodsNum:this.goods_num,
                goodsSize:this.goods_size
            }).then(res=>{
                console.log(res)
            }).catch(err=>{
                console.log(err)
            })
        },
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
        margin: 0 auto;
    }
    .address ul{
        display: inline-block;
    }
    .address li{
        position: relative;
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
        width: 252px;
        height: 151px;
        margin-right: 10px;
        padding: 10px;
        border: 1px solid #dbdbdb;
    }
    .addresslist{
        opacity: .7;
        background: url("../../image/addressbg2.jpg") 100% 100% no-repeat;
        background-size: cover;
    }
    /*选中的样式*/
    .address li.choose_address{
        opacity: 1;
        background: url("../../image/addressbg.png") 100% 100% no-repeat;
        background-size: cover;
    }
    /*新增地址*/
    .createaddress{
        position: absolute;
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
        top: 23px;
        font-size: 16px;
        color: black;
    }
    .pay-type div:nth-child(3){
        position: relative;
        left:8%
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
        width: 100%;
    }
    .pay_money button{
        width: 145px;
        height: 40px;
        background: linear-gradient(to right, #f1366d, #fe5745);
        color: white;
    }
    .goods_pay_td{
        text-align: left !important;
    }
</style>
