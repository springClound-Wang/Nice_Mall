<template>
    <div id="home-container">
        <!--首页轮播图-->
        <div class="swipe">

            <mt-swipe :auto="6000">
                <mt-swipe-item><img src="../../image/bg1.jpg"/></mt-swipe-item>
                <mt-swipe-item><img src="../../image/bg2.jpg"/></mt-swipe-item>
                <mt-swipe-item><img src="../../image/bg4.jpg"/></mt-swipe-item>
                <mt-swipe-item><img src="../../image/bg5.jpg"/></mt-swipe-item>
            </mt-swipe>
        </div>
        <!--<hr>-->
        <!--首页主体推荐-->
        <div class="content">
            <div class="con">
                <i></i>
                <p>每日必看专区</p>
            </div>
            <div class="goods-one">
                <span class="goods-title"> 今日推荐 Nice好物</span>
                <div class="goods-item"  v-for="item in goods" :key="item.goods_id"
                     @mouseenter="item_enter" @mouseleave="item_leave">
                    <router-link :to="'/goods_detail?id='+item.goods_id" >
                        <img src="../../image/goods1.jpg" class="goods-img"/>
                        <div class="goods-desc">
                            <span class="goods-name">{{item.goods_name}}</span><br>
                            <span class="price-desc">心动价</span>
                            <span style="margin: 0 10px">￥{{item.goods_price}}</span>
                            <span style="text-decoration:line-through;color: #6d6d72">￥{{item.goods_orgprice}}</span>
                        </div>
                    </router-link>
                </div>
            </div>
            <div class="goods-two">
                <span class="goods-title"> 最后疯抢 3折封顶</span>
                <div class="goods-item"  v-for="item in goods" :key="item.goods_id"
                     @mouseenter="item_enter" @mouseleave="item_leave">
                    <router-link :to="'/goods_detail?id='+item.goods_id" >
                        <img src="../../image/goods2.jpg" class="goods-img"/>
                        <div class="goods-desc">
                            <span class="goods-name">{{item.goods_name}}</span><br>
                            <span class="price-desc">心动价</span>
                            <span style="margin: 0 10px">￥{{item.goods_price}}</span>
                            <span style="text-decoration:line-through;color: #6d6d72">￥{{item.goods_orgprice}}</span>
                        </div>
                    </router-link>
                </div>
            </div>

            <div class="con">
                <i></i>
                <p>精选专区</p>
            </div>

            <div class="goods-select" v-for="item in goodsSelect" :key="item.goods_type_id">
                <router-link :to="'/goods_list?type='+item.all_type_name">
                    <div>
                        <img :src="'../src/image/select'+item.goods_type_id+'.jpg'" @mouseenter="enter"
                             @mouseleave="leave"/>
                        <div class="select-into">
                            <span class="iconfont icon-xinbaniconshangchuan-"></span>
                            <span>进入选购</span>
                        </div>
                    </div>
                    <div class="select_name">{{item.type_title}}</div>
                    <span class="select_desc">{{item.type_desc}}</span><span>折封顶</span>
                </router-link>
            </div>
        </div>

    </div>

</template>

<script>
    export default {
        name: "home.vue",
        data(){
            return{
                goodsSelect:'',
                goods:''
            }
        },
        created(){
            this.getTypeGoodsList(); //得到这个页面请求的所有数据
        },
        methods:{
            //发请求
            getTypeGoodsList(){
                this.$http.get('/home').then(res=>{
                    this.goodsSelect = res.data.goodsSelect;
                    this.goods = res.data.goods;
                }).catch(err=>{
                    console.log(err);
                })
            },
            //进入
            item_enter(e){
                e.target.style.transform = 'scale(1.03)';
            },
            //离开
            item_leave(e){
                e.target.style.transform = 'scale(1)';
            },
            //进入
            enter(e){
                e.target.style.transform = 'scale(1.03)';
                e.target.style.opacity = '0.4'
            },
            //离开
            leave(e){
                e.target.style.transform = 'scale(1)';
                e.target.style.opacity = '1'
            },
            //显示
            show(e){
                e.target.lastChild.style.display ='block'
            },
            //隐藏
            hide(e){
                e.target.lastChild.style.display ='none'
            }
        }
    }
</script>

<style scoped>
    #home-container{
        width: 100%;
        margin: 0 auto;
        min-width: 1260px;
    }

    /*分区标题*/
    .con{
        position:relative;
        height:30px;
        line-height: 30px;
        margin:40px auto;
        text-align: center;
    }
    .con i{
        display:block;
        height:1px;
        background: #b2b2b2;
        position:absolute;
        top:20px;
        width:100%;
    }
    .con p{
        display:inline-block;
        font-size: 18px;
        padding:5px 20px;
        text-align: center;
        margin:0 auto;
        position:relative;
        z-index:2;
        color: white;
        background-color: #fa5f62;
    }

    /*推荐商品样式*/
    .goods-one,.goods-two{
        width: 48%;
        margin-top: 20px;
        border: 1px solid #cccccc;
        display: inline-block;
    }
    .goods-one{
        margin-left: 15px;
        background:linear-gradient(to top , #a8edea, #fed6e3);
    }
    .goods-two{
        margin-left: 1%;
        background:linear-gradient(to top , #ebc0fd, #d9ded8);
    }
    .goods-item{
        width:30%;
        background: #ffffff;
        display: inline-block;
        position: relative;
        top:0;
        margin: 10px;
        border: 1px solid #cccccc;
    }
    .goods-title{
        display: block;
        width: 100%;
        text-align: center;
        margin: 10px 0;
        font-size: 18px;
    }
    .goods-img{
        width: 100%;
        height: 205px;
    }
    .goods-desc{
        width: 95%;
        height: 60px;
        z-index: 200;
        margin:5px;
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

    /*精选专区*/
    .goods-select{
        width: 45%;
        height: 280px;
        position: relative;
        margin-top: 20px;
        display: inline-block;
        margin-left: 3%;
    }
    .goods-select img{
        width: 100%;
        height: 100%;
    }
    .select_name{
        font-size: 18px;
        font-weight: bolder;
        margin: 10px;
    }
    .select_desc{
        font-size: 20px;
        font-weight: bolder;
        margin: 10px 1px 10px 10px;
        color: #fa5f62;
    }
    .select-into{
        position: absolute;
        top:-1px;
        display: inline-block;
        padding: 5px;
        font-size: 19px;
        margin: 5px 10px 10px;
    }
    .select-into .iconfont{
        color: #ff5125;
        font-size: 30px;
        margin: 10px;
        font-weight: bolder;
    }
    .select-into span:nth-child(2){
        position: relative;
        top: -3px;
    }
    /*轮播图*/
    .swipe{
        position: relative;
        width: 100%;
        margin: 0 auto;
        height: 470px;
    }
    .mint-swipe{
        width: 100%;
        height: 100%;
    }
    .mint-swipe img{
        width: 92%;
        height: 440px;
        position: absolute;
        left:4.5%;
        border: 10px solid #ffffff;
        z-index: 100;
    }
    .mint-swipe-indicator {
        width: 8px;
        height: 8px;
        display: inline-block;
        border-radius: 100%;
        background: #b5b5b5 !important;
         opacity: 1 !important;
        margin: 0 3px;
    }
    .mint-swipe-indicator .is-active {
        background: #ee6168 !important;
    }
    .content{
        position: relative;
        width: 90%;
        margin: 10px auto;
    }

</style>