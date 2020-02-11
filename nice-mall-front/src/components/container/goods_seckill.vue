<template>
  <div id="seckill">
    <div class="goods_seckill" v-for="(item,index) in goodsSeckill" :key="item.goodsId"
         @mouseenter="change" @mouseleave="nochange" >
      <router-link :to="'/goods_detail?id='+item.goodsId" >
        <div class="goods_seckill_img">
          <img :src="item.imageMain" />
        </div>
        <div class="goods_seckill_content">
          <h4>{{item.goodsBrand}}</h4>
          <h4>{{item.goodsName}}</h4>
          <div class="goods_price">
            <span>￥{{item.goodsCurPrice}}</span>
            <span>￥{{item.goodsPrePrice}}</span>
          </div>
          <div class="goods_seckill_crazy">
            <!--<div>已抢购</div>-->
            <el-progress :text-inside="true" :stroke-width="16" :percentage="30" status="exception"></el-progress>
          </div>

        </div>
      </router-link>
    </div>
    <div v-show="hasSeckillGoods" style="text-align: center">! 暂无秒杀商品</div>
  </div>
</template>
<script>
export default {
  created(){
    this.getAllSeckill();
  },
  data(){
    return{
      goodsSeckill:[],
      hasSeckillGoods:false
    }
  },
  methods:{
    getAllSeckill(){
      this.$http.get('/buss/getgoods/'+window.localStorage.getItem('userId'), {
        params: {},
        headers: {Authorization: window.localStorage.getItem('token')}
      }).then(res=>{
        if(!res.data.status){
          this.hasSeckillGoods = true;
          this.goodsSeckill  = [];
        }
        else{
          this.goodsSeckill  = res.data.data;
        }

      }).catch(err=>{
        this.$router.push('/not_found')
      })
    },
    change(e) {
      e.target.style.boxShadow = '0 0 3px 1px #dddddd';
    },
    nochange(e) {
      e.target.style.boxShadow  = '';
    }
  }
}
</script>
<style scoped>
  #seckill{
    width: 90%;
    min-width: 1360px;
    min-height: 500px;
    margin: 10px auto;
  }
  .goods_seckill{
    width: 32%;
    height: 200px;
    border: 1px solid #dddddd;
    display: inline-block;
    margin: 8px;
  }
  .goods_seckill:after{
    content: '';
    clear: both;
    display: block;
  }
  .goods_seckill_img{
    width: 45%;
    height: 200px;
    float: left;
    border-right: 1px solid #dcdcdc;
  }
  .goods_seckill_img img{
    width: 100%;
    height: 100%;
  }
  .goods_seckill_content{
    width: 48%;
    height: 200px;
    float: left;
    padding: 5px 0;
    color: #606060;
    margin-left: 15px;
  }
  .goods_seckill_content h4{
    margin-top: 10px;
  }
  .goods_price{
    width: 100%;
    margin: 20px 0;
  }
  .goods_price span:nth-child(1){
    font-size: 23px;
    color: #fa5f62;
    font-weight: bolder;
  }
  .goods_price span:nth-child(2){
    font-size: 17px;
    display: inline-block;
    margin-left: 20px;
    color: #cccccc;
    text-decoration: line-through
  }
  .goods_seckill_crazy{
    width: 100%;
    font-size: 15px;
    padding-bottom: 10px;
  }
  .goods_seckill_crazy div{
    display: inline-block;
    width: 100%;
  }
  .goods_seckill_crazy div:nth-child(2){
    width: 75%;
  }
  h4{
    font-size: 15px;
  }
</style>
