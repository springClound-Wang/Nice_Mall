<!--所有订单-->
<template>
    <div id="order_container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="所有订单" name="first">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width: 35%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data" class="sel_item">
                            <li class="order_item"><span>订单号：1213232424</span></li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">等待买家付款<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button type="warning" plain style="margin-left: 20px">查看详情</el-button></li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待付款" name="second">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width: 35%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data" class="sel_item">
                            <li class="order_item">
                              <span>订单号：1213232424</span>
                              <span style="position: absolute;left: 85%;color: #f1b447;font-size: 15px">30分钟后订单过期</span>
                            </li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">待付款<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button type="danger" plain style="margin-left: 20px">立即付款</el-button></li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待发货" name="third">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width: 35%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data" class="sel_item">
                            <li class="order_item"><span>订单号：1213232424</span></li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">待发货<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button type="primary" plain style="margin-left: 20px">联系卖家</el-button></li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待收货" name="fourth">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width: 35%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data" class="sel_item">
                            <li class="order_item"><span>订单号：1213232424</span></li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">待收货<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button type="info" plain style="margin-left: 20px">查看物流</el-button></li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待评价" name="fifth">
                <div class="order_content">
                    <div class="goods">
                        <ul class="sel_nav_list">
                            <li style="width: 35%;text-align: left;padding-left: 15px;">Nice 宝贝</li>
                            <li>尺码</li>
                            <li>单价</li>
                            <li>数量</li>
                            <li>实付款</li>
                            <li>交易状态</li>
                            <li>交易操作</li>
                        </ul>
                        <ul v-for="(item,index) in sel_data" class="sel_item">
                            <li class="order_item"><span>订单号：1213232424</span></li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">待评价<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button  type="success" plain style="margin-left: 20px">立即评价</el-button></li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: 'first',
                multipleSelection: [],
                sel_data:[]
            };
        },
        created(){
          this.getOrderGoodsList()
        },
        methods: {
            //TODO 发出请求 得到订单 数据：
            getOrderGoodsList() {
                this.$http.get('/getcarlist?userId=1').then(res => {
                    this.sel_data = res.data;
                }).catch(err => {
                  this.$message.error("查询订单失败");
                })
            },
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>
<style scoped>
    #order_container{
        width: 80%;
        margin: 20px auto;
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
        width: 35%;
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
        text-align: left;
        top: 0;
        left: 0;
    }
    .first_td{
        width: 35% !important;
        line-height: 0;
    }
    .first_td span,.order_status{
        width: 70%;
        float: right;
        margin-top: 40px;
        line-height: 30px !important;
        text-align: left;
    }
    .sel_item{
        width: 100%;
    }
</style>
<style>
  .el-tabs__nav-wrap {
    overflow: hidden;
    position: relative;
    width: 58%;
    margin: 20px auto;
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
  }
</style>
