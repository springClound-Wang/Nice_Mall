<template>
  <div id="shop_order">
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
        style="margin-top: 10px;"
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
          <template slot-scope="scope">{{scope.row.receiptAddress}}</template>
        </el-table-column>
        <el-table-column label="发货" width="" align="center">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.ship"
              active-color="#13ce66"
              inactive-color="rgb(173, 176, 184)">
            </el-switch>
          </template>
        </el-table-column>
      </el-table>
      <el-button
        @click="handleNoShip"
        size="mini"
        style="float: left;
        margin: 20px 0"
        type="danger" plain>
        未发货
      </el-button>
      <el-button
        @click="handleShip"
        size="mini"
        style="float: left;
        margin: 20px"
        type="success" plain>
        已发货
      </el-button>
      <el-button
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
   inject:['reload'],
   data(){
     return{
       orderList:[],
     }
   },
   computed: {
     getSendOutList(){
       let OrderSend = [];
       this.orderList.forEach((item)=> {
         console.log(item.ship);
         if (item.ship === true && OrderSend.indexOf(parseInt(item.orderId)) === -1) {
           OrderSend.push(item.orderId.toString());
         }
       });
       return OrderSend;
     }
   },
   created(){
     this.handleGetOrder();
   },
   methods:{
     //TODO 得到全部订单列表
     handleGetOrder(){
       this.$http.get('/buss/allorder',{
         params:{ },
         headers:{ Authorization: window.localStorage.getItem('token')}
       }).then(res=>{
         this.orderList = res.data.data
       }).catch(err=>{
         this.$message.success(err.data.message)
       })
     },
     // TODO 提交选择发货的商品
     handleSubmit(){
       this.$http.put('/buss/ship',{
         orderId:this.getSendOutList, //监听数据变化
         orderStatus:"2"
       },{
         headers:{ Authorization: window.localStorage.getItem('token')}
       }).then(res=>{
          this.$message.success(res.data.message);
          this.reload();
       }).catch(err=>{
         this.$message.success(err.data.message)
       })
     },
     //未发货
     handleNoShip(){
       this.$http.get('/buss/status',{
         params:{ orderStatus:1 },
         headers:{ Authorization: window.localStorage.getItem('token')}
       }).then(res=>{
         this.orderList = res.data.data
       }).catch(err=>{
         this.$message.success(err.data.message)
       })
     },
     //已发货
     handleShip(){
       this.$http.get('/buss/status',{
         params:{ orderStatus:2 },
         headers:{ Authorization: window.localStorage.getItem('token')}
       }).then(res=>{
         this.orderList = res.data.data
       }).catch(err=>{
         this.$message.success(err.data.message)
       })
     }


     /*
      orderStatus：0  未支付
      orderStatus：1  已支付= 待发货
      orderStatus：2  已发货 = 待收货 （店铺确认发货）
      orderStatus：3   待评价 （用户确认收货）
      */
   }
 }
</script>
<style>
  #shop_order{
    font-family: ahoma,Helvetica,Arial,'宋体',sans-serif;
  }
  #shop_order .btn-add {
    margin: 10px 0;
  }
</style>
