<template>
  <div id="pay-container" v-if="!loading">
    <div class="pay_title">订单状态<span style="font-size: 16px;"><a style="color:#f14649" href="javascript:history.back(-1)">返回</a></span></div>
    <div class="is_pay">
      <span v-if="status === 0">
        由于商品未支付 <br>
        30分钟后自动过期<br>
        . . . . . . .
      </span>
      <span v-if="status === 1">
        商品已支付 <br>
        等待发货<br>
        . . . . . . .
      </span>
      <span v-if="status === 2">
        商品已发货 <br>
        查看发货详情<br>
        . . . . . . .
      </span>
      <span v-if="status === 3">
        商品已确认收货 <br>
        查看交易详情，进行评价<br>
        . . . . . . .
      </span>
      <img src="../../assets/image/wait.jpg"/>
    </div>
    <div class="pay_title">订单详情</div>
    <div class="goods">
      <table border="1" cellspacing="0" cellpadding="0">
        <tr>
          <td class="goods_pay_td">订单编号：{{orderId}}</td>
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
    </div>
    <div class="pay_title" style="margin-top: 50px">支付信息 </div>
    <div class="pay">
      <div class="pay_order">
        <div>
          <span>地址信息</span><hr>
          <span>收货人：{{order_details.receiptName}}</span>
          <span>收件人电话：{{order_details.receiptPhone}}</span>
          <span style="font-size: 19px;"><b style="color: #fe5745">支付：￥{{order_details.totalMoney}}</b> </span>
          <span>送货至：{{order_details.receiptAddress}}</span>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    created(){
      this.getOrder();
    },
    data(){
      return{
        loading: false,
        //TODO 前面的组件传过来的orderId
        orderId:this.$route.query.orderId,
        status:null,
        order_details:{},  //订单详情
        pay_data:null //支付商品详情
      }
    },
    methods:{
      // TODO 得到order信息
      getOrder(){
        this.$http.get('/order/detail',{
          params:{orderId:this.orderId},
          headers: {Authorization: window.localStorage.getItem('token')}
        }).then(res=>{
          this.order_details = res.data.data;
          this.pay_data = res.data.data.payData;
          this.status = res.data.data.orderStatus;
        }).catch(err=>{
          this.$message.error(err.data.message);
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
  .is_pay{
    width: 100%;
    height: 150px;
    font-size: 17px;
    color: #f16051;
    text-align: left;
    font-weight: bolder;
    border: 1px solid #cccccc;
    /*background-color: rgba(255, 241, 194, 0.7);*/
  }
  .is_pay span{
    margin: 25px;
    display: inline-block;
  }
  .is_pay img{
    width: 450px;
    height: 150px;
    float: right;
  }
  /*商品详情*/
  .goods table{
    width: 100%;
    border: 1px solid #cccccc;
  }
  .goods table tr{
    border-bottom: 1px solid #d6d6d6;
    font-size: 14px;
    height: 100px;
  }
  .goods table tr:nth-child(1){
    height: 40px;
    margin-bottom: 10px;

  }
  .goods table tr td{
    border: none;
    border-bottom: 1px solid #cccccc;
    text-align: center;
  }
  .goods table tr td img{
    width: 80px;
    height: 80px;
    margin-left: 10px;
  }
  .pay span{
    font-size: 15px;
    display: inline-block;
    margin: 10px 0;
    font-weight: bolder;
  }
  .pay_order{
    position: relative;
    margin: 20px 0;
    border: 1px solid #cccccc;
  }
  .pay_order div{
    text-align: left;
    margin: 0 20px;
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
  .goods_pay_td{
    text-align: left !important;
    margin-left: 10px;
    padding-left: 5px;
  }
  .car_goods_name{
    position: absolute;
    width: 250px;
    margin-left: 20px;
    margin-top: 20px;
  }

</style>
