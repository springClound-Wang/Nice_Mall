<!--分类商品-->
<template>
    <div id="goods_container">
        <img :src="hot_head_img"/>
        <div id="hot-type-sort">
            <div class="hot-select">热门选择</div>
            <ul style="padding: 10px;">
              <li v-for="(item ,index) in hot_type" :key="index"
                    @mouseenter="list_change" @mouseleave="list_nochange">
                  <router-link :to="{path:'/goods_item',query: {goodsname:item.typeName,urls:'home/'}}" @click.native="flushCom">
                    <img :src="item.imageDetails" width="120" height="90"/><br>
                    <span>{{item.typeName}}</span>
                  </router-link>
              </li>
            </ul>
        </div>

        <div id="all_goods_list">
            <hr>
            <div class="goods_title" v-show="!goodsStatus">！暂无商品</div>
            <div class="goods-item" v-for="item in goods" :key="item.goodsId">
                <!--点击跳到详情-->
                <router-link :to="'/goods_detail?id='+item.goodsId" >
                    <div @mouseenter="change" @mouseleave="nochange">
                        <img :src="item.imageMain" class="goods-img"/>
                        <div class="goods-desc">
                            <span class="goods-name">{{item.goodsName}}</span><br>
                            <span class="price-desc">心动价</span>
                            <span style="margin: 0 8px">￥{{item.goodsCurPrice}}</span>
                            <span style="text-decoration:line-through;color: #6d6d72">￥{{item.goodsPrePrice}}</span>
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
            goodsStatus:false,
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
         this.$http.get('/home/sort/'+this.$route.query.type).then(res=>{
             this.goodsStatus = res.data.status; //商品数据状态
             this.hot_type = res.data.data.hotType;
             this.goods = res.data.data.goods;
             this.hot_head_img = res.data.data.hotHeadImg;
         }).catch(err=>{
             this.$router.push('/not_found');
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
            e.target.style.transform = 'scale(1.05)';
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
        margin: 10px auto;
        border: 1px solid #dddddd;
    }
    #goods_container >img{
        width: 100%;
        height: 370px;
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
        /*text-align: center;*/
        min-height: 200px;
    }
    #all_goods_list:after{
      content: '';
      clear: both;
      display: block;
    }
    .goods-item{
        width:18%;
        background: #ffffff;
        float: left;
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
      height: 80px;
      z-index: 200;
      padding: 5px;
      margin-top: -5px;
      border-top: 1px solid #cccccc;
    }
    .price-desc{
        display: inline-block;
        padding: 5px;
        background-color: #fa5f62;
        margin: 10px 10px 10px 2px;
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
        margin-top: 20px;
        width: 100%;
        text-align: center;
    }
</style>
