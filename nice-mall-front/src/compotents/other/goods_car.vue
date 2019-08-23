<template>
    <div id="car_container">
        <table>
            <thead>
            <tr id="title">
                <td>商品信息</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in car_data " :key="item.car_goods_id">
                <td>
                    <label :for="item.car_goods_id" class="select" @click="selectedMoney()">
                        <input type="checkbox" v-model="selected" class="checked"
                               :value="item.car_goods_id" :id="item.car_goods_id">
                        <img :src="item.car_goods_img"/>
                        <div class="car_goods_name">
                            {{item.car_goods_name}}
                            <div style="margin-top: 10px">尺码：M</div>
                        </div>
                    </label>
                </td>
                <td>￥{{item.car_goods_price}}</td>
                <td>
                    <div class="goods_details_num" >
                        <span @click="handleCountAdd(item)">+</span><span v-model="item.car_goods_num">{{item.car_goods_num}}</span><span @click="handleCountLess(item)">-</span>
                    </div>
                </td>
                <td>￥{{item.car_goods_num*item.car_goods_price}}</td>
                <td>删除</td>
            </tr>
            </tbody>
        </table>
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
                <span>合计：￥{{all_money}}</span>
                <router-link to="/other_container/goods_order"><button>结算</button></router-link>
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
          all_money:0.00
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
        //发出请求：
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
        }
    }
}
</script>
<style scoped>
    #car_container{
        width: 86%;
        margin: 10px auto;
    }
    .select{
        width: 100%;
    }
    table{
        width:86%;
        margin: 0 auto;
        border: none;
    }
    tbody{
        width: 100% ;
    }
    thead tr:nth-child(1){
        background:linear-gradient(to right, #fe7e61, #f15e85);
        color: white;
        height: 50px;
    }
    tr {
        height: 115px;
        border-bottom: 1px solid #dddddd;
        border-left: 1px solid #dddddd;
        border-right: 1px solid #dddddd;
    }
    #title td{
        padding-left:6%;
    }
    tbody thead tr td,tbody tr td{
        text-align: center;
        position: relative;
        width: 12%;
    }
    tbody thead tr td:nth-child(1),tbody tr td:nth-child(1){
        width: 35%;
    }
    .select img{
        width: 80px;
        height: 80px;
        display: inline-block;
        position: absolute;
        right: 300px;
        top:27px;
    }
    .select input{
        position: absolute;
        left: 20px;
        top:30%;
        border: 1px solid #333;
        border-radius: 50%;
        outline: none;
        width: 16px;
        height: 16px !important;
    }
    .checkBox input[type=checkbox]:checked{
        background-color: red;
    }
    .car_goods_name{
        width: 200px;
        display: inline-block;
        text-align: justify;
        position: absolute;
        right: 18%;
        top: 25%;
        padding: 5px;
        font-weight: 500;
    }

    /*结算*/
    .check_all{
        position: absolute;
        top: 10px;
    }
    .account{
        position: sticky;
        bottom: 0;
        width: 86%;
        margin: 10px auto;
        height: 70px;
        line-height: 70px;
        border: 1px solid #cccccc;
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
        border: 1px solid #cccccc;
    }
</style>