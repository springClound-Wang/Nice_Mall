<template>
  <div id="seckill">
    <div class="goods_seckill" v-for="(item,index) in goodsSeckill" :key="item.goodsId">
      <router-link :to="'/goods_detail?id='+item.goodsId" >
        <div class="goods_seckill_img">
          <img :src="item.imgMain" />
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
  </div>
</template>
<script>
export default {
  data(){
    return{
      goodsSeckill:[
        {
          goodsId:1,
          goodsName:'春户外连帽印花夹克显瘦防晒皮肤衣女式外套薄款透气运动女式风衣',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569752849451.jpeg'
        },   {
          goodsId:3,
          goodsName:'新品牛仔裤新款高腰韩版宽松学生阔腿九分cec直筒裤子',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569756009098.jpeg'

        },   {
          goodsId:2,
          goodsName:'【驻颜小金瓶】24K黄金烟酰胺精华液2支装 100ml/支',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569755187867.jpeg'

        },
        {
          goodsId:4,
          goodsName:'春户外连帽印花夹克显瘦防晒皮肤衣女式外套薄款透气运动女式风衣',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569752849451.jpeg'
        },   {
          goodsId:5,
          goodsName:'新品牛仔裤新款高腰韩版宽松学生阔腿九分cec直筒裤子',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569755187867.jpeg'

        },   {
          goodsId:6,
          goodsName:'【驻颜小金瓶】24K黄金烟酰胺精华液2支装 100ml/支',
          goodsBrand:'植美村',
          goodsCurPrice:'58',
          goodsPrePrice:'338',
          goodsCrazy:'90',
          imgMain:'http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/goods/1569752849451.jpeg'

        },
      ]
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
    width: 50%;
    height: 200px;
    float: left;
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
    margin-left: 5px;
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
