<!--所有订单-->
<template>
    <div id="order_container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="所有订单" name="first"  >
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width:28%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>颜色</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data"  class="sel_item">
                            <li class="order_item">
                              <span class="order_id">订单编号：{{item.orderId}}</span>
                              <span class="no_pay_message" v-if="item.statusStr === '待付款'">订单30分钟后自动过期</span>
                            </li><br>
                            <li class="first_td">
                              <img :src="item.imageMain" class="order_img"/>
                              <span>{{item.goodsName}}</span>
                            </li>
                            <li>{{item.goodsSize}}</li>
                            <li>¥ {{item.goodsPrice}}</li>
                            <li>{{item.goodsColor}}</li>
                            <li>{{item.goodsNum}}</li>
                            <li>￥{{item.totalPrice}}</li>
                            <li class="order_status" style="margin-top: 5%">{{item.statusStr}}<br></li>
                            <li style="width: 9%;"><el-button type="success" plain @click="handleGetDetails(item.orderId)">查看详情</el-button></li>
                        </ul>
                        <ul v-if="!sel_data" class="no_sel">
                          <li>! 暂无订单</li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待付款" name="zero" >
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width:28%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>颜色</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data"  class="sel_item">
                          <li class="order_item">
                            <span class="order_id">订单编号：{{item.orderId}}</span>
                            <span class="no_pay_message">订单30分钟后自动过期</span>
                          </li><br>
                          <li class="first_td">
                            <img :src="item.imageMain" class="order_img"/>
                            <span>{{item.goodsName}}</span>
                          </li>
                          <li>{{item.goodsSize}}</li>
                          <li>¥ {{item.goodsPrice}}</li>
                          <li>{{item.goodsColor}}</li>
                          <li>{{item.goodsNum}}</li>
                          <li>￥{{item.totalPrice}}</li>
                            <li class="order_status">待付款<br><el-link type="primary" @click="handleGetDetails(item.orderId)">订单详情</el-link></li>
                            <li style="width: 9%;">
                              <el-button type="danger" plain
                                          @click="handleToPayOrder(item.orderId)">立即付款</el-button>
                            </li>
                        </ul>
                        <ul v-if="!sel_data" class="no_sel">
                          <li>! 暂无待付款订单</li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待发货" name="one">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width:28%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>颜色</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                          <ul v-for="(item,index) in sel_data"  class="sel_item">
                            <li class="order_item"><span>订单编号：{{item.orderId}}</span></li><br>
                            <li class="first_td">
                              <img :src="item.imageMain" class="order_img"/>
                              <span>{{item.goodsName}}</span>
                            </li>
                            <li>{{item.goodsSize}}</li>
                            <li>¥ {{item.goodsPrice}}</li>
                            <li>{{item.goodsColor}}</li>
                            <li>{{item.goodsNum}}</li>
                            <li>￥{{item.totalPrice}}</li>
                            <li class="order_status">待发货<br><el-link type="primary" @click="handleGetDetails(item.orderId)">订单详情</el-link></li>
                            <li style="width: 9%;"><el-button type="warning" plain>联系卖家</el-button></li>
                        </ul>
                        <ul v-if="!sel_data" class="no_sel">
                          <li>! 暂无待发货订单</li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待收货" name="second">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width:28%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>颜色</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data"  class="sel_item">
                          <li class="order_item"><span>订单编号：{{item.orderId}}</span></li><br>
                          <li class="first_td">
                            <img :src="item.imageMain" class="order_img"/>
                            <span>{{item.goodsName}}</span>
                          </li>
                          <li>{{item.goodsSize}}</li>
                          <li>¥ {{item.goodsPrice}}</li>
                          <li>{{item.goodsColor}}</li>
                          <li>{{item.goodsNum}}</li>
                          <li>￥{{item.totalPrice}}</li>
                            <li class="order_status">待收货<br><el-link type="primary" @click="handleGetDetails(item.orderId)">订单详情</el-link></li>
                            <li style="width: 9%;"><el-button type="success" plain @click="handleBuiedGoods(item.orderId)">确认收货</el-button></li>
                        </ul>
                        <ul v-if="!sel_data" class="no_sel">
                          <li>! 暂无待收货订单</li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待评价" name="third" >
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width:28%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>颜色</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data"  class="sel_item">
                            <li class="order_item"><span>订单编号：{{item.orderId}}</span></li><br>
                            <li class="first_td">
                                <img :src="item.imageMain" class="order_img"/>
                                <span>{{item.goodsName}}</span>
                            </li>
                            <li>{{item.goodsSize}}</li>
                            <li>¥ {{item.goodsPrice}}</li>
                            <li>{{item.goodsColor}}</li>
                            <li>{{item.goodsNum}}</li>
                            <li>￥{{item.totalPrice}}</li>
                            <li class="order_status">待评价<br><el-link type="primary" @click="handleGetDetails(item.orderId)">订单详情</el-link></li>
                            <li style="width: 9%;"><el-button  type="primary" plain>立即评价</el-button></li>
                        </ul>
                        <ul v-if="!sel_data" class="no_sel">
                          <li>! 暂无待评价订单</li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                activeName: 'first',
                multipleSelection: [],
                sel_data:[],
            };
        },
        created(){
          this.getOrderData()
        },
        methods: {
            //发出请求 得到订单 数据
          getOrderData() {
              if(!window.localStorage.getItem('username')){
                this.$message.warning('请先登录，再查询订单');
                this.$router.push('/login_sign/login_phone');
              }
              else{
                this.$http.get('/order/queryall',{
                  params:{},
                  headers:{Authorization: window.localStorage.getItem('token')}
                }).then(res => {
                  this.sel_data = res.data.data;
                }).catch(err => {
                  this.$message.error('查询失败');
                })
              }

            },
            // todo 确认收货 将id 发送给 后台 后台修改待收货为  待评价
            handleBuiedGoods(orderId){
              this.$http.put('/order/confirm',{
                  orderId:orderId,
                  orderStatus: 3
                },{
                headers:{Authorization: window.localStorage.getItem('token')}
              }).then(res => {
                this.$message.success(res.data.message);
                this.reload();
              }).catch(err => {
                this.$message.success(err.data.message)
              })
            },
            //根据点击不同的tab  查看不同的订单
            handleClick(tab, event) {
                let orderStatus;
                console.log(tab.name);
                if(tab.name === 'first'){
                  this.getOrderData();
                  return;
                }
              if(tab.name === 'zero')  orderStatus = 0;
              else if(tab.name === 'one') orderStatus = 1;
              else if(tab.name === 'second') orderStatus = 2;
              else if(tab.name === 'third') orderStatus = 3;
              this.$http.get('/order/query',{
                params:{orderStatus:orderStatus},
                headers:{Authorization: window.localStorage.getItem('token')}
              }).then(res => {
                this.sel_data = res.data.data;
              }).catch(err => {
                this.$message.error('查询失败');
              })
            },
            // TODO 查看订单详情
            handleGetDetails(id){
              this.$router.push({path:'/other_container/order_details',
                query:{
                  orderId:id
                }
              })
            },
            // TODO  待付款重新支付订单  发id
            handleToPayOrder(id){
              this.$http.post('/order/pay', {
                orderId:id,
                orderStatus:"1"
              }, {
                headers: {Authorization: window.localStorage.getItem('token')}
              }).then(res => {
                this.reload();
                this.$message.success(res.data.message)
              }).catch(err => {
                this.$message.error(err.data.message)
              })
            },

        }
    }
</script>
<style scoped>
    #order_container{
        width: 80%;
        margin: 20px auto;
        font-family: ahoma,Helvetica,Arial,'宋体',sans-serif;
    }
    .order_content{
        width: 96%;
        margin: 10px auto;
        /*border: 1px solid #eae8e8;*/
    }
    .sel_item{
        height: 170px;
        margin-bottom: 20px;
        border: 1px solid #dddee1;
        border-left: 2px solid #ff2a23;
    }
    .sel_nav_list{
        height: 41px;
        width: 100%;
        line-height: 45px;
        margin-top: 40px;
        border: 1px solid #dddee1;
        margin-bottom: 10px;
    }
    .sel_nav_list,.sel_item{
        list-style: none;
        position: relative;
    }
    .sel_nav_list li{
        width: 10%;
        position: relative;
        font-weight: 600;
        height: 40px;
        float: left;
        text-align: center;
        border-left: 1px solid #dddee1;
    }
    .sel_item li{
        width: 10%;
        position: relative;
        height: 150px;
        line-height: 150px;
        float: left;
        text-align: center;
        margin-left: 3px;
    }
    .sel_item:hover{
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
    .sel_nav_list ul li:nth-child(1){
        width: 28%;
    }
    .goods ul:after{
        content: '';
        display: block;
        clear: both;
    }
    .order_img{
        width: 80px;
        height: 80px;
        margin: 30px 15px;
    }
    .goods ul .order_item{
        width: 100%;
        height: 35px;
        position: absolute;
        top: 0;
        border-bottom:1px solid #dddee1;
    }
    .order_item span{
        display: block;
        position: absolute;
        height: 35px;
        line-height: 35px !important;
        width: 300px;
        margin-left: 20px;
    }
    .no_pay_message{
      text-align: right;
      right: 15px;
      font-weight: bolder;
      color: #f18343;
      top: 0;
    }
    .order_id{
      text-align: left;
      left: 0;
      top: 0;
    }
    .first_td{
        width: 28% !important;
        line-height: 0;
    }
    .first_td span{
        width: 64%;
        float: right;
        margin-top: 25px;
        line-height: 30px !important;
        text-align: left;
    }
    .order_status{
      width: 64%;
      float: right;
      line-height: 30px !important;
      text-align: left;
      color: rgb(95, 147, 229);
      font-weight: bolder;
      height: 30px !important;
      margin-top: 3%;
    }
    .sel_item{
        width: 100%;
    }
    .no_sel{
      width: 100%;
    }
    .no_sel li{
      text-align: center;
      display: block;
      margin-top: 25px;
      padding-top: 20px;
      color: #5f93e5;
      font-size: 19px;
      font-weight: bolder;
      border-top: 1px solid #cccccc;
    }

</style>
<style>
  .el-tabs__nav-wrap {
    overflow: hidden;
    position: relative;
    width: 58%;
    margin: 20px auto;
  }
  .el-tabs__item:hover {
    color: #fe5745;
    cursor: pointer;
  }
  .el-tabs__item {
    padding: 0 20px;
    height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 40px;
    display: inline-block;
    list-style: none;
    font-size: 18px;
    font-weight: 500;
    color: #303133;
    position: relative;
    width: 30%;
    text-align: center;
    font-weight: bolder;
  }
</style>
