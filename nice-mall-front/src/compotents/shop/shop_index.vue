<template>
    <div class="app-container">
        <div class="total-layout">
            <el-row :gutter="20">
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/images/home_order.png" class="total-icon">
                        <div class="total-title">今日订单总数</div>
                        <div class="total-value">200</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/images/home_today_amount.png" class="total-icon">
                        <div class="total-title">今日销售总额</div>
                        <div class="total-value">￥5000.00</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/images/home_yesterday_amount.png" class="total-icon">
                        <div class="total-title">昨日销售总额</div>
                        <div class="total-value">￥5000.00</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/images/home_yesterday_amount.png" class="total-icon">
                        <div class="total-title">总销售额</div>
                        <div class="total-value">￥10000.00</div>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div class="un-handle-layout">
            <div class="layout-title">待处理事务</div>
            <div class="un-handle-content">
                <el-row :gutter="20">
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">待付款订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">已完成订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">待确认收货订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">待发货订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">新缺货登记</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">待处理退款申请</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">已发货订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">待处理退货订单</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="un-handle-item">
                            <span class="font-medium">广告位即将到期</span>
                            <span style="float: right" class="color-danger">(10)</span>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
        <div class="overview-layout">
            <el-row :gutter="20">
                <el-col :span="12">
                    <div class="out-border">
                        <div class="layout-title">商品总览</div>
                        <div style="padding: 40px">
                            <el-row>
                                <el-col :span="6" class="color-danger overview-item-value">100</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">400</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">50</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">500</el-col>
                            </el-row>
                            <el-row class="font-medium">
                                <el-col :span="6" class="overview-item-title">已下架</el-col>
                                <el-col :span="6" class="overview-item-title">已上架</el-col>
                                <el-col :span="6" class="overview-item-title">库存紧张</el-col>
                                <el-col :span="6" class="overview-item-title">全部商品</el-col>
                            </el-row>
                        </div>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="out-border">
                        <div class="layout-title">用户总览</div>
                        <div style="padding: 40px">
                            <el-row>
                                <el-col :span="6" class="color-danger overview-item-value">100</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">200</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">1000</el-col>
                                <el-col :span="6" class="color-danger overview-item-value">5000</el-col>
                            </el-row>
                            <el-row class="font-medium">
                                <el-col :span="6" class="overview-item-title">今日新增</el-col>
                                <el-col :span="6" class="overview-item-title">昨日新增</el-col>
                                <el-col :span="6" class="overview-item-title">本月新增</el-col>
                                <el-col :span="6" class="overview-item-title">会员总数</el-col>
                            </el-row>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
        <div class="statistics-layout">
            <div class="layout-title">现有商品</div>
            <el-row :span="20">
                <div style="padding: 10px;border-left:1px solid #DCDFE6">
                    <el-date-picker
                            style="float: right;z-index: 1;margin-right: 20px"
                            size="small"
                            v-model="orderCountDate"
                            type="daterange"
                            align="right"
                            unlink-panels
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                </div>
            </el-row>
            <el-row>
                <el-col :span="5" v-for="(item, index) in goods" :key="index">
                    <el-card :body-style="{ padding: '0px' }" shadow="hover">
                        <img src="../../image/goods1.jpg" class="image">
                        <div style="padding: 14px;">
                            <div class="goods_name">{{item.goods_name}}</div>
                            <div class="bottom clearfix">
                                <el-tag type="info" class="time">￥{{item.goods_price}}</el-tag>
                                <div class="btn_all">
                                    <el-button type="primary" icon="el-icon-edit" circle @click="handleModifyGoods(item.goods_id)"></el-button>
                                    <el-button  type="danger" icon="el-icon-delete"
                                                style="margin-top: -10px !important;
                                                margin-left: 15% !important;"
                                                circle
                                                @click="handleDeleteGoods(item.goods_id)"></el-button>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>

            </el-row>
        </div>
    </div>
</template>

<script>

    export default {
        name: 'home',
        data() {
            return {
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            let start = new Date();
                            start.setFullYear(2018);
                            start.setMonth(10);
                            start.setDate(1);
                            end.setTime(start.getTime() + 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一月',
                        onClick(picker) {
                            const end = new Date();
                            let start = new Date();
                            start.setFullYear(2018);
                            start.setMonth(10);
                            start.setDate(1);
                            end.setTime(start.getTime() + 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                orderCountDate: '',

                goods:''
            }
        },
        created(){
            this.initOrderCountDate();
            this.getTypeGoodsList();
        },
        methods:{
            //时间
            initOrderCountDate(){
                let start = new Date();
                start.setFullYear(2019);
                start.setMonth(8);
                start.setDate(1);
                const end = new Date();
                end.setTime(start.getTime() + 1000 * 60 * 60 * 24 * 7);
                this.orderCountDate=[start,end];
            },
            //请求所有商品
            getTypeGoodsList() {
                this.$http.get('/getallgoods').then(res => {
                    this.goods = res.data.goods;
                }).catch(err => {
                    console.log(err);
                })
            },
            //删除商品
            handleDeleteGoods(id){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http.delete('http://120.78.64.17:8086/nice-mall-backend/deletegoods', {
                        params: {goodsId:id},
                        headers: {Authorization: window.localStorage.getItem('token')}
                    }).then(res =>{
                        this.$message({
                            type: 'success',
                            message: '删除成功!',
                            // message: res.data.message
                        });
                    }).catch(err =>{

                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            //修改商品
            handleModifyGoods(goods_id){

            }
        }
    }
</script>

<style scoped>
    .app-container {
        margin-top: 40px;
        margin-left: 120px;
        margin-right: 120px;
        min-width: 1036px;
    }
    .total-layout {
        margin-top: 20px;
    }
    .total-frame {
        border: 1px solid #DCDFE6;
        padding: 20px;
        height: 100px;
    }
    .total-icon {
        color: #409EFF;
        width: 60px;
        height: 60px;
    }
    .total-title {
        position: relative;
        font-size: 16px;
        color: #909399;
        left: 70px;
        top: -50px;
    }
    .total-value {
        position: relative;
        font-size: 18px;
        color: #606266;
        left: 70px;
        top: -40px;
    }
    .un-handle-layout {
        margin-top: 20px;
        border: 1px solid #DCDFE6;
    }
    .layout-title {
        color: #606266;
        padding: 15px 20px;
        background: #F2F6FC;
        font-weight: bold;
    }
    .un-handle-content {
        padding: 20px 40px;
    }
    .un-handle-item {
        border-bottom: 1px solid #EBEEF5;
        padding: 10px;
    }
    .overview-layout {
        margin-top: 20px;
    }
    .overview-item-value {
        font-size: 24px;
        text-align: center;
    }
    .overview-item-title {
        margin-top: 10px;
        text-align: center;
    }
    .out-border {
        border: 1px solid #DCDFE6;
    }
    .statistics-layout {
        margin-top: 20px;
        border: 1px solid #DCDFE6;
    }
    .time {
        font-size: 16px;
        color: #fe0e09 !important;
    }
    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }
    .button {
        padding: 0;
        float: right;
    }
    .image {
        width: 100%;
        height: 220px;
        display: block;
    }
    .clearfix:before, .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
    .goods_name {
        width: 95%;
        display: inline-block;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .el-button+.el-button {
        margin-left: 10px;
        margin-right: 10px;
    }
    .btn_all{
        width: 80%;
        height: auto;
        position: relative;
        margin-left: 20%;
        margin-top: -13%;
    }
</style>
