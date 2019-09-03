<!--分类商品-->
<template>
    <div id="goods_container">
        <img :src="hot_head_img"/>
        <div id="hot-type-sort">
            <div class="hot-select">热门选择</div>
            <ul>
                <li v-for="(item ,index) in hot_type" :key="index"
                    @mouseenter="list_change" @mouseleave="list_nochange">
                    <img :src="item.goods_type_img"/><br>
                    <span>{{item.goods_type_name}}</span>
                </li>
            </ul>
        </div>

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
    name: "goods_list.vue",
    data(){
        return{
            type:this.$route.query.type,
            goods:'', //所有商品
            hot_type:'' ,//商品类别
            hot_head_img:''//商品大图
        }
    },
    created(){
        this.getTypeGoodsList(); //得到这个页面请求的所有数据
    },
    methods:{
        //发请求：
        getTypeGoodsList(){
         this.$http.get('/gettypegoodslist?type='+this.$route.query.type).then(res=>{
             this.hot_type = res.data.hot_type;
             this.goods = res.data.goods;
             this.hot_head_img = res.data.hot_head_img;
         }).catch(err=>{
             console.log(err);
         })
        },
        change(e){
            e.target.style.boxShadow = 'rgb(192, 190, 190) 0px 0px 11px 4px';
            e.target.style.borderRadius = '5px';
            e.target.lastChild.style.background = 'linear-gradient(to top,#a8edea,#fed6e3)';
        },
        nochange(e){
            e.target.style.boxShadow = '';
            e.target.lastChild.style.background = '';
        },
        list_change(e){
            e.target.style.transform = 'scale(1.1)';
        },
        list_nochange(e){
            e.target.style.transform = 'scale(1)';
        }
    }
}
</script>
<style>
    #goods_container{
        width: 90%;
        margin: 0 auto;
        border: 1px solid #dddddd;
    }
    #goods_container >img{
        width: 100%;
        height: 330px;
    }
    /*分类列表*/
    #hot-type-sort{
        width: 90%;
        margin: 10px auto;
        border: 1px solid #fa5f62;
        border-radius: 5px;
    }
    #hot-type-sort ul li{
        float: left;
        width: 10%;
        text-align: center;
    }
    #hot-type-sort ul:after{
        content: '';
        display: block;
        clear: both;
    }
    .hot-select{
        font-size: 17px;
        padding: 5px 10px;
        background-color: #fa5f62;
        display: inline-block;
        color: white;
    }
    /*商品展示*/
    #all_goods_list{
        width: 90%;
        margin: 20px auto;
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
        padding: 5px 10px;
    }
    .goods-desc{
        width: 100%;
        height: 80px;
        z-index: 200;
        padding: 5px 10px;
        border-top: 1px solid #cccccc;
        /*background-color: #edc1c4;*/
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

</style>