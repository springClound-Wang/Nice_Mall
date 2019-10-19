<template>
    <div id="car_container">
        <div class="car_content">
            <div class="car_goods">
                <ul class="car_nav_list">
                    <li style="width:29%;text-align: left">
                        <span style="margin-left: 10px">Nice 宝贝&nbsp;&nbsp;&nbsp; 商品信息</span>
                    </li>
                    <li>尺码</li>
                    <li>单价</li>
                    <li>数量</li>
                    <li>颜色</li>
                    <li>小计</li>
                    <li>操作</li>
                </ul>
                <ul v-for="(item,index) in car_data " :key="item.goodsId" class="car_goods_item">
                    <label :for="item.goodsId" class="select" @click="selectedMoney()">
                    <input type="checkbox" v-model="selected" class="checked"
                        :value="item.goodsId" :id="item.goodsId">
                    <li class="car_shop_title">
                        <span class="iconfont icon-icon4" style="font-size: 18px;color: #f17374"></span>
                        <span style="margin-left: 4%">{{item.storeName}}</span>
                    </li><br>
                    <li class="first_td">
                        <img :src="item.imageMain" class="order_img"/>
                        <span>{{item.goodsName}}</span>
                    </li>
                    <li>{{item.goodsSize}}</li>
                    <li>¥ {{item.goodsPrice}}</li>
                    <li>{{item.goodsNum}}</li>
                    <li>红色</li>
                    <li>￥{{item.totalPrice}}</li>
                    <li><el-button type="primary" plain style="margin: 30% 0 0 15% !important;"
                                   @click="handleDeleteCar(item.goodsId,item.goodsColor,item.goodsSize)">删除</el-button></li>
                    </label>
                </ul>
            </div>
        </div>
        <div class="account">
            <ul>
                <li>
                    <label for="check_all" @click="checkedAll" >
                        <input type="checkbox" id="check_all" class="check_all" ><span style="display: inline-block;margin: 2px 36px">全选</span>
                    </label>
                </li>
                <li>清除失效宝贝</li>
                <li>移入收藏夹</li>
            </ul>
            <div class="account_all">
                <span>合计：￥{{all_money}}</span>
                <button @click="handleToCount">结算</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    inject:['reload'],
    data(){
      return{
          selected:[],
          car_data:'',
          all_money:0.00,
          select_data:[]
      }
    },
    computed:{

    },
    created(){
        this.getCarGoodsList();
    },
    methods:{
        //结算总金额
        selectedMoney(){
            let checkObj = document.querySelectorAll('.checked'); // 获取所有checkbox项
            let sum = 0;
            this.all_money = 0.00;
            checkObj.forEach((item,index)=>{
                if(item.checked){ // 将未勾选的checkbox选项push到绑定数组中
                    sum += parseInt(this.car_data[index].totalPrice);
                }
            });
            this.all_money = sum;
        },
        //全选
        checkedAll(e){
            let checkObj = document.querySelectorAll('.checked'); // 获取所有checkbox项
            if(e.target.checked){ // 判定全选checkbox的勾选状态
                for(let i=0;i<checkObj.length;i++){
                    if(!checkObj[i].checked){ // 将未勾选的checkbox选项push到绑定数组中
                        this.selected.push(checkObj[i].value);
                    }
                }
                //全选结算价钱
                let sum =0;
                for(let i=0;i<this.selected.length;i++){
                    sum+= parseInt(this.car_data[i].totalPrice)
                }
                this.all_money = sum;

            }else { // 如果是去掉全选则清空checkbox选项绑定数组
                this.selected = [];
                this.all_money = 0.00;
            }

        },
        //TODO 删除购物车的某条信息   ？=
        handleDeleteCar(id,color,size){
          this.$http.delete('/cart/del',{
              params:{
                goodsId:id,
                goodsColor:color,
                goodsSize:size
              },
              headers: {'Authorization':window.localStorage.getItem('token') }
          }).then(res => {
            this.$message.success('删除成功');
            this.reload();
          }).catch(err => {
            this.$message.error('删除失败');
          });
        },
        //TODO 发出请求 得到购物车 数据：
        getCarGoodsList() {
            this.$http.get('/cart/gain',{
              params:{},
              headers:{Authorization: window.localStorage.getItem('token')}
            }).then(res => {
                this.car_data = res.data.data;
                if(!this.car_data){
                  this.$message.success('您的购物车暂时还没有数据');
                }
            }).catch(err => {
                this.$message.error('查询失败');
            })

        },
        //数量++
        handleCountAdd(item){
            console.log(item.goodsNum);
            if(item.goodsNum <5) item.goodsNum++;
            else {item.goodsNum =5; alert("限购5件");}
        },
        //数量--
        handleCountLess(item){
            if(item.goodsNum >0) item.goodsNum--;
            else item.goodsNum = 0;
        },
        // TODO 结算 将选中的数据发给下一个组件 进行确认结算
        handleToCount(){

            for(let i=0;i< this.car_data.length ;i++){
                if(this.car_data[i].goodsId === parseInt(this.selected[i])){
                  this.select_data.push(this.car_data[i]);
                }
            }
            if(this.select_data.length === 0){
              this.$message.error("请选择商品！");
              return;
            }
            this.$router.push({
                    path:"/other_container/goods_order",
                    query: {select_data:JSON.stringify(this.select_data)}
                })
        }
    }
}
</script>
<style scoped>
    #car_container{
        width: 75%;
        margin: 10px auto;
    }
    .car_content {
        width: 92%;
        display: contents;
        position: relative;
        margin: 10px auto;
        border: none;
    }
    .shop_con span:nth-child(2){
        display: inline-block;
        width: 100px;
        margin-top: 2px;
        white-space: nowrap;
        -ms-text-overflow: ellipsis;
        text-overflow: ellipsis;
        overflow: hidden;
    }
    .select{
        width: 100%;
    }
    .select img{
        width: 80px;
        height: 80px;
        display: inline-block;
        position: absolute;
        top: 23%;
        left: 12%;
    }
    .select input{
        position: absolute;
        left: 0;
        top: 45%;
        border: 1px solid #dddee1;
        border-radius: 50%;
        outline: none;
        width: 18px;
        height: 18px !important;
    }
    .checkBox input[type=checkbox]:checked{
        background-color: red;
    }
    .car_content{
        width: 96%;
        margin: 10px auto;
    }
    .car_goods_item{
        height: 160px;
        margin-bottom: 20px;
        border: 1px solid #dddee1;
        border-left: 2px solid #ff2a23;
    }
    .car_nav_list{
        height: 41px;
        line-height: 45px;
        margin-top: 40px;
        border:1px solid #cfd0d3;
        width: 100%;
        margin-bottom: 10px;
    }
    .car_nav_list,.car_goods_item{
        list-style: none;
        position: relative;
        border-radius: 3px;
    }

    .car_nav_list li{
        width: 11%;
        position: relative;
        height: 40px;
        float: left;
        text-align: center;
        border-left: 1px solid #cfd0d3;
        font-weight: 500;

    }
    .car_goods_item:hover{
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
    .car_goods_item li{
        width: 11%;
        position: relative;
        height: 150px;
        line-height: 150px;
        float: left;
        text-align: center;
        font-weight: 500;
    }
    .car_nav_list ul li:nth-child(1){
        width: 35%;
    }
    .first_td{
        width: 28% !important;
        line-height: 0;
        margin-left: 2% !important;
    }
    .first_td span{
        width: 58%;
        float: left;
        margin: 9% 9% 5% 40%;
        line-height: 30px !important;
        text-align: left;
    }
    .car_goods_item{
        width: 100%;
    }
    .car_shop_title{
        width: 100%;
        height: 30px;
        position: absolute;
        top: 0;
        line-height: 2 !important;
        border-bottom: 1px solid #dddee1;
    }
    .car_shop_title span{
        display: block;
        position: absolute;
        height: 30px;
        width: 300px;
        margin-left: 20px;
        text-align: left;
        top: 0;
        left: 0;
    }
    .car_goods ul .car_shop_title {
        width: 100%;
        height: 35px;
        line-height: 35px !important;
        position: absolute;
        top: 0;
        border-bottom: 1px solid #dddee1;
    }
    /*结算*/
    .check_all{
        position: absolute;
        top: 28px;
        width: 18px;
        height: 18px !important;
    }
    .account{
        position: sticky;
        bottom: 0;
        width: 100%;
        margin: 10px auto;
        height: 70px;
        line-height: 70px;
        border: 1px solid #dddee1;
        background-color: white;
        z-index: 100;
    }
    .account ul li{
        margin-left:5%;
        float: left;
        display: inline-block;
    }
    .account ul li:nth-child(1){
        margin-left: 2%;
    }
    .account_all{
        position: absolute;
        right: 10px;
        top:0;
        font-size: 17px;
    }
    .account_all span{
        margin-right: 20px;
    }
    .account_all button{
        width: 150px;
        height: 45px;
        line-height: 36px;
        margin-top: 10px;
        color: white;
        font-weight: bolder;
        background:linear-gradient(to right, #fe7e61, #f15e85) ;
        border: 1px solid #cccccc;
        font-size: 17px;
        border-radius: 5px;
        outline: none;
    }
    .goods_details_num span{
        display: inline-block;
        padding: 1px 6px;
        height: 32px;
        width: 30px;
        line-height: 30px;
        border:1px solid #dddee1;
    }

</style>
