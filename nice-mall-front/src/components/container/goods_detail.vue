<!--商品详情-->
<template>
    <div id="detail-container">
        <div class="goods_details_img">
            <div id="container"  @mouseenter="viewImage">
                <div class="leftView">
                    <div class="mask"></div>
                    <img class="small" :src="this.imageMain">
                </div>
                <div class="rightView">
                    <img class="big" :src="this.imageMain">
                </div>
            </div>
            <div class="goods_details_img_list">
                <img  v-for="item in imageShow" :src="item" @click="changeImg"/>
            </div>
        </div>
        <div class="goods_details_desc">
            <div class="goods_details_desc_title">
                <span>{{goodsMain.goodsBrand}}</span>
                <span>{{goodsName}}</span>
                <span>{{goodsDesc}}</span>
            </div>
            <div class="goods_details_price">
                <div>
                    <span style="margin-left: 20px">疯抢价</span> <span style="font-size: 32px">￥{{goodsCurPrice}}</span>
                </div>
                <div>
                    <span style="margin:0  20px ">原价</span> <span style="font-size: 25px;text-decoration: line-through">￥{{goodsPrePrice}}</span>
                </div>
            </div>
            <div class="goods_details_size" v-if="this.size">
                <span>尺码</span>
                <button  v-for="item in size" class="btn btn-default" @click="chooseSize">{{item}}</button>
            </div>
            <div class="goods_details_size" v-if="this.color">
                <span>颜色</span>
                <button v-for="item in color" class="btn btn-default" @click="chooseSize">{{item}}</button>
            </div>
            <br>
            <div class="goods_details_size" v-if="this.electronic_format">
              <span>规格</span>
              <button v-for="item in electronic_format" class="btn btn-default" @click="chooseSize">{{item}}</button>
            </div>
            <br>
            <div class="goods_details_num">
               <span>数量</span>
                <span @click="handleCountAdd">+</span><span>{{goods_num}}</span><span @click="handleCountLess">-</span>
            </div>
            <div class="goods_details_buy">
                <button @click="handleAddCar">加入购物车</button>
                <button @click="handleBuy">立即购买</button>
            </div>
            <hr>
            <div class="goods_details_other">
                <span>客服 <span class="iconfont icon-xinxi"></span> 在线客服(09:00-18:00)</span><br/>
                <div style="margin-top: 10px">服务
                    <span class="iconfont icon-yduizhengquekongxin"></span><span>Nice自营</span>
                    <span class="iconfont icon-yduizhengquekongxin"></span><span>7天可退</span>
                    <span class="iconfont icon-yduizhengquekongxin"></span><span>退货返10元Nice币</span>
                    <span class="iconfont icon-yduizhengquekongxin"></span><span>7天可换</span>
                </div>

            </div>
        </div>
        <div class="goods_details">
            <div class="goods_details_title">
                <span class="iconfont icon-liebiaodaohang_dongman"></span><span>商品尺码  SIZE</span>
            </div>
            <div class="goods_details_size_table">
                <div style="font-size: 14px;padding-bottom: 10px;">
                    TIPS:<br>
                    1. 选购时，请综合参考尺码表中各项参数，有助于你选择到更合适的尺码。<br>
                    2.（只适用服装）平铺测量自己的合身服装尺寸作参考，对比选购更准确！<br>
                    3. 请放心挑选尺码，唯品7天退货无忧替你保驾护航。<br>
                </div>
                <hr>
                <span style="margin-bottom: 10px;display: block">尺码对照表:</span>
                <table border="1" class="first_table" v-show="this.goodsFlag === 'phone' || this.goodsFlag === 'package'">
                  <tr style="background-color: white">
                    <th>无尺码对照</th>
                  </tr>
                </table>
                <!--女装上衣-->
                <table border="1" class="first_table" v-show="this.goodsFlag === 'clothesUpWoman'">
                    <thead>
                    <tr>
                        <th>尺码</th><th>号型	</th><th>胸围(cm)</th><th>下摆围(cm)</th><th>衣长(cm)</th><th>袖长(cm)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>XS</td><td>155/76A</td><td>125</td><td>122</td><td>60</td><td>56.5</td>
                    </tr>
                    <tr>
                        <td>S</td><td>160/80A</td><td>129</td><td>126</td><td>61.5</td><td>56.7</td>
                    </tr>
                    <tr>
                        <td>M</td><td>165/84A</td><td>133</td><td>130</td><td>63</td><td>56.9</td>
                    </tr>
                    <tr>
                        <td>L</td><td>170/88A</td><td>137</td><td>134</td><td>64.5</td><td>60.1</td>
                    </tr>
                    <tr>
                        <td>XL</td><td>175/92A</td><td>141</td><td>138</td><td>66</td><td>60.3</td>
                    </tr>
                    <tr>
                        <td colspan="6"  style="text-align: left">温馨提示： 平铺测量尺寸，没有拉伸，不考虑把弹性范围内的数据，误差范围1-2cm，敬请谅解。
                            【因个人体型差异及穿衣习惯的不同，请参照尺码表和自身习惯选择，如有疑问请联系客服。】</td>
                    </tr>
                    </tbody>
                </table>
                <!--女装裤子-->
                <table border="1" class="first_table" v-show="this.goodsFlag === 'clothesDownWomen'">
                    <thead>
                    <tr>
                        <th>尺码</th><th>号型	</th><th>腰围(cm)</th><th>臀围(cm)</th><th>大腿围(cm)</th><th>裤长(cm)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>S</td><td>155/88B</td><td>76</td><td>78</td><td>42</td><td>88</td>
                    </tr>
                    <tr>
                        <td>M</td><td>160/92B</td><td>78</td><td>80</td><td>44</td><td>90</td>
                    </tr>
                    <tr>
                        <td>L</td><td>165/96B</td><td>80</td><td>82</td><td>46</td><td>92</td>
                    </tr>
                    <tr>
                        <td>XL</td><td>170/100B</td><td>82</td><td>84</td><td>48</td><td>94</td>
                    </tr>
                    <tr>
                        <td colspan="6"  style="text-align: left">温馨提示： 平铺测量尺寸，没有拉伸，不考虑把弹性范围内的数据，误差范围1-2cm，敬请谅解。
                            【因个人体型差异及穿衣习惯的不同，请参照尺码表和自身习惯选择，如有疑问请联系客服。】</td>
                    </tr>
                    </tbody>
                </table>
                <!--男装上衣-->
                <table border="1" class="first_table" v-show="this.goodsFlag === 'clothesUpMen'">
                    <thead>
                    <tr>
                        <th>尺码</th><th>号型	</th><th>胸围(cm)</th><th>衣长(cm)</th><th>袖长(cm)</th><th>胸围(cm)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>M</td><td>170/88A</td><td>96</td><td>61.5</td><td>52.5</td><td>94</td>
                    </tr>
                    <tr>
                        <td>L</td><td>175/92A</td><td>100</td><td>63.5</td><td>54</td><td>98</td>
                    </tr>
                    <tr>
                        <td>XL</td><td>180/96A</td><td>104</td><td>65.5</td><td>55.5</td><td>102</td>
                    </tr>
                    <tr>
                        <td>XXL</td><td>185/100A</td><td>108</td><td>67.5</td><td>57</td><td>106</td>
                    </tr>
                    <tr>
                        <td>XXXL</td><td>190/104A</td><td>112</td><td>69.5</td><td>58.5</td><td>110</td>
                    </tr>
                    <tr>
                        <td colspan="6"  style="text-align: left">温馨提示： 平铺测量尺寸，没有拉伸，不考虑把弹性范围内的数据，误差范围1-2cm，敬请谅解。
                            【因个人体型差异及穿衣习惯的不同，请参照尺码表和自身习惯选择，如有疑问请联系客服。】</td>
                    </tr>
                    </tbody>
                </table>
                <!--鞋子-->
                <table border="1" class="first_table" v-show="this.goodsFlag === 'shoes'">
                    <thead>
                    <tr>
                        <th>尺码</th><th>鞋码	</th><th>欧洲码</th><th>美国码</th><th>脚长(mm)</th><th>中国码</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>35</td><td>35</td><td>35</td><td>5</td><td>22.5</td><td>35</td>
                    </tr>
                    <tr>
                        <td>36</td><td>36</td><td>36</td><td>6</td><td>23</td><td>36</td>
                    </tr>
                    <tr>
                        <td>37</td><td>37</td><td>37</td><td>7</td><td>23.5</td><td>37</td>
                    </tr>
                    <tr>
                        <td>38</td><td>38</td><td>38</td><td>8</td><td>24</td><td>38</td>
                    </tr>
                    <tr>
                        <td>39</td><td>39</td><td>39</td><td>9</td><td>25</td><td>39</td>
                    </tr>
                    <tr>
                        <td>40</td><td>40</td><td>40</td><td>10</td><td>25.5</td><td>40</td>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align: left">温馨提示： 平铺测量尺寸，没有拉伸，不考虑把弹性范围内的数据，误差范围1-2cm，敬请谅解。
                            【因个人体型差异及穿衣习惯的不同，请参照尺码表和自身习惯选择，如有疑问请联系客服。】</td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="goods_details_title">
                <span class="iconfont icon-liebiaodaohang_dongman"></span><span>商品参数 DETAIL</span>
            </div>
            <!--衣服-->
            <table border="1" class="second_table" v-show="this.goodsType === 'clothes'">
                <tr>
                    <th>适用季节:</th><td>{{goodsDetail.clothesSeason}}</td><th>款式：</th><td>正常</td>
                </tr>
                <tr>
                    <th>适用人群：</th><td>{{goodsDetail.clothesPerson}}</td><th>衣长：</th><td>常规</td>
                </tr>
                <tr>
                    <th>版型：</th><td>宽松</td><th>面料：</th><td>棉</td>
                </tr>
                <tr>
                    <th>商品名称：</th><td>{{goodsName}}</td><th>产地：</th><td>{{goodsPlace}}</td>
                </tr>
            </table >
            <!--鞋子-->
            <table border="1" class="second_table" v-show="this.goodsType === 'shoes'">
                <tr>
                    <th>适用季节:</th><td>{{goodsDetail.shoesSeason}}</td><th>品牌：</th><td>{{goodsMain.goodsBrand}}</td>
                </tr>
                <tr>
                    <th>适用场合：</th><td>{{goodsDetail.shoesPlace}}</td><th>产地：</th><td>{{goodsPlace}}</td>
                </tr>
                <tr>
                   <th>材质：</th><td>{{goodsDetail.shoesMaterial}}</td><th>商品名称：</th><td>{{goodsName}}</td>
                </tr>
            </table>
            <!--包包-->
            <table border="1" class="second_table" v-show="this.goodsType=== 'package'">
                <tr>
                    <th>适用季节:</th><td>春</td><th>品牌：</th><td>无</td>
                </tr>
                <tr>
                    <th>适用性别：</th><td>{{goodsDetail.packageSex}}</td><th>产地：</th><td>{{goodsPlace}}</td>
                </tr>
                <tr>
                    <th>材质：</th><td>{{goodsDetail.packageMaterial}}</td> <th>重量：</th><td>{{goodsDetail.packageWeight}}</td>
                </tr>
                <tr>
                    <th>商品编号：</th><td>121232300{{goodsMain.goodsId}}</td><th>商品名称：</th><td>{{goodsName}}</td>
                </tr>
            </table>
            <!--电子产品-->
            <table border="1" class="second_table" v-show="this.goodsType === 'electronic'">
                <tr>
                    <th>规格:</th><td>{{goodsMain.electronicFormat}}</td><th>品牌：</th><td>{{goodsMain.goodsBrand}}</td>
                </tr>
                <tr>
                    <th>系统：</th><td>{{goodsMain.electronicSystem}}</td><th>产地：</th><td>{{goodsPlace}}</td>
                </tr>
                <tr>
                    <th>是否支持双卡：</th><td>{{goodsMain.electronicDoubleCard}}</td> <th>分辨率：</th><td>{{goodsMain.electronicResolution}}</td>
                </tr>
                <tr>
                    <th>前置像素：</th><td>{{goodsMain.electronicFront}}</td> <th>后置像素：</th><td>{{goodsMain.electronicBackend}}</td>
                </tr>
                <tr>
                    <th>屏幕尺寸：</th><td>{{goodsMain.electronicScreenSize}}</td> <th>配件：</th><td>{{goodsMain.phoneFitting}}</td>
                </tr>
                <tr>
                    <th>商品编号：</th><td>100332322{{goodsMain.goodsId}}</td><th>商品名称：</th><td>{{goodsName}}</td>
                </tr>
            </table>
            <div class="goods_details_title">
                <span class="iconfont icon-liebiaodaohang_dongman"></span><span>商品展示  IMAGE</span>
            </div>
            <div class="goods_details_show_img">
                <img  v-for="item in imageDetail" :src="item"/>
            </div>
            <div class="goods_details_title">
                <span class="iconfont icon-liebiaodaohang_dongman"></span><span>售后说明  INSTRUCTIONS</span>
            </div>
            <div class="goods_details_instruct">
                <div style="font-size: 15px;margin: 20px 0">防盗扣请勿剪断，凭此退货</div>
                <div style="background-color: #eae8eb">售后说明</div>
                <div>
                    <div><span class="iconfont icon-yduizhengquekongxin"></span><span>Nice自营</span></div>
                    <div class="icon_desc">本商品由Nice安排发货，并提供售后服务。</div>
                </div>
                <div>
                    <div><span class="iconfont icon-yduizhengquekongxin"></span><span>7天可退</span></div>
                    <div class="icon_desc">高价值商品退货时需确保防盗扣/防伪标签等相关配件保持完整，否则不予退货</div>
                </div>
                <div>
                    <div><span class="iconfont icon-yduizhengquekongxin"></span><span>退货返10元Nice币</span></div>
                    <div class="icon_desc">任何原因产生的订单退货邮费，Nice都将补贴10元Nice（1000个）。</div>
                </div>
                <div>
                    <div><span class="iconfont icon-yduizhengquekongxin"></span><span>7天可换</span></div>
                    <div class="icon_desc">该商品支持换货，订单签收7天内可申请换货 </div>
                </div>
                <div style="background-color: #eae8eb">全部口碑</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name:'goods_detail.vue',
    data(){
        return{
            electronic_format:'',

            imageMain:'',
            goodsName:'',
            goodsDesc:'',
            goodsPlace:'',
            goodsCurPrice:'',
            goodsPrePrice:'',
            goods_num:1,
            goodsDetail:null,
            goodsMain:null,
            goods_size:'',
            goodsFlag:'clothesUpWoman',
            goodsType:'clothes',
            color :[],
            size:[],
            imageDetail:[],
            imageShow:[],
            storeGoods:'',

        }
    },
    created(){
        this.getGoodsDetails();
    },
    methods:{
        // TODO 请求当前商品的详情数据
        getGoodsDetails(){
          this.$http.get('http://120.78.64.17:8086/nice-mall-backend/home/showone/'+this.$route.query.id,{
                params: {},
                headers: {Authorization: window.localStorage.getItem('token')}
            }).then(res=>{
              this.imageMain = res.data.data.goodsMain.imageMain;
              this.goodsName = res.data.data.goodsMain.goodsName;
              this.goodsDesc = res.data.data.goodsMain.goodsDesc;
              this.goodsPlace = res.data.data.goodsMain.goodsPlace;
              this.goodsCurPrice = res.data.data.goodsMain.goodsCurPrice;
              this.goodsPrePrice = res.data.data.goodsMain.goodsPrePrice;
              this.goodsFlag = res.data.data.goodsFlag;
              this.goodsType = res.data.data.goodsType;
              this.goodsDetail= res.data.data.goodsDetail;
              this.goodsMain = res.data.data.goodsMain;
              this.color = res.data.data.color;
              this.size = res.data.data.size;
              this.imageDetail = res.data.data.imageDetail;
              this.imageShow = res.data.data.imageShow;
              this.storeGoods = res.data.data.storeGoods
          }).catch(err=>{
              // this.$router.push('/not_found');
          })
        },
        // TODO  加入购物车
        handleAddCar(){
            if(!this.goods_size){
                alert('请先选择商品尺码！');
            }
            else{
                this.$http.post('/addcar',{
                    userId:1,
                    goodsId:this.$route.query.id,
                    goodsNum:this.goods_num,
                    goodsSize:this.goods_size
                }).then(res=>{
                    console.log(res)
                }).catch(err=>{
                    console.log(err)
                })
            }
        },

        //ToDO 立即购买
        handleBuy(){
          let data = [{
            user_id:1,
            car_goods_id:this.$route.query.id,
            car_goods_num:this.goods_num,
            car_goods_size:this.goods_size,
            car_goods_img :this.imageMain,
            car_goods_name:this.goodsName,
            car_goods_price:this.goodsCurPrice
          }];
          this.$router.push({path:'/other_container/goods_order',query:{select_data:JSON.stringify(data)}});
        },
        //选择规格尺寸
        chooseSize(e){
            this.goods_size =e.target.innerText;
            let all = e.target.parentElement.childNodes;
            all.forEach(function (item) {
                if(item.nodeName === 'BUTTON') {
                    item.style.backgroundColor = 'white';
                    item.style.color = '';``
                }
            });
            e.target.style.backgroundColor = ' #fa5f62';
            e.target.style.color = 'white'
        },
        //数量++
        handleCountAdd(){
            if(this.goods_num <5) this.goods_num++;
            else {this.goods_num =5; alert("限购5件");}
        },
        //数量--
        handleCountLess(){
            if(this.goods_num >0) this.goods_num--;
            else this.goods_num = 0;
        },
        //点击切换图片
        changeImg(e){
            this.imageMain = e.target.src;
        },
        //鼠标放大 查看图
        viewImage(){
            function $(el) {
                return document.querySelector(el)
            }
            // 获取元素
            let container = $('#container');
            let leftView = $('.leftView');
            let mask = $('.mask');
            let rightView = $('.rightView');
            let bigImg = $('.big');
            // 显示放大遮罩与放大的图片容器
            leftView.addEventListener('mouseover', function() {
                mask.style.display = 'block';
                rightView.style.display = 'block'
            }, false);
            // 隐藏放大遮罩与放大的图片容器
            leftView.addEventListener('mouseout', function() {
                mask.style.display = 'none';
                rightView.style.display = 'none'
            });
            // 放大遮罩移动放大图片
            leftView.addEventListener('mousemove', function(evt){
                evt = evt || window.event;
                // 获取当前鼠标的位置
                let currentMouseX = evt.pageX;
                let currentMouseY = evt.pageY;
                // 获取要放大图片左侧与顶部的偏移距离
                let offsetLeft = container.offsetLeft;
                let offsetTop = container.offsetTop;
                // 获取放大遮罩的大小
                let maskWidth = mask.offsetWidth;
                let maskHeight = mask.offsetHeight;
                // 计算鼠标移动后放大遮罩的位置 位置 = 当前鼠标的位置 - 偏移距离 - 放大遮罩大小/2
                let zoomMaskX = currentMouseX - offsetLeft - maskWidth / 2 -100;
                let zoomMaskY = currentMouseY - offsetTop - maskHeight / 2 -120;
                // 限制鼠标上侧与左侧的范围
                if (zoomMaskX <= 0) {
                    zoomMaskX = 0
                }
                if (zoomMaskY <= 0) {
                    zoomMaskY = 0
                }
                // 限制鼠标右侧与下侧的范围
                let maxScopeX = leftView.offsetWidth - maskWidth;
                if (zoomMaskX >= maxScopeX) {
                    zoomMaskX = maxScopeX
                }
                let maxkScopeY = leftView.offsetHeight - maskHeight;
                if (zoomMaskY >= maxkScopeY) {
                    zoomMaskY = maxkScopeY
                }
                mask.style.left = zoomMaskX + 'px';
                mask.style.top = zoomMaskY + 'px';
                // 计算图片放大比例 比例= (大图长度 - 大图父容器长度) / (小图父容器长度 - 放大遮罩长度)
                let zommProportion = (bigImg.offsetWidth - rightView.offsetWidth) / (leftView.offsetWidth - maskWidth)
                bigImg.style.left = -zommProportion * zoomMaskX + 'px';
                bigImg.style.top = -zommProportion * zoomMaskY + 'px'
            }, false)

            }

        }
}
</script>
<style scoped>
    table{
      border-collapse:collapse;
    }
    #detail-container{
        width: 80%;
        margin: 0 auto;
        border: 1px solid #dedede;
        position: relative;
    }
    /*商品详情*/
    .goods_details{
        width: 95%;
        margin: 30px auto;
    }
    .goods_details_title{
        border-bottom: 1px solid #cccccc;
        margin: 20px 0;
        padding-bottom: 10px;
        background: url(//shop.vipstatic.com/img/detail/detial_line-hash-73c7b229.png?a6c60ccd) repeat-x left bottom;
    }
    .goods_details_title span{
        font-size: 20px;
        padding: 5px;
        display: inline-block;
        color: #fa5f62;
    }
    /*尺码对照表*/
    .goods_details_size_table{
        width: 100%;
        margin: 10px auto;
        font-size: 16px;
    }
    .goods_details_size_table button{
        outline: none;
    }
    .first_table,.second_table{
        width: 100%;
        font-size: 16px;
        color: #4e4e52;
        border: 1px solid #cccccc;
    }
    .first_table tr td,.second_table tr th{
        padding: 5px;
        font-size: 14px;
        text-align: center;
    }
    .first_table tr th{
        font-size: 15px;
        padding: 10px 0;
        text-align: center;
        background-color: #dddddd;
    }
    .second_table tr th{
        background-color:#f5f5f5;
        border: none;
        width: 8%;
        font-size: 14px;
        padding: 10px 5px;
        font-weight: 700;
    }
    .second_table tr td{
        color: #999;
        padding:10px  5px;
        border: 1px solid #e9e9e9;
        width: 30%;
        font-size: 14px;
    }
    /*商品大图*/
    .goods_details_show_img{
       width: 65%;
        margin: 0 auto;
    }
    /*售后*/
    .goods_details_instruct{
        width: 100%;
    }
    .goods_details_instruct .icon-yduizhengquekongxin{
        font-size: 23px;
        display: inline-block;
    }
    .goods_details_instruct div{
        width: 100%;
        padding: 6px;
        font-size: 15px;
    }
    .icon_desc{
        margin-left: 30px;
    }
    /*商品图展示块*/
    .goods_details_img{
        display: inline-block;
        margin: 20px;
    }
    .goods_details_img_list{
        width: 100%;
        margin-top: 20px;
    }
    .goods_details_img_list img:nth-child(1){
        margin-left: 0;
    }
    .goods_details_img_list img{
        width: 69px;
        margin-left: 11px;
        height: 68px;
        border: 1px solid #dedede;
    }
    .goods_details_desc{
        width: 55%;
        display: inline-block;
        position: absolute;
        top:30px;
        margin-left:20px;
        border-left:1px solid #dedede;
        padding-left: 20px;
    }
    /*商品名*/
    .goods_details_desc_title{
        width: 100%;
    }
    .goods_details_desc_title span{
        display: block;
        margin-bottom: 10px;
    }
    .goods_details_desc_title span:nth-child(1){
        font-size: 15px;
        color: #6d6d72;
    }
    .goods_details_desc_title span:nth-child(2){
        color: black !important;
        font-weight: bolder;
        font-size: 18px;
    }
    .goods_details_desc_title span:nth-child(3){
        font-size: 15px;
        color: #6d6d72;
    }
    /*价格条*/
    .goods_details_price div{
        width: 85%;
        height: 45px;
        color: white;
        line-height: 45px;
        font-size: 18px;
    }
    .goods_details_price div:nth-child(1){
        background:linear-gradient(to right, #f1366d, #fe5745);
    }
    .goods_details_price div:nth-child(2){
        font-size: 18px;
        color: #959595;
        background:linear-gradient(to right, #e2e2e2, #f4f0f2) ;
    }

    /*尺码*/
    .goods_details_size{
        width: 80%;
        margin: 20px 0;
        font-size: 17px;
    }
    .goods_details_size button{
        margin-left: 10px;
        padding: 4px 10px;
        border-radius: 0 !important;
    }
    /*数量*/
    .goods_details_num span:nth-child(1){
        border: none;
        display: inline-block;
        font-size: 17px;
        width: 45px;
        padding: 0;
    }
    .goods_details_num span{
        height: 28px;
        width: 23px;
        padding: 4px 10px;
        font-size: 16px;
        border: 1px solid #cccccc;
        border-radius: 0 !important;
    }

    /*加入购物车 立即购买*/
    .goods_details_buy{
        margin-top: 60px;
    }
    .goods_details_buy button{
      width: 150px;
      color: white;
      padding: 10px 0;
      background: -webkit-gradient(linear, left top, right top, from(#f1366d), to(#fe5745));
      background: linear-gradient(to right, #f1366d, #fe5745);
      margin-right: 10px;
      font-size: 16px;
      font-weight: bolder;
      margin-bottom: 10px;
      border: none;
    }

    /*底部其他信息  客服*/
    .goods_details_other{
        font-size: 15px;
        margin-top: 20px;
    }
    .icon-liebiaodaohang_dongman,.icon-yduizhengquekongxin,.icon-xinxi{
        color: #fa5f62;
        margin: 0 5px;
        font-weight: bolder;
    }
    .icon-liebiaodaohang_dongman{
        font-size: 25px !important;
    }
    /*商品图放大*/
    #container {
        position: relative;
    }
    #container .leftView {
        width: 400px;
        height: 400px;
    }
    #container .leftView .mask {
        width: 160px;
        height: 160px;
        position: absolute;
        display: none;
        cursor: move;
        top: 0;
        left: 0;
        background: rgba(221, 221, 221, 0.4);
    }
    #container .leftView .small {
        width: 100%;
        height: 100%;
    }
    #container .rightView {
        width: 400px;
        height: 400px;
        display: none;
        position: absolute;
        top: 0;
        left: 400px;
        z-index: 1000;
        overflow: hidden;
    }
    #container .rightView .big {
        position: absolute;
        top: 0;
        left: 0;
    }

</style>
