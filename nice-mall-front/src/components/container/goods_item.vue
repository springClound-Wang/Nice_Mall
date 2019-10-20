<!--搜索单个商品-->
<template>
    <div id="goods-container">
        <div class="goods_type_header">全部商品 > <span style="color: #fe5745;">{{goodsname}}</span></div>
        <div id="all_goods_list" v-if="status">
            <div class="goods-item" v-for="item in goods" :key="item.goodsId">
                <!--点击跳到详情-->
                <router-link :to="'/goods_detail?id='+item.goodsId" >
                    <div @mouseenter="change" @mouseleave="nochange">
                        <img :src="item.imageMain" class="goods-img"/>
                        <div class="goods-desc">
                            <span class="goods-name">{{item.goodsName}}</span><br>
                            <span class="price-desc">心动价</span>
                            <span style="margin: 0 10px">￥{{item.goodsPrePrice}}</span>
                            <span style="text-decoration:line-through;color: #6d6d72">￥{{item.goodsCurPrice}}</span>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
        <div v-if="!status" class="not_found">
            <span>!暂无此类商品</span>
        </div>
    </div>
</template>
<script>
    export default {
        name: "goods_item.vue",
        data() {
            return {
                goodsname: this.$route.query.goodsname,
                url:this.$route.query.urls,
                goods: '', //所有商品
                status:true
            }
        },
        created() {
            this.getTypeGoodsList(); //得到这个页面请求的所有数据
        },
        methods: {
            //发请求：
            getTypeGoodsList() {
                this.$http.get('/'+this.url+this.goodsname).then(res => {
                    this.status = res.data.status;
                    if(res.data.status === true) {
                        this.goods = res.data.data;
                    }

                }).catch(err => {
                    this.$router.push('/not_found');
                })
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
    .not_found{
        width: 100%;
        height: 300px;
        margin: 10px auto;
        text-align: center;
        font-size: 18px;
        color: #6d6d72;
    }
    #all_goods_list{
      width: 90%;
      margin: 20px auto;
      /*text-align: center;*/
    }
    .goods-item{
      width:18%;
      background: #ffffff;
      display: inline-block;
      position: relative;
      top:0;
      margin: 20px 14px 20px 10px;
      border-radius: 5px;
      border: 1px solid #e2e2e2;
      box-shadow: 0 0 4px 1px #dedede;
    }
    .goods-img{
      width: 100%;
      height: 235px;
      /*padding: 5px 10px;*/
    }
    .goods-desc{
      width: 96%;
      margin: -3px auto;
      height: 60px;
      z-index: 200;
      border-radius: 5px;
      padding: 5px;
      border-top: 1px solid #cccccc;
    }
    .price-desc{
      display: inline-block;
      padding: 5px;
      background-color: #fa5f62;
      margin: 0 10px 10px 2px;
      color: white;
    }
    .goods-name{
      width: 95%;
      display: inline-block;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .goods_title{
      font-size: 18px;
      color: #6d6d72;
      display: inline-block;

    }
</style>
