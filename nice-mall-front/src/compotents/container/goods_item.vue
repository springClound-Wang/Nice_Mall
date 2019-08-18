<template>
    <div id="goods-container">
        <div class="goods_type_header">全部商品 > <span style="color: #fe5745;">{{goodsname}}</span></div>
        <div id="all_goods_list">
            <div class="goods-item" v-for="item in goods" :key="item.goods_id">
                <!--点击跳到详情-->
                <router-link :to="'/goods_detail?id='+item.goods_id" >
                    <div @mouseenter="change" @mouseleave="nochange">
                        <img :src="item.goods_img" class="goods-img"/>
                        <div class="goods-desc">
                            <span class="goods-name">{{item.goods_name}}</span><br>
                            <span class="price-desc">心动价</span>
                            <span style="margin: 0 10px">￥{{item.goods_price}}</span>
                            <span style="text-decoration:line-through;color: #6d6d72">￥{{item.goods_orgprice}}</span>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name: "goods_item.vue",
        data() {
            return {
                goodsname: this.$route.query.goodsname,
                goods: '', //所有商品
                hot_type: '',//商品类别
                hot_head_img: ''//商品大图
            }
        },
        created() {
            this.getTypeGoodsList(); //得到这个页面请求的所有数据
        },
        methods: {
            //发请求：
            getTypeGoodsList() {
                this.$http.get('/getgoodslist?goodsname=' + this.$route.query.goodsname).then(res => {
                    this.hot_type = res.data.hot_type;
                    this.goods = res.data.goods;
                    this.hot_head_img = res.data.hot_head_img;
                }).catch(err => {
                    console.log(err);
                })
            },
            change(e) {
                e.target.style.border = '1px solid #aea6a6';
                e.target.style.borderRadius = '5px';
                e.target.style.transform = 'scale(1.02)';
                e.target.lastChild.style.background = 'linear-gradient(to top,#a8edea,#fed6e3)';
            },
            nochange(e) {
                e.target.style.border = '';
                e.target.style.transform = 'scale(1)';
                e.target.lastChild.style.background = '';
            }
        }
    }
</script>
<style>
    #goods-container{
        width: 90%;
        margin: 0 auto;
    }
    .goods_type_header{
        width: 90%;
        margin: 10px auto;
        padding: 10px;
        border-bottom: 1px solid #cccccc;
        font-size: 17px;
    }
</style>