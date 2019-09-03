<!--所有订单-->
<template>
    <div id="order_container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="所有订单" name="first">
                <div class="order_content">
                    <div class="goods">
                        <table border="1">
                            <tr>
                                <td>Nice 宝贝</td>
                                <td>尺码</td>
                                <td>单价</td>
                                <td>数量</td>
                                <td>实付款</td>
                                <td>交易状态</td>
                                <td>交易操作</td>
                            </tr>
                            <tr  class="order_item">订单号：1213232424</tr>
                            <tr>
                                <td>
                                    <img src="../../image/goods1.jpg"/>
                                    <span>拉夏贝尔旗下拉贝缇春新品女韩版宽松学生百搭牛仔外套</span>
                                </td>
                                <td>160/84A</td>
                                <td>¥299</td>
                                <td>1</td>
                                <td>￥299</td>
                                <td>等待买家付款<br>订单详情</td>
                                <td><el-button type="danger">立即付款</el-button></td>
                            </tr>
                            <tr class="order_item">订单号：1213232424</tr>
                            <tr>
                                <td>
                                    <img src="../../image/goods1.jpg"/>
                                    <span>拉夏贝尔旗下拉贝缇春新品女韩版宽松学生百搭牛仔外套</span>
                                </td>
                                <td>160/84A</td>
                                <td>¥299</td>
                                <td>1</td>
                                <td>￥299</td>
                                <td>等待买家付款<br>订单详情</td>
                                <td><el-button type="danger">立即付款</el-button></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="待付款" name="second">待付款</el-tab-pane>
            <el-tab-pane label="待发货" name="third">待发货</el-tab-pane>
            <el-tab-pane label="待收货" name="fourth">待收货</el-tab-pane>
            <el-tab-pane label="待评价" name="fifth">待评价</el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: 'first',
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>
<style>
    #order_container{
        width: 80%;
        margin: 20px auto;
    }
    .order_content{
        width: 96%;
        margin: 10px auto;
        border: 1px solid #eae8e8;
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
    }
    .goods table tr td img{
        width: 80px;
        height: 80px;
    }
    .goods table .order_item{
        width: 100%;
        height: 35px;
        line-height: 35px;
        padding: 10px;
        margin-top: 20px;
    }
    .el-tabs__item {
        padding: 0 20px;
        height: 45px;
        text-align: center;
        width: 25% !important;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        line-height: 40px;
        display: inline-block;
        list-style: none;
        font-size: 16px !important;
        font-weight: 700 !important;
        color: #303133;
        position: relative;
    }
</style>