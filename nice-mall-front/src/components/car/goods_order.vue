<template>
    <div id="order-container">
        <table>
            <thead>
            <tr>
                <td>精选特卖</td>
                <td>颜色</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in order_data " :key="item.goodsId">
                <td>
                    <img :src="item.imageMain" class="order_img"/>
                    <div class="carslist_name">
                        {{item.goodsName}}<br>
                        <div style="margin-top: 10px">尺码：{{item.goodsSize}}</div>
                    </div>
                </td>
                <td>{{item.goodsColor}}</td>
                <td>￥{{item.goodsPrice}}</td>
                <td>
                    <div class="goods_details_num" >
                        <span @click="handleCountAdd(item)">+</span><span v-model="item.goodsNum">{{item.goodsNum}}</span><span @click="handleCountLess(item)">-</span>
                    </div>
                </td>
                <td>￥{{item.totalPrice}}</td>
            </tr>
            </tbody>
        </table>

        <div class="total_pay">
            <div class="prefer">
                <span>已成功使用1张优惠券</span>&nbsp;&nbsp;&nbsp;&nbsp;
                <span>- ¥ 20</span>
            </div>
            <div class="total">
                <div>共<span style="color: #fe6139"> {{order_data.length}} </span>
                    件商品&nbsp; &nbsp;商品金额:&nbsp;
                    <span style="font-weight: bold">￥ {{selectedMoney}}</span></div>
                <div>优惠券优惠:&nbsp; <span style="font-weight: bold">- ¥ 20</span></div>
                <div>总金额（未含运费）：<span>￥{{selectedMoney}}</span></div>
            </div>
            <div class="to_pay">
                <button @click="handleToPay">立即结算</button>
            </div>
        </div>

        <div class="other">
            <div class="recommend">推荐</div>
            <div id="all_goods_list">
                <div class="goods-item" v-for="item in goods" :key="item.goods_id">
                    <!--点击跳到详情-->
                    <router-link :to="'/goods_detail?id='+item.goods_id" >
                        <div @mouseenter="change" @mouseleave="nochange" class="goods_item_every">
                            <img :src="item.goods_img" class="goods-img"/>
                            <div class="goods-desc">
                                <span class="goods-name">{{item.goods_name}}</span><br>
                                <span class="price-desc">心动价</span>
                                <span style="margin-left: 10%">￥{{item.goods_price}}</span>
                            </div>
                        </div>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {

    data(){
        return{
            goods:'',
            // TODO 前面购物车传过来的数据
            order_data:JSON.parse(this.$route.query.select_data),
            all_money:0.00
        }
    },
    computed:{
        //结算总金额
        selectedMoney()
        {
            let sum = 0;
            this.all_money = 0.00;
            this.order_data.forEach((item, index) => {
                sum += parseInt(this.order_data[index].goodsPrice) * this.order_data[index].goodsNum;
            });
            this.all_money = sum;
            return this.all_money;
        }
    },
    created(){
        this. getOrderGoodsList();
    },
    methods:{
        //TODO 发请求：推荐
        getOrderGoodsList() {
            this.$http.get('/getgoodsorder?goodsname').then(res => {
                this.goods = res.data.goods;
            }).catch(err => {
                console.log(err);
            })
        },
        //TODO 到下一个组件进行 支付
        handleToPay(){
            this.$router.push({path:'/other_container/goods_pay',
                query:{
                    pay_data:JSON.stringify(this.order_data),
                    pay_money:JSON.stringify(this.all_money)
                }
            })
        },
        //数量++
        handleCountAdd(item){
            console.log(item.goodsNum);
            if(item.goodsNum <5) item.goodsNum++;
            else {item.goodsNum = 5; this.$message.warning("限购5件");}
        },
        //数量--
        handleCountLess(item){
            if(item.goodsNum >0) item.goodsNum--;
            else item.goodsNum = 0;
        },
        change(e) {
            e.target.style.borderRadius = '5px';
            e.target.style.transform = 'scale(1.02)';
            e.target.lastChild.style.background = 'linear-gradient(to top,#a8edea,#fed6e3)';
        },
        nochange(e) {
            e.target.style.transform = 'scale(1)';
            e.target.lastChild.style.background = '';
        }
    }
}
</script>
<style scoped>
    #order-container{
        width: 80%;
        margin: 20px auto;
    }
    #all_goods_list{
        text-align: left;
    }
    .carslist_name{
        width: 270px;
        display: inline-block;
        text-align: justify;
        position: absolute;
        left: 23%;
        top: 25%;
        padding: 5px;
    }
    .goods_details_num span{
        display: inline-block;
        padding: 1px 6px;
        border: 1px solid #cccccc;
        cursor: pointer;
    }
    table{
        width:86%;
        margin: 0 auto;
        border: none;
    }
    tbody{
        width: 100% ;
    }
    tr{
        border-bottom:1px solid #cccccc;
        border-left:1px solid #cccccc;
        border-right:1px solid #cccccc;
        height: 115px;
    }
    tbody tr td{
        text-align: center;
        position: relative;
        width: 12%;
    }
    thead tr td{
        padding-left: 6%;
    }
    thead tr td:nth-child(1){
        padding-left: 2%;
    }
    tbody tr td:nth-child(1),thead tr td:nth-child(1){
        width: 35%;

    }
    thead tr:nth-child(1){
        height: 45px;
        border-top: 1px solid #cccccc;
        border-left: 3px solid #f1487f;
        background-color: #f9f9f9;
        padding-left: 2%;
    }
    .order_img{
        float: left;
        margin-left: 20px;
        width: 70px;
        height: 70px;
        margin-top: 28px;
    }
    .total_pay{
        width: 86%;
        position:relative ;
        margin: 15px auto;
        border: 1px solid #cccccc;
    }
    .to_pay{
        width: 100%;
        height: 55px;
        font-size: 17px;
        border-top: 1px solid #cccccc;
        background-color: #f9f9f9;
    }
    .to_pay button{
        width: 145px;
        height: 40px;
        background: linear-gradient(to right, #f1366d, #fe5745);
        color: white;
        float: right;
        margin: 5px 10px;
        border: 1px solid #cccccc;
        font-size: 15px;
        border-radius: 5px;
        outline: none;
    }
    .total{
        width: 100%;
        text-align: right;
    }
    .total div{
        display: block;
        margin: 10px;
        font-size: 13px;
    }
    .total div:nth-child(3) span{
        font-size: 18px;
        color: #fe6139;
    }
    .prefer{
        display: inline-block;
        position: absolute;
        top: 20px;
        left: 20px;
        color: #808080;
        font-size: 15px;
    }
    .other{
        width:86%;
        margin: 20px auto;
        border-top: 2px solid #fe6e5a;
    }
    .recommend{
        width: 100%;
        height: 40px;
        line-height: 40px;
        margin-top: 10px;
        border: 1px solid #dddddd;
        border-left: 2px solid #fe6e5a;
        background-color: #f9f9f9;
        padding-left: 15px;
    }
    #all_goods_list{
        width: 100% !important;
    }
    .goods-item {
        width: 17% !important;
        background: #ffffff;
        float: left;
        height: 230px;
        position: relative;
        margin: 20px 14px 20px 10px;
        border-radius: 5px;
        border: 1px solid #e2e2e2;
        -webkit-box-shadow: 0 0 4px 1px #dedede;
        box-shadow: 0 0 4px 1px #dedede;
    }
    .goods_item_every{
        float: left;
        width: 100%;
    }
    #all_goods_list:after{
        content: '';
        display: block;
        clear: both;
    }
    .goods-img {
        width: 100%;
        height: 170px;
    }
    .goods-desc {
        width: 94%;
        height: 47px;
        z-index: 200;
        border-radius: 5px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        font-size: 14px;
        padding: 5px;
        border-top: 1px solid #cccccc;
        margin-top: -4px;
    }
    .price-desc {
        display: inline-block;
        padding: 2px;
        background-color: #fa5f62;
        margin: 4px 43px 0 2px;
        color: white;
    }

</style>
