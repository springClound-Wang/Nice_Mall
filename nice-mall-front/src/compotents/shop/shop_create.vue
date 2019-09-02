<template>
    <div id="shop_container">
        <h4>我要开店</h4>
        <p>遵循以下流程，了解开店</p>
        <hr>
        <div class="knows">
            <div class="shop-one" :class="{bgChange:show1}">
                <h1>1</h1>
                <div><span>选择开店类型</span><span>个人店铺, 企业店铺</span></div>
            </div>
            <div class="shop-two" :class="{bgChange:show2}">
                <h1>2</h1>
                <div><span>阅读开店须知</span><span>确认自己符合个人店铺的相关规定</span></div>
            </div>
            <div class="shop-three" :class="{bgChange:show3}">
                <h1>3</h1>
                <div><span>填写店铺信息</span><span>需填写相关资料,快速开店</span></div>
            </div>
        </div>
        <mt-tab-container v-model="active" class="choose_shop_type">
            <mt-tab-container-item id="one">
            <div class="one_shop">
                <div><span class="iconfont icon-user"></span></div>
                <h4>个人店铺</h4>
                <p>通过支付宝个人实名认证的商家创建的店铺, 就是个人店铺。</p>
                <button @click="createShop">创建个人店铺</button>
            </div>

            <div class="one_shop">
                <div><span class="iconfont icon-qiye"></span></div>
                <h3>企业店铺</h3>
                <p>通过企业实名认证的商家创建的店铺, 就是个人店铺。</p>
                <button @click="createShop" disabled title="暂未开通">创建企业店铺</button>
            </div>
            </mt-tab-container-item>
            <mt-tab-container-item id="two">
                <section>
                    <p>1、我在淘宝已经开了个店了，我用别人的身份证再开一个店可以吗？</p>
                    <p>答：不可以。未经淘宝平台同意，将本人淘宝账号提供给他人做开店使用，由此导致相关争议，诉讼及因店铺经营中的违法违规行为导致一切人身，财产权益损害，均由本人自行承担全部民事、行政及刑事责任。</p>
                </section>
                <section>
                    <p>2、开企业店铺需要满足什么条件？</p>
                    <p>答：企业开店需完成支付宝认证和责任人认证，店铺负责人需要对该淘宝店铺的运营及管理全面负责，包含但不限于：该企业的法定代表人、股东、淘宝店铺的运营人等，具体请参考：企业店铺开店流程。</p>
                </section>
                <section>
                    <p>3、我可以开多个店铺吗？</p>
                    <p>答：同一会员已开设的店铺均须同时满足以下要求，才能获得多店权益：</p>
                    <p>（一）近365天无出售假冒商品违规、严重违规、一般违规扣分达12分（含）以上等违规记录，且无其他风险特征；</p>
                    <p>（二）满足一定经营条件（如近365天确认收货金额>=120万且近12个自然月持续有成交）</p>
                </section>
                <section>
                    <p>4、我已经开过淘宝店，现在想要注销原来的店铺重新开店，可以吗?</p>
                    <p>答：满足条件就可以注销。具体请参考：如何注销淘宝店铺。</p>
                </section>
                <div class="shop_btn">
                    <button @click="backCreate" class="btn">上一步</button>
                    <button @click="createKnows" class="btn" style="background-color:#6ba2cb ;color: white">我已了解，继续开店</button>
                </div>
            </mt-tab-container-item>
            <mt-tab-container-item id="three">
                <form>
                    <fieldset>
                    <label>
                        <span><i>*</i>店铺名字：</span>
                        <div class="toast_message" v-show="!storeName">
                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                            <span v-text="toastMsg"></span>
                        </div>
                        <input type="text" size="20" value="" placeholder="请填写店铺名字" name="storeName" v-model="storeName"/>
                    </label>
                    <label>
                        <span><i>*</i>经营人姓名：</span>
                        <div class="toast_message" v-show="!businessTrueName">
                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                            <span v-text="toastMsg"></span></div>
                        <input type="text" size="20" value="" placeholder="请填写真实姓名" name="businessTrueName" v-model="businessTrueName"/>
                    </label>
                    <label>
                        <span><i>*</i>身份证号码：</span>
                        <div class="toast_message" v-show="!businessIdentityNumber">
                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                            <span v-text="toastMsg"></span></div>
                        <input type="text" size="20" value="" placeholder="请填写身份证号码" name="businessIdentityNumber" v-model="businessIdentityNumber"/>
                    </label>
                    <label>
                        <span><i>*</i>联系电话:</span>
                        <div class="toast_message" v-show="!businessPhone">
                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                            <span v-text="toastMsg"></span>
                        </div>
                        <input type="text" size="20" value="" placeholder="请填写联系电话" name="businessPhone" v-model="businessPhone"/>
                    </label>
                    <label>
                        <span><i>*</i>店铺发货地址:</span>
                        <div class="toast_message" v-show="!businessAddress">
                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                            <span v-text="toastMsg"></span>
                        </div>
                        <input type="text" size="20" value="" placeholder="请填写店铺发货地址" name="businessAddress" v-model="businessAddress"/>
                    </label>
                    <div style="margin: 30px 0">
                        <input type="button" class="sum_btn" value="上一步" @click="createShop"/>
                        <input type="button" class="sum_btn sub" value="提交信息" @click="createInfoPush" />
                    </div>
                    </fieldset>
                </form>
            </mt-tab-container-item>
        </mt-tab-container>
    </div>
</template>
<script>
export default {
    data(){
        return{
            show1:true,
            show2:false,
            show3:false,
            icon_show:false, //警告框
            active:'one',
            toastMsg:'',
            storeName:'',//店铺名字
            businessTrueName:'',//经营人名
            businessIdentityNumber:'',//身份证号
            businessPhone:'',//联系电话
            businessAddress:''//地址
        }
    },
    methods:{
        createInfoPush(){
            if(!this.storeName || !this.businessTrueName || ! this.businessIdentityNumber || !this.businessPhone ||!this.businessAddress){
                this.toastMsg = '请输入内容';
                this.icon_show = true;
                return;
            }
            else {
                this.toastMsg = '';
                this.icon_show = false;
                this.$message.success('信息已提交');
                this.$http.post('http://120.78.64.17:8086/nice-mall-backend/business/createshop',
                    {
                        userId:window.localStorage.getItem('userId'),
                        storeName:this.storeName,
                        businessTrueName:this.businessTrueName,
                        businessIdentityNumber:this.businessIdentityNumber,
                        businessPhone:this.businessPhone,
                        businessAddress:this.businessAddress
                     },
                    {
                        headers: {'Authorization':window.localStorage.getItem('token') }
                    }
                ).then(res=>{
                    if(res.data.status === true){
                        this.$message.success(res.data.message);
                        window.localStorage.getItem['isshop'] = true;
                        this.$router.push('/shop_home/shop_curd');
                    }
                    else {
                        console.log(res.data.data.storeNameError);
                        if(res.data.data.storeNameError)
                            this.$message({
                                showClose: true,
                                message: res.data.data.storeNameError,
                                type: 'warning',
                                offset:20
                            });
                        if(res.data.data.AddressError)
                            this.$message({
                                showClose: true,
                                message:res.data.data.AddressError,
                                type: 'warning',
                                offset:20
                            });
                        if(res.data.data.IdentityError)
                            this.$message({
                                showClose: true,
                                message:res.data.data.IdentityError,
                                type: 'warning',
                                offset:20
                            });
                        if(res.data.data.IdError)
                            this.$message({
                                showClose: true,
                                message:res.data.data.IdError,
                                type: 'warning',
                                offset:20
                            });
                    }
                }).catch(res=>{
                    console.log(res);
                });
            }

        },
        createShop(){
            this.toastMsg = '';
            this.icon_show = false;
            this.active='two';
            this.show1 = false;
            this.show2 = true;
        },
        backCreate(){
            this.toastMsg = '';
            this.icon_show = false;
            this.active = 'one';
            this.show1 = true;
            this.show3 = false;
        },
        createKnows(){
            this.toastMsg = '';
            this.icon_show = false;
            this.active = 'three';
            this.show3 = true;
            this.show2 = false;
        }
    }

}
</script>
<style scoped>
    #shop_container{
        width: 86%;
        border: 1px solid #cccccc;
        margin: 10px auto;
    }
    #shop_container h4{
        padding: 10px 20px 3px;
    }
    #shop_container p{
        padding: 3px 20px;
    }
    .knows{
        width: 82%;
        margin-left:14%;
    }
    .knows:after{
        content: '';
        display: block;
        clear: both;
    }
    .shop-one,.shop-two,.shop-three{
        float: left;
        position: relative;
        width: 25%;
        height: 70px;
        background-color: #a3a4a4;
        margin-right: 3.5%;
        color: white;
    }
    .shop-three{
        width: 28%;
    }
    .shop-one h1,.shop-two h1,.shop-three h1{
        display: inline-block;
        margin-right: 10px;
        margin-left: 22%;
    }
    .shop-one div,.shop-two div,.shop-three div{
        width: 60%;
        display: inline-block;
    }
    .shop-two:before,.shop-three:before{
        content: '';
        width: 0;
        height: 0;
        position: absolute;
        left: 0;
        top: 0;
        border-left: 36px solid white;
        border-top: 33px solid transparent;
        border-bottom: 38px solid transparent;
    }
    .shop-one:after,.shop-two:after{
        content: '';
        width: 0;
        height: 0;
        position: absolute;
        left: 100%;
        top: 0;
        border-left: 36px solid #a3a4a4;
        border-top: 33px solid transparent;
        border-bottom: 38px solid transparent;
    }
    .choose_shop_type{
        width: 70%;
        margin:20px 0  50px 14%;
        border: 1px solid #cccccc;

    }
    .one_shop{
        width: 43%;
        display: inline-block;
        margin: 6% 0 20px 4%;
        text-align: center;
        padding: 20px;
    }
    .one_shop div{
        width: 90px;
        height: 90px;
        display: inline-block;
        border-radius: 50%;
        background-color:#75B0DF ;
    }
    .one_shop div .iconfont{
        color: white;
        font-size: 45px;
        line-height: 90px;
    }
    .one_shop p{
        width: 60%;
        margin: 10px auto;
    }
    .one_shop button{
        margin-top: 20px;
        padding: 10px 45px;
        font-size: 16px;
        color: white;
        background-color:#75B0DF;
    }
    /*开店通知*/
    #shop_container section{
        padding: 20px;
    }
    #shop_container section p:nth-child(1){
        color: #5e5e5e;
    }
    .bgChange{
        background-color: #75B0DF;
    }
    .bgChange:after{
        border-left: 36px solid #75B0DF;
    }
    .shop_btn{
        width: 100%;
        margin: 20px 0;
        text-align: center;
    }
    .btn{
        display: inline-block;
        padding: 7px 18px;
        border: 1px solid #cccccc;
        margin-right: 20px;
        background-color: #e5e5e5;
    }
    /*提交表单*/
    .choose_shop_type form {
        width: 100%;
        color: #3b3b3b;
        text-align: center;
    }
    .choose_shop_type label{
        position: relative;
        display: block;
        width: 80%;
        margin: 8px auto;
        font-weight: 500 !important;
    }
    .choose_shop_type input[type='text']{
        width: 50%;
        height: 38px !important;
    }
    #shop_container label input{
        margin-left: -2px;
    }
    label span i{
        color: #fe6139;
        display: inline-block;
        font-size: 20px;
        margin-right: 5px;
    }
    .sum_btn{
        padding: 9px 5% !important;
        margin-top: 20px;
        margin-bottom: 20px;
        background-color: #e5e5e5;
    }
    .sub{
        background-color: rgb(107, 162, 203);
        color: #f8f7f5;
    }
    fieldset{
        border: none;
        border-radius: 2px;
        margin-bottom: 12px;
        margin-top: 20px;
        overflow: hidden;
        padding: 0 .625em;
    }

    .choose_shop_type form label span{
        cursor: pointer;
        display: inline-block;
        text-align: right;
        width: 150px;
        vertical-align: top;
    }
    .toast_message{
        display: inline-block;
        position: absolute;
        top: -18px;
        left: 35%;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_message span:nth-child(2){
        margin-left: -65px;
    }
    .icon-jinggao{
        font-size: 22px;
        display: inline-block;
        width: 0 !important;
        position: relative;
        top: -6px;
        padding: 0;
    }

</style>
