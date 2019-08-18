<template>
    <div id="car_container">
        <table>
            <thead>
            <tr>
                <th>商品信息</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in car_data " :key="item.car_goods_id">
                <td>
                    <label :for="item.car_goods_id" class="select">
                        <input type="checkbox" v-model="selected" :value="item.car_goods_num*item.car_goods_price" :id="item.car_goods_id">
                        <img :src="item.car_goods_img"/>
                        <div class="car_goods_name">{{item.car_goods_name}}</div>
                    </label>
                </td>
                <td>￥{{item.car_goods_price}}</td>
                <td>
                    <div class="goods_details_num" :goods_num ='item.car_goods_num'>
                        <span style="width: 0"></span>
                        <span @click="handleCountAdd">+</span><span>{{item.car_goods_num}}</span><span @click="handleCountLess">-</span>
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
                    <label for="check_all" >
                        <input type="checkbox" id="check_all" class="check_all" ><span style="display: inline-block;margin: 2px 30px">全选</span>
                    </label>
                </li>
                <li>清除失效宝贝</li>
                <li>移入收藏夹</li>
            </ul>
            <div class="account_all">
                <span>合计：{{selected}}</span>
                <button>结算</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
      return{
          selected:[],
          goods_num:'',
          car_data:'',
          all_money:0.00
      }
    },
    created(){
      this.getCarGoodsList();
    },
    methods:{


        //发出请求：
        getCarGoodsList() {
            this.$http.get('/getcarlist?userId=1').then(res => {
                this.car_data = res.data;
                console.log(res.data)
            }).catch(err => {
                console.log(err);
            })
        },
        //数量++
        handleCountAdd(){
            console.log(this.goods_num);
            if(this.goods_num <5) this.goods_num++;
            else {this.goods_num =5; alert("限购5件");}
        },
        //数量--
        handleCountLess(){
            if(this.goods_num >0) this.goods_num--;
            else this.goods_num = 0;
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
        width:80%;
        margin: 0 auto;
        border: none;
    }
    tbody{
        width: 100% ;
    }
    thead tr:nth-child(1){
        background:linear-gradient(to right, #fe7e61, #f15e85);
        height: 50px;
    }
    tr{
        height: 100px;
        border-bottom:1px solid #dddddd;
        border-left:1px solid #dddddd;
        border-right:1px solid #dddddd;

    }
    th{
        text-align: center !important;
        height: 40px;
        font-size:17px;
        line-height: 40px;
    }
    tbody thead tr th,tbody tr td{
        text-align: center;
        position: relative;
        width: 12%;
    }
    tbody thead tr th:nth-child(1),tbody tr td:nth-child(1){
        width: 35%;
    }
    .select img{
        width: 80px;
        height: 80px;
        display: inline-block;
        position: absolute;
        right: 300px;
        top:10px;
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
    }

    /*结算*/
    .check_all{
        position: absolute;
        top: 10px;
    }
    .account{
        position: sticky; bottom: 10px;
        width: 80%;
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
</style>