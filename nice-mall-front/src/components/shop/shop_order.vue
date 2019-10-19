<template>
  <div>
    <div style="padding: 20px;">
      <el-breadcrumb separator-class="el-icon-arrow-right" >
        <el-breadcrumb-item >首页</el-breadcrumb-item>
        <el-breadcrumb-item>订单</el-breadcrumb-item>
        <el-breadcrumb-item>订单列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <hr>
    <el-card shadow="never" style="margin-top: 30px">
      <span class="font-title-medium">店铺订单列表</span>
      <el-table
        border
        class="standard-margin"
        ref="goodsTable"
        :data="orderList">
        <el-table-column label="商品图片" width="120" align="center">
          <template slot-scope="scope">
            <img style="height:80px" :src="scope.row.imageMain">
          </template>
        </el-table-column>
        <el-table-column label="商品名称" width="160" align="center">
          <template slot-scope="scope">
            <span class="font-small">{{scope.row.goodsName}}</span><br>
          </template>
        </el-table-column>
        <el-table-column label="商品颜色" width="100" align="center">
          <template slot-scope="scope">
            <span class="font-small">{{scope.row.goodsColor}}</span><br>
          </template>
        </el-table-column>
        <el-table-column label="商品尺码" width="110" align="center">
          <template slot-scope="scope">
            <span class="font-small">{{scope.row.goodsSize}}</span><br>
          </template>
        </el-table-column>
        <el-table-column label="商品数量" width="100" align="center">
          <template slot-scope="scope">
            <span class="font-small">{{scope.row.goodsNum}}</span><br>
          </template>
        </el-table-column>
        <el-table-column label="实付" width="110" align="center">
          <template slot-scope="scope">
            <span class="font-small">{{scope.row.totalPrice}}</span>
          </template>
        </el-table-column>
        <el-table-column label="收件人" width="110" align="center">
          <template slot-scope="scope">{{scope.row.receiptName}}</template>
        </el-table-column>
        <el-table-column label="手机/电话" width="150" align="center">
          <template slot-scope="scope">{{scope.row.receiptPhone}}</template>
        </el-table-column>
        <el-table-column label="收货地址" width="200" align="center">
          <template slot-scope="scope">{{scope.row.toAddress}}</template>
        </el-table-column>
        <el-table-column label="发货" width="" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.sendOut"
              active-color="#13ce66"
              inactive-color="rgb(173, 176, 184)">
            </el-switch>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        class="btn-add"
        @click="handleSubmit"
        size="mini"
        style="float: right;
        margin: 20px 0"
        type="primary" plain>
        确认提交
      </el-button>
    </el-card>
  </div>

</template>
<script>
 export default {
   data(){
     return{
       orderList:[
         {
           goodsColor: "香槟",
           goodsId: 6,
           goodsName: " iPhone XS Max 双卡双待 全网通",
           goodsNum: 1,
           goodsPrice: 8499,
           goodsSize: "8GB+256GB",
           imageMain: "http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569078094103.jpeg",
           storeName: "西西比的家",
           totalPrice: 8499,
           userId: 4,
           payMoney: 8668,
           payStatus: "paid",
           receiptPhone: "13259964094",
           toAddress: "陕西省西安市长安区西安邮电大学",
           receiptName: "小猪佩奇",
           sendOut:false,
         },
         {
           goodsColor: "香槟",
           goodsId: 5,
           goodsName: " iPhone XS Max 双卡双待 全网通",
           goodsNum: 1,
           goodsPrice: 8499,
           goodsSize: "8GB+256GB",
           imageMain: "http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569078094103.jpeg",
           storeName: "西西比的家",
           totalPrice: 8499,
           userId: 4,
           payMoney: 8668,
           payStatus: "paid",
           receiptPhone: "13259964094",
           toAddress: "陕西省西安市长安区西安邮电大学",
           receiptName: "小猪佩奇",
           sendOut:false
         },
       ],
       OrderSendList:[], // todo 发货列表
     }
   },
   computed: {
     getSendOutList(){
       this.orderList.forEach((item)=> {
         if (item.sendOut === true && this.OrderSendList.indexOf(parseInt(item.goodsId)) === -1) {
           this.OrderSendList.push(parseInt(item.goodsId));
         }
       });
       return this.OrderSendList;
     }
   },
   methods:{
     //TODO 得到订单列表


     // TODO 提交选择发货的商品
     handleSubmit(){
       this.OrderSendList = this.getSendOutList;
       this.$http.post('/buss/spike',{
         orderSend:this.OrderSendList
       },{
         headers:{ Authorization: window.localStorage.getItem('token')}
       }).then(res=>{

       }).catch(err=>{

       })
     }
   }
 }
</script>
<style>

</style>
