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
                                <li><el-button type="danger">立即付款</el-button></li>
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
                            <li class="order_item"><span>订单号：1213232424</span></li><br>
                            <li class="first_td">
                                <img :src="item.car_goods_img" class="order_img"/>
                                <span>{{item.car_goods_name}}</span>
                            </li>
                            <li>{{item.car_goods_size}}</li>
                            <li>¥ {{item.car_goods_price}}</li>
                            <li>{{item.car_goods_num}}</li>
                            <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                            <li class="order_status">待付款<br><el-link type="primary">订单详情</el-link></li>
                            <li><el-button type="danger">立即付款</el-button></li>
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
                            <li><el-button type="danger">联系卖家</el-button></li>
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
                            <li><el-button type="danger">查看物流</el-button></li>
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
                            <li><el-button type="danger">立即评价</el-button></li>
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
                    console.log(err);
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
        border: 1px solid #babab4;
        border-left: 2px solid #ff2a23;
    }
    .sel_nav_list{
        height: 41px;
        line-height: 45px;
        margin-top: 40px;
        border: 1px solid #babab4;
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
        border-left: 1px solid #babab4;
    }
    .sel_item li{
        width: 10%;
        position: relative;
        height: 150px;
        line-height: 150px;
        float: left;
        text-align: center;

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
        margin: 10px 15px;
    }
    .goods ul .order_item{
        width: 100%;
        height: 30px;
        position: absolute;
        top: 0;
        line-height: 2 !important;
        border-bottom: 1px solid #d7d7d0;
    }
    .order_item span{
        display: block;
        position: absolute;
        height: 30px;
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