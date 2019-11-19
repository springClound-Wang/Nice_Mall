<template>
    <div id="app-container">
        <div class="total-layout">
            <el-row :gutter="20">
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/image/home_order.png" class="total-icon">
                        <div class="total-title">今日订单总数</div>
                        <div class="total-value">200</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/image/home_today_amount.png" class="total-icon">
                        <div class="total-title">今日销售总额</div>
                        <div class="total-value">￥5000.00</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/image/home_yesterday_amount.png" class="total-icon">
                        <div class="total-title">昨日销售总额</div>
                        <div class="total-value">￥5000.00</div>
                    </div>
                </el-col>
                <el-col :span="6">
                    <div class="total-frame">
                        <img src="../../assets/image/home_yesterday_amount.png" class="total-icon">
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
                        <img :src="item.imageMain" class="image">
                        <div style="padding: 14px;">
                            <div class="goods_name">{{item.goodsName}}</div>
                            <div class="bottom clearfix">
                                <el-tag type="info" class="time">￥{{item.goodsCurPrice}}</el-tag>
                                <div class="btn_all">
                                    <el-button type="primary" icon="el-icon-edit" circle
                                               style=" float: right;margin: -10px 20px 0 10px;"
                                               @click="handleModifyGoods(item.goodsId)"></el-button>
                                    <el-button  type="danger" icon="el-icon-delete"
                                                style="float: right;margin-top: -10px !important;"
                                                circle
                                                @click="handleDeleteGoods(item.goodsId)"></el-button>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <el-dialog title="修改商品信息" :visible.sync="dialogFormVisible">
            <el-form :model="form" class="form_modify">
              <el-form-item label="商品名称" :label-width="formLabelWidth">
                <el-input v-model="form.goodsName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="商品原价" :label-width="formLabelWidth">
                <el-input v-model="form.goodsPrePrice" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="商品现价" :label-width="formLabelWidth">
                <el-input v-model="form.goodsCurPrice" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="商品库存" :label-width="formLabelWidth">
                <el-input v-model="form.storeGoodsNumber" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleModifyAll">确 定</el-button>
            </div>
          </el-dialog>
        </div>
    </div>
</template>

<script>

  export default {
    name: 'home',
    data() {
      return {
        dialogFormVisible: false,
        form: {
          goodsId:null,
          goodsName: null,
          goodsCurPrice:null,
          goodsPrePrice:null,
          storeGoodsNumber:null
        },
        formLabelWidth: '120px',
        orderCountDate: '',
        goods:'',
        modifyList:[],
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
        this.$http.get('/buss/getgoods/'+window.localStorage.getItem('userId'), {
          params: {},
          headers: {Authorization: window.localStorage.getItem('token')}
        }).then(res=>{
          this.goods  = res.data.data;
        }).catch(err=>{
          console.log(err);
        })
      },
      //删除商品
      handleDeleteGoods(id){
        this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.delete('/deletegoods', {
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
        this.dialogFormVisible = true;
        for(let i=0;i<this.goods.length;i++){
          if(this.goods[i].goodsId=== goods_id){
            this.form = this.goods[i];
          }
        }
      },
      //确认修改商品信息
      handleModifyAll(){
        this.dialogFormVisible = false;
        this.modifyList.push(this.form);
        console.log(this.modifyList)
      }
    }
  }
</script>

<style scoped>

    #app-container {
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

    .btn_all{
        width: 80%;
        height: auto;
        position: relative;
        margin-left: 28%;
        margin-top: -13%;
    }
    .form_modify{
      width: 100%;
    }

</style>
<style>
  #app-container  .el-button+.el-button {
    margin-left: 10px;
    margin-right: 10px;
  }
  #app-container  .form_modify .el-input__inner {
    -webkit-appearance: none;
    background-color: #FFF;
    background-image: none;
    border-radius: 4px;
    border: 1px solid #DCDFE6;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 40px;
    line-height: 40px;
    outline: 0;
    padding: 0 15px;
    -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    width: 85%;
  }
  #app-container .el-form-item__content {
    line-height: 40px;
    position: relative;
    font-size: 14px;
  }
  #app-container .el-button.is-circle {
    border-radius: 50%;
    padding: 7px !important;
    width: 30px;
    height: 30px;
    margin-left: 50px;
    margin-top: -10px;
  }
  #app-container .el-col-5 {
    width: 20.83333%;
    margin: 15px 21px;
  }
</style>
