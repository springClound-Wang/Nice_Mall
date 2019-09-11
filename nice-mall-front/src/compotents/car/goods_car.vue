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
                    <li>小计</li>
                    <li>操作</li>
                </ul>
                <ul v-for="(item,index) in car_data " :key="item.car_goods_id" class="car_goods_item">
                    <label :for="item.car_goods_id" class="select" @click="selectedMoney()">
                    <input type="checkbox" v-model="selected" class="checked"
                        :value="item.car_goods_id" :id="item.car_goods_id">
                    <li class="car_shop_title">
                        <span class="iconfont icon-icon4" style="font-size: 18px;color: #f17374"></span>
                        <span style="margin-left: 4%">敬爱的旧爱店铺</span>
                    </li><br>
                    <li class="first_td">
                        <img :src="item.car_goods_img" class="order_img"/>
                        <span>{{item.car_goods_name}}</span>
                    </li>
                    <li>{{item.car_goods_size}}</li>
                    <li>¥ {{item.car_goods_price}}</li>
                    <li>{{item.car_goods_num}}</li>
                    <li>￥{{item.car_goods_price*item.car_goods_num}}</li>
                    <li><el-button type="primary" plain style="margin: 30% 0 0 15% !important;">删除</el-button></li>
                    </label>
                </ul>
            </div>
        </div>
        <div class="account">
            <ul>
                <li>
                    <label for="check_all" @click="checkedAll" >
                        <input type="checkbox" id="check_all" class="check_all" ><span style="display: inline-block;margin: 2px 30px">全选</span>
                    </label>
                </li>
                <li>清除失效宝贝</li>
                <li>移入收藏夹</li>
            </ul>
            <div class="account_all">
                <!--<span>合计：￥{{all_money}}</span>-->
                <span>合计：￥{{all_money}}</span>
                <button @click="handleToCount">结算</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
      return{
          selected:[],
          car_data:'',
          all_money:0.00,
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
                    sum += parseInt(this.car_data[index].car_goods_price )* this.car_data[index].car_goods_num;
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
                    sum+= parseInt(this.car_data[i].car_goods_price )* this.car_data[i].car_goods_num
                }
                this.all_money = sum;

            }else { // 如果是去掉全选则清空checkbox选项绑定数组
                this.selected = [];
                this.all_money = 0.00;
            }

        },
        //TODO 发出请求 得到购物车 数据：
        getCarGoodsList() {
            this.$http.get('/getcarlist?userId=1').then(res => {
                this.car_data = res.data;
            }).catch(err => {
                console.log(err);
            })
        },
        //数量++
        handleCountAdd(item){
            console.log(item.car_goods_num);
            if(item.car_goods_num <5) item.car_goods_num++;
            else {item.car_goods_num =5; alert("限购5件");}
        },
        //数量--
        handleCountLess(item){
            if(item.car_goods_num >0) item.car_goods_num--;
            else item.car_goods_num = 0;
        },
        // TODO 结算 将选中的数据发给下一个组件 进行确认结算
        handleToCount(){
            let select_data =[];
            for(let i=0;i< this.selected.length ;i++){
                this.selected[i] = parseInt(this.selected[i]) -1;
                select_data.push(this.car_data[this.selected[i]]);
            }
            console.log(select_data);
            this.$router.push({
                    path:"/other_container/goods_order",
                    query: {select_data:JSON.stringify(select_data)}
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
        top: 20%;
    }
    .select input{
        position: absolute;
        left: 2%;
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
    }
    .car_nav_list,.car_goods_item{
        list-style: none;
        position: relative;
        border-radius: 3px;
    }

    .car_nav_list li{
        width: 14%;
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
        width: 14%;
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
        float: right;
        margin: 8% 9% 5% 15%;
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
        top: 23px;
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