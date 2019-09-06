<template>
    <div id="curd_container">
        <!--侧导航-->
        <div class="write_process">
            <div class="write_icon">
                <div :class="{cur_icon:cur_show1,pre_icon:pre_show1}" v-text="one_icon"></div>
                <div :class="{cur_icon:cur_show1,pre_icon:pre_show1}">填写商品信息</div>
            </div>
            <div class="write_icon">
                <div :class="{cur_icon:cur_show2,pre_icon:pre_show2}" v-text="two_icon"></div>
                <div :class="{cur_icon:cur_show2,pre_icon:pre_show2}">填写商品属性</div>
            </div>
            <div class="write_icon">
                <div :class="{cur_icon:cur_show3,pre_icon:pre_show3}"  v-text="three_icon"></div>
                <div :class="{cur_icon:cur_show3,pre_icon:pre_show3}">提交商品信息</div>
            </div>
            <div class="write_border"></div>
        </div>
        <mt-tab-container v-model="active_public" class="tab">
            <mt-tab-container-item id="public">
               <form>
                   <fieldset>
                       <div class="clothes_info">
                           <div class="text_item">
                               <span><i>*</i>商品类型：</span>
                               <div class="block select">
                                   <el-cascader :options="options" clearable v-model="goodsType" @change="handleSelect"></el-cascader>
                               </div>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsName">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span><i>*</i>商品名：</span>
                               <input type="text" value="" placeholder="请输入商品名字" v-model="goodsName" aria-required="true"/>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsDesc">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span><i>*</i>商品描述：</span>
                               <textarea rows="3" cols="6" placeholder="请输入商品描述" v-model="goodsDesc"></textarea>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsBrand">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span><i>*</i>品牌：</span>
                               <input type="text" value="" placeholder="请输入商品品牌" v-model="goodsBrand"/>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsCurPrice">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span>销售价：</span>
                               <input type="number" value="" placeholder="请输入商品现价" v-model="goodsCurPrice"/>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsPrePrice">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span>市场价：</span>
                               <input type="number" value="0" placeholder="请输入商品原价" v-model="goodsPrePrice"/>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!goodsPlace">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span>商品产地：</span>
                               <input type="text" value="" placeholder="请输入商品产地" v-model="goodsPlace"/>
                           </div>
                           <div class="text_item">
                               <div class="toast_message" v-show="!storeGoodsNumber">
                                   <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                   <span v-text="toastMsg"></span>
                               </div>
                               <span>库存量：</span>
                               <input type="number" value="" placeholder="请输入库存量"  v-model="storeGoodsNumber" />
                           </div>
                           <div class="btn_box">
                                <button class="handlebtn" @click.self="nextTwo">下一步，填写商品属性</button>
                           </div>
                       </div>
                   </fieldset>
               </form>
            </mt-tab-container-item>
            <mt-tab-container-item id="sort">
                <form>
                    <fieldset>
                        <mt-tab-container v-model="active">
                            <mt-tab-container-item id="clothes">
                                <div class="clothes_info">
                                    <div class="text_item">
                                        <div class="toast_more_message" v-show="clothesSize.length <= 0" >
                                            <span class="iconfont icon-jinggao" v-if="icon_show" style="top: -5px;"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="toast_title">衣服尺码：</span>
                                        <el-checkbox-group v-model="clothesSize">
                                            <el-checkbox label="均码"></el-checkbox>
                                            <el-checkbox label="XS"></el-checkbox>
                                            <el-checkbox label="S"></el-checkbox>
                                            <el-checkbox label="M"></el-checkbox>
                                            <el-checkbox label="L"></el-checkbox>
                                            <el-checkbox label="XL"></el-checkbox>
                                            <el-checkbox label="XXL"></el-checkbox>
                                            <el-checkbox label="XXL"></el-checkbox>
                                        </el-checkbox-group>
                                    </div>
                                    <hr>
                                    <div class="text_item">
                                        <div class="toast_more_message" v-show="clothesColor.length <= 0">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="toast_title" >衣服颜色：</span>
                                        <el-checkbox-group v-model="clothesColor">
                                            <el-checkbox label="黑色"></el-checkbox>
                                            <el-checkbox label="白色"></el-checkbox>
                                            <el-checkbox label="卡其色"></el-checkbox>
                                            <el-checkbox label="红色"></el-checkbox>
                                            <el-checkbox label="粉色"></el-checkbox>
                                            <el-checkbox label="杏色"></el-checkbox>
                                            <el-checkbox label="黄色"></el-checkbox>
                                            <el-checkbox label="紫色"></el-checkbox>
                                            <el-checkbox label="蓝色"></el-checkbox>
                                        </el-checkbox-group>
                                    </div>
                                    <hr>
                                    <span>补充颜色：</span>
                                    <input type="text" value="" v-model="clothesColor" placeholder="补充输入颜色"/>
                                    <br><hr>
                                    <div class="toast_check_message" v-show="!clothesSeason">
                                        <span class="iconfont icon-jinggao" v-if="icon_show" style="top: -7px;"></span>
                                        <span v-text="toastMsg" style="margin-left: 7px;"></span>
                                    </div>
                                    <span>适用季节：</span>
                                    <div class="select">
                                        <el-select v-model="clothesSeason" placeholder="请选择" value="">
                                            <el-option
                                                    v-for="item in clothesSeasonOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                    <hr>
                                    <div class="toast_check_message" v-show="!clothesPerson">
                                        <span class="iconfont icon-jinggao" v-if="icon_show" style="top: -7px;"></span>
                                        <span v-text="toastMsg" style="margin-left: 7px;"></span>
                                    </div>
                                    <span>适用人群：</span>
                                    <div class="select">
                                        <el-select v-model="clothesPerson" placeholder="请选择" value="">
                                            <el-option
                                                    v-for="item in clothesPersonOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                </div><br><hr>
                            </mt-tab-container-item>
                            <mt-tab-container-item id="shoes">
                                <div class="shoes_info">
                                    <div class="text_item">
                                        <div class="toast_more_message" v-show="shoesSize.length <= 0">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="toast_title">鞋子尺码：</span>
                                        <el-checkbox-group v-model="shoesSize">
                                            <el-checkbox label="25"></el-checkbox>
                                            <el-checkbox label="26"></el-checkbox>
                                            <el-checkbox label="27"></el-checkbox>
                                            <el-checkbox label='28'></el-checkbox>
                                            <el-checkbox label="29"></el-checkbox>
                                            <el-checkbox label="30"></el-checkbox>
                                            <el-checkbox label="31"></el-checkbox>
                                            <el-checkbox label="32"></el-checkbox>
                                            <el-checkbox label="33"></el-checkbox>
                                            <el-checkbox label="34"></el-checkbox>
                                            <el-checkbox label="35"></el-checkbox>
                                            <el-checkbox label="36"></el-checkbox>
                                            <el-checkbox label="37"></el-checkbox>
                                            <el-checkbox label="38"></el-checkbox>
                                            <el-checkbox label="39"></el-checkbox>
                                            <el-checkbox label="40"></el-checkbox>
                                            <el-checkbox label="41"></el-checkbox>
                                            <el-checkbox label="42"></el-checkbox>
                                            <el-checkbox label="43"></el-checkbox>
                                            <el-checkbox label="44"></el-checkbox>
                                            <el-checkbox label="45"></el-checkbox>
                                            <el-checkbox label="46"></el-checkbox>
                                        </el-checkbox-group>
                                    </div>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_more_message" v-show="shoesColor.length <= 0">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="toast_title">鞋子颜色：</span>
                                        <el-checkbox-group v-model="shoesColor">
                                            <el-checkbox label="黑色"></el-checkbox>
                                            <el-checkbox label="白色"></el-checkbox>
                                            <el-checkbox label="卡其色"></el-checkbox>
                                            <el-checkbox label="棕色"></el-checkbox>
                                            <el-checkbox label="杏色"></el-checkbox>
                                            <el-checkbox label="黑白色"></el-checkbox>
                                        </el-checkbox-group>
                                    </div>
                                    <div v-show="!shoesColor">
                                        <span>补充颜色：</span>
                                        <input type="text" value="" v-model="shoesColor" placeholder="补充输入颜色"/>
                                    </div>
                                    <br><hr>

                                    <div class="toast_check_message" v-show="!shoesSeason">
                                        <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                        <span v-text="toastMsg"></span>
                                    </div>
                                    <span>适用季节：</span>
                                    <div class="select">
                                        <el-select v-model="shoesSeason" placeholder="请选择" value="">
                                            <el-option
                                                    v-for="item in shoesSeasonOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                    <br><hr>

                                    <div class="toast_check_message" v-show="!shoesPlace">
                                        <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                        <span v-text="toastMsg"></span>
                                    </div>
                                    <span>适用场合：</span>
                                    <div class="select">
                                        <el-select v-model="shoesPlace" placeholder="请选择" value="">
                                            <el-option
                                                    v-for="item in shoesPlaceOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                    <br><hr>

                                    <div class="toast_check_message" v-show="!shoesMaterial">
                                        <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                        <span v-text="toastMsg"></span>
                                    </div>
                                    <span>鞋子材质：</span>
                                    <div class="select">
                                        <el-select v-model="shoesMaterial" placeholder="请选择" value="">
                                            <el-option
                                                    v-for="item in shoesMaterialOptions"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value">
                                            </el-option>
                                        </el-select>
                                    </div>
                                    <br><hr>
                                </div>
                            </mt-tab-container-item>
                            <mt-tab-container-item id="package">
                                    <div class="shoes_info">
                                        <div class="text_item">
                                            <div class="toast_more_message" v-show="packageSize.length <= 0">
                                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                                <span v-text="toastMsg"></span>
                                            </div>
                                            <span class="toast_title">包包尺寸：</span>
                                            <el-checkbox-group v-model="packageSize">
                                                <el-checkbox label="均码"></el-checkbox>
                                                <el-checkbox label="大"></el-checkbox>
                                                <el-checkbox label="中"></el-checkbox>
                                                <el-checkbox label="小"></el-checkbox>
                                            </el-checkbox-group>
                                        </div>
                                        <br><hr>
                                        <div class="text_item">
                                            <div class="toast_more_message" v-show="packageColor.length <= 0">
                                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                                <span v-text="toastMsg"></span>
                                            </div>
                                            <span class="toast_title">包包颜色：</span>
                                            <el-checkbox-group v-model="packageColor">
                                                <el-checkbox label="黑色"></el-checkbox>
                                                <el-checkbox label="白色"></el-checkbox>
                                                <el-checkbox label="卡其色"></el-checkbox>
                                                <el-checkbox label="棕色"></el-checkbox>
                                                <el-checkbox label="卡其色"></el-checkbox>
                                                <el-checkbox label="杏色"></el-checkbox>
                                                <el-checkbox label="黄色"></el-checkbox>
                                                <el-checkbox label="绿色"></el-checkbox>
                                                <el-checkbox label="蓝色"></el-checkbox>
                                                <el-checkbox label="棕红色"></el-checkbox>
                                            </el-checkbox-group>
                                        </div>
                                        <br><hr>
                                        <span>补充颜色：</span>
                                        <input type="text" value="" v-model="packageColor" placeholder="补充输入颜色"/>
                                        <br><hr>
                                        <div class="toast_radio_message" v-show="!packageSash">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span >有无肩带：</span>
                                        <el-radio v-model="packageSash" label="有">有</el-radio>
                                        <el-radio v-model="packageSash" label="无">无</el-radio>
                                        <br><hr>
                                        <div class="toast_radio_message" v-show="!packageSex">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>适用性别：</span>
                                        <el-radio v-model="packageSex" label="男">男</el-radio>
                                        <el-radio v-model="packageSex" label="女">女</el-radio>
                                        <br><hr>
                                        <div class="text_item">
                                            <div class="toast_message input_message" v-show="!packageWeight" >
                                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                                <span v-text="toastMsg"></span>
                                            </div>
                                            <span>包包重量：</span>
                                            <input type="number" value=""  v-model="packageWeight"/>
                                        </div>
                                        <hr>
                                        <div class="toast_check_message" v-show="!packageSeason">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>适用季节：</span>
                                        <div class="select">
                                            <el-select v-model="packageSeason" placeholder="请选择" value="">
                                                <el-option
                                                        v-for="item in packageSeasonOptions"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </div>
                                        <br><hr>
                                        <div class="toast_check_message" v-show="!packageMaterial">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>包包材质：</span>
                                        <div class="select">
                                            <el-select v-model="packageMaterial" placeholder="请选择" value="">
                                                <el-option
                                                        v-for="item in packageMaterialOptions"
                                                        :key="item.value"
                                                        :label="item.label"
                                                        :value="item.value">
                                                </el-option>
                                            </el-select>
                                        </div>
                                       <br><hr>
                                </div>
                            </mt-tab-container-item>
                            <mt-tab-container-item id="electronic">
                                <div class="electronic_info">

                                    <div class="phone_color">
                                        <div class="text_item">
                                            <div class="toast_more_message" v-show="electronicColor.length <= 0">
                                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                                <span v-text="toastMsg"></span>
                                            </div>
                                            <span class="toast_title">手机颜色：</span>
                                            <el-checkbox-group v-model="electronicColor">
                                            <el-checkbox label="黑色"></el-checkbox>
                                            <el-checkbox label="白色"></el-checkbox>
                                            <el-checkbox label="银色"></el-checkbox>
                                            <el-checkbox label="香槟金"></el-checkbox>
                                            <el-checkbox label="深空灰"></el-checkbox>
                                            <el-checkbox label="红色"></el-checkbox>
                                            <el-checkbox label="玫瑰金"></el-checkbox>
                                            <el-checkbox label="渐变色"></el-checkbox>
                                        </el-checkbox-group>
                                        </div>
                                        <br><hr>
                                        <span>补充颜色：</span>
                                        <input type="text" value="" v-model="electronicColor" placeholder="补充输入颜色"/>
                                    </div>
                                    <hr>
                                    <div class="text_item">
                                        <div class="toast_more_message" v-show="electronicFormat.length <= 0">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="toast_title">手机规格：</span>
                                        <el-checkbox-group v-model="electronicFormat">
                                            <el-checkbox label="8GB+128GB"></el-checkbox>
                                            <el-checkbox label="8GB+256GB"></el-checkbox>
                                            <el-checkbox label="4GB+128GB"></el-checkbox>
                                            <el-checkbox label="8GB+64GB"></el-checkbox>
                                            <el-checkbox label="4GB+64GB"></el-checkbox>
                                            <el-checkbox label="4GB+32GB"></el-checkbox>
                                        </el-checkbox-group>
                                    </div>
                                    <br><hr>
                                    <div class="toast_radio_message" v-show="!electronicSystem" style="left: 40%">
                                        <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                        <span v-text="toastMsg"></span>
                                    </div>
                                    <span>手机系统：</span>
                                    <el-radio v-model="electronicSystem" label="苹果（IOS）">苹果（IOS）</el-radio>
                                    <el-radio v-model="electronicSystem" label="安卓（Android）">安卓（Android）</el-radio>
                                    <br><hr>

                                    <div class="toast_radio_message" v-show="!electronicDoubleCard" style="left: 30%">
                                        <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                        <span v-text="toastMsg"></span>
                                    </div>
                                    <span>支持双卡：</span>
                                    <el-radio v-model="electronicDoubleCard" label="支持">支持</el-radio>
                                    <el-radio v-model="electronicDoubleCard" label="不支持">不支持</el-radio>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_message input_message" v-show="!electronicResolution">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg" ></span>
                                        </div>
                                        <span>手机分辨率：</span>
                                        <input type="text" value="" v-model="electronicResolution" placeholder="请输入分辨率"/>
                                    </div>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_message input_message" v-show="!electronicFront" >
                                            <span class="iconfont icon-jinggao" v-if="icon_show" ></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>前置摄像头：</span>
                                        <input type="text" value="" v-model="electronicFront" placeholder="请输入前置摄像头像素"/>
                                    </div>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_message input_message" v-show="!electronicBackend">
                                            <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>后置摄像头：</span>
                                        <input type="text" value="" v-model="electronicBackend" placeholder="请输入后置摄像头像素"/>
                                    </div>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_message input_message" v-show="!electronicScreenSize">
                                            <span class="iconfont icon-jinggao" v-if="icon_show" ></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span>屏幕尺寸：</span>
                                        <input type="text" value="" v-model="electronicScreenSize" placeholder="请输入屏幕尺寸"/>
                                    </div>
                                    <br><hr>
                                    <div class="text_item">
                                        <div class="toast_message input_message" v-show="!phoneFitting">
                                            <span class="iconfont icon-jinggao" v-if="icon_show" ></span>
                                            <span v-text="toastMsg"></span>
                                        </div>
                                        <span class="phone_fitting" style="width: auto">手机配置：</span>
                                        <textarea rows="4" v-model="phoneFitting" placeholder="请输入手机配置"></textarea>
                                    </div>
                                    <br><hr>
                                </div>
                            </mt-tab-container-item>
                        </mt-tab-container>
                        <div>
                            <span class="show_img" style="margin-left: 0">主宣传图:</span>
                            <div class="toast_img_message" v-show="imageMain.length <= 0">
                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                <span v-text="toastMsg"></span>
                            </div>
                            <input type="file" accept="image/*" @change="changeMainImage" ref="avatarInputMain" style="display:none">
                            <div class="pic_list_box">
                                <div class="pic_list" v-show="imageMain.length<2" >
                                    <div v-for="(src,index) in imageMain" :key="index"
                                         @mouseenter="delShow" @mouseleave="delHide">
                                        <img :src="src" width="80" height="80" alt srcset>
                                        <span class="del_img" @click="deleteImg(index,imageMain)">×</span>
                                    </div>
                                    <div class="upload_img" v-show="imageMain.length < 1" >
                                        <span class="upload_btn" @click="upLoadMain" >+</span>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <span class="show_img">展示图:</span>
                            <div class="toast_img_message" v-show="imageShow.length <= 0">
                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                <span v-text="toastMsg"></span>
                            </div>
                            <input type="file" accept="image/*" @change="changeShowImage" ref="avatarInputShow" style="display:none">
                            <div class="pic_list_box">
                                <div class="pic_list" v-show="imageShow.length<6" >
                                    <div v-for="(item,index) in imageShow" :key="index"
                                         @mouseenter="delShow" @mouseleave="delHide">
                                        <img :src="item" width="80" height="80" alt srcset>
                                        <span class="del_img" @click="deleteImg(index,imageShow)">×</span>
                                    </div>
                                    <div class="upload_img" v-show="imageShow.length < 5" >
                                        <span class="upload_btn" @click="upLoadShow" >+</span>
                                    </div>
                                </div>

                            </div>
                            <hr>
                            <span class="show_img">详情图:</span>
                            <div class="toast_img_message" v-show="imageDetail.length <= 0">
                                <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                                <span v-text="toastMsg"></span>
                            </div>
                            <input type="file" accept="image/*" @change="changeDetailImage" ref="avatarInputDetail" style="display: none">
                            <div class="pic_list_box">
                                <div class="pic_list" v-show="imageDetail.length<6" >
                                    <div v-for="(item,index) in imageDetail" :key="index"
                                         @mouseenter="delShow" @mouseleave="delHide">
                                        <img :src="item" width="80" height="80" alt srcset>
                                        <span class="del_img" @click="deleteImg(index,imageDetail)">×</span>
                                    </div>
                                    <div class="upload_img" v-show="imageDetail.length < 5" >
                                        <span class="upload_btn" @click="upLoadDetail" >+</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="btn_box">
                            <button @click="nextOne" class="handlebtn">上一步，填写商品信息</button>
                            <button @click="submitInfo" class="handlebtn">提交商品信息</button>
                        </div>
                    </fieldset>
                </form>
            </mt-tab-container-item>

        </mt-tab-container>
    </div>
</template>
<script>
export default {
    data() {
        return {
            list:[],
            options:[
            {
                value: 'clothes',
                label: '衣服',
                children: [
                    {
                        value: '人气美衣',
                        label: '人气美衣',
                        children: [
                            {
                                value: 'T恤',
                                label: 'T恤'
                            }, {
                                value: '外套',
                                label: '外套'
                            }, {
                                value: '短外套',
                                label: '短外套'
                            }, {
                                value: '小西装',
                                label: '小西装'
                            }, {
                                value: '风衣',
                                label: '风衣'
                            }, {
                                value: '连衣裙',
                                label: '连衣裙'
                            }, {
                                value: '防晒衣',
                                label: '防晒衣'
                            }, {
                                value: '衬衫',
                                label: '衬衫'
                            }, {
                                value: '卫衣',
                                label: '卫衣'
                            }, {
                                value: '棉衣',
                                label: '棉衣'
                            },{
                                value: '阔腿裤',
                                label: '阔腿裤'
                            }, {
                                value: '雪纺衫',
                                label: '雪纺衫'
                            }, {
                                value: '大衣',
                                label: '大衣'
                            }, {
                                value: '针织衫',
                                label: '针织衫'
                            }]
                    }, {
                        value: '女下装',
                        label: '女下装',
                        children: [
                            {
                                value: '休闲裤',
                                label: '休闲裤'
                            }, {
                                value: '半身裙',
                                label: '半身裙'
                            }, {
                                value: '打底裤',
                                label: '打底裤'
                            }, {
                                value: '西装裤',
                                label: '西装裤'
                            }, {
                                value: '牛仔裙',
                                label: '牛仔裙'
                            }, {
                                value: '牛仔裤',
                                label: '牛仔裤'
                            }, {
                                value: '短裤',
                                label: '短裤'
                            }, {
                                value: '阔腿裙',
                                label: '阔腿裙'
                            }]
                    }, {
                        value: '男上装',
                        label: '男上装',
                        children: [
                            {
                                value: '卫衣',
                                label: '卫衣'
                            }, {
                                value: '大衣',
                                label: '大衣'
                            }, {
                                value: '棉衣',
                                label: '棉衣'
                            }, {
                                value: '毛衣',
                                label: '毛衣'
                            }, {
                                value: '短袖T恤',
                                label: '短袖T恤'
                            }]
                    }, {
                        value: '孕妈专区',
                        label: '孕妈专区',
                        children: [
                            {
                                value: '孕妇上装',
                                label: '孕妇上装'
                            }, {
                                value: '男童装',
                                label: '男童装'
                            }, {
                                value: '女童装',
                                label: '女童装'
                            }, {
                                value: '月子服',
                                label: '月子服'
                            }, {
                                value: '防辐射服',
                                label: '防辐射服'
                            }],
                    }],
            },
            {
                value: 'shoes',
                label: '鞋子',
                children: [{
                    value: '女单鞋',
                    label: '女单鞋',
                    children: [{
                        value: '单鞋',
                        label: '单鞋'
                    }, {
                        value: '厚底鞋',
                        label: '厚底鞋'
                    }, {
                        value: '高跟鞋',
                        label: '高跟鞋'
                    }, {
                        value: '厚底鞋',
                        label: '厚底鞋'
                    }, {
                        value: '平底鞋',
                        label: '平底鞋'
                    }, {
                        value: '浅口单鞋',
                        label: '浅口单鞋'
                    }, {
                        value: '深口单鞋',
                        label: '深口单鞋'
                    }, {
                        value: '高跟鞋',
                        label: '高跟鞋'
                    }]
                },{
                    value: '女休闲鞋',
                    label: '女休闲鞋',
                    children: [{
                        value: '单鞋',
                        label: '单鞋'
                    }, {
                        value: '内增高',
                        label: '内增高'
                    }, {
                        value: '小白鞋',
                        label: '小白鞋'
                    }, {
                        value: '帆布鞋',
                        label: '帆布鞋'
                    }, {
                        value: '板鞋',
                        label: '板鞋'
                    }, {
                        value: '运动鞋',
                        label: '运动鞋'
                    }]
                }, {
                    value: '男商务鞋',
                    label: '男商务鞋',
                    children: [{
                        value: '休闲皮鞋',
                        label: '休闲皮鞋'
                    }, {
                        value: '冲孔鞋',
                        label: '冲孔鞋'
                    }, {
                        value: '套脚商务鞋',
                        label: '套脚商务鞋'
                    }, {
                        value: '德比鞋',
                        label: '德比鞋'
                    }, {
                        value: '英伦鞋',
                        label: '英伦鞋'
                    }]
                }, {
                    value: '男休闲鞋',
                    label: '男休闲鞋',
                    children: [{
                        value: '一脚蹬',
                        label: '一脚蹬'
                    }, {
                        value: '休闲皮鞋',
                        label: '休闲皮鞋'
                    }, {
                        value: '小白鞋',
                        label: '小白鞋'
                    }, {
                        value: '帆布鞋',
                        label: '帆布鞋'
                    }, {
                        value: '豆豆鞋',
                        label: '豆豆鞋'
                    }]
                }, {
                    value: '女童鞋',
                    label: '女童鞋',
                    children: [{
                        value: '儿童休闲鞋',
                        label: '儿童休闲鞋'
                    },{
                        value: '儿童凉拖鞋',
                        label: '儿童凉拖鞋'
                    },{
                        value: '儿童小白鞋',
                        label: '儿童小白鞋'
                    }, {
                        value: '儿童跑鞋',
                        label: '儿童跑鞋'
                    }]
                }, {
                    value: '男童鞋',
                    label: '男童鞋',
                    children: [{
                        value: '儿童休闲鞋',
                        label: '儿童休闲鞋'
                    },{
                        value: '儿童凉拖鞋',
                        label: '儿童凉拖鞋'
                    },{
                        value: '儿童小白鞋',
                        label: '儿童小白鞋'
                    }, {
                        value: '儿童跑鞋',
                        label: '儿童跑鞋'
                    }]
                }]
            },
            {
                value: 'package',
                label: '包包',
                children: [{
                    value: '女包',
                    label: '女包',
                    children: [{
                        value: '实用大包',
                        label: '实用大包'
                    }, {
                        value: '双肩包',
                        label: '双肩包'
                    }, {
                        value: '真皮包',
                        label: '真皮包'
                    }, {
                        value: '手提包',
                        label: '手提包'
                    }, {
                        value: '单肩包',
                        label: '单肩包'
                    }, {
                        value: '手拿包',
                        label: '手拿包'
                    }, {
                        value: '小方包',
                        label: '小方包'
                    }, {
                        value: '轻便大包',
                        label: '轻便大包'
                    }]
                }, {
                    value: '男包',
                    label: '男包',
                    children: [{
                        value: '商务公文包',
                        label: '商务公文包'
                    }, {
                        value: '双肩包',
                        label: '双肩包'
                    }, {
                        value: '手拿包',
                        label: '手拿包'
                    }, {
                        value: '男式钱包',
                        label: '男式钱包'
                    }, {
                        value: '腰包',
                        label: '腰包'
                    }]
                }]
            },
            {
                value: 'electronic',
                label: '电子产品',
                children: [{
                    value: '热销手机',
                    label: '热销手机',
                    children: [ {
                        value: '苹果',
                        label: '苹果'
                    }, {
                        value: 'OPPO',
                        label: 'OPPO'
                    }, {
                        value: 'vivo',
                        label: 'vivo'
                    }, {
                        value: '小米',
                        label: '小米'
                    }, {
                        value: '华为荣耀',
                        label: '华为荣耀'
                    }, {
                        value: '联想',
                        label: '联想'
                    },{
                        value: '手机',
                        label: '手机'
                    }]
                }, {
                    value: '手机配件',
                    label: '热销手机',
                    children: [{
                        value: '原装配件',
                        label: '原装配件'
                    }, {
                        value: '手机保护套',
                        label: '手机保护套'
                    }, {
                        value: '手机存储卡',
                        label: '手机存储卡'
                    }, {
                        value: '手机耳机',
                        label: '手机耳机'
                    }, {
                        value: '拍照配件',
                        label: '拍照配件'
                    }, {
                        value: '移动电源',
                        label: '移动电源'
                    }]
                }]
            }
        ],
            selectedStyle: false,
            active_public: 'public', //公共
            active: 'clothes', //类型
            sel_active:'',//选择的类型
            toastMsg: '',//提示信息
            icon_show: false, //警告图标
            //小图标切换
            cur_show1: true,
            cur_show2: false,
            cur_show3: false,
            pre_show1: false,
            pre_show2: false,
            pre_show3: false,
            //小图标文字切换
            one_icon: '1',
            two_icon: '2',
            three_icon: '3',
            //公共
            goodsType: '',
            goodsName: '',
            goodsDesc: '',
            goodsBrand: '',
            goodsCurPrice: '',
            goodsPrePrice: '',
            goodsPlace: '',
            storeGoodsNumber: '',
            imageMain: [], //主图片
            imageShow: [],
            imageDetail: [],
            //衣服
            clothesSize: [],
            clothesSeason: '',
            clothesPerson: '',
            clothesColor: [],
            clothesSeasonOptions: [
                {
                    value: '春季',
                    label: '春季'
                }, {
                    value: '夏季',
                    label: '夏季'
                }, {
                    value: '秋季',
                    label: '秋季'
                }, {
                    value: '冬季',
                    label: '冬季'
                }, {
                    value: '初春/初秋',
                    label: '初春/初秋'
                }, {
                    value: '初夏',
                    label: '初夏'
                }, {
                    value: '初冬',
                    label: '初冬'
                }
            ],
            clothesPersonOptions: [
                {
                    value: '青少年',
                    label: '青少年'
                }, {
                    value: '中年人',
                    label: '中年人'
                }, {
                    value: '老人',
                    label: '老人'
                }, {
                    value: '儿童',
                    label: '儿童'
                }, {
                    value: '孕妇',
                    label: '孕妇'
                }, {
                    value: '大学生',
                    label: '大学生'
                }, {
                    value: '中学生',
                    label: '中学生'
                }, {
                    value: '年轻人',
                    label: '年轻人'
                }
            ],
            //鞋子
            shoesSize: [],
            shoesSeason: '',
            shoesPlace: '',
            shoesMaterial: '',
            shoesColor: [],
            shoesPlaceOptions: [
                {
                    value: '日常生活',
                    label: '日常生活'
                }, {
                    value: '运动',
                    label: '运动'
                }, {
                    value: '工作',
                    label: '工作'
                }
            ],
            shoesMaterialOptions: [
                {
                    value: '牛皮',
                    label: '牛皮'
                }, {
                    value: '磨砂皮',
                    label: '磨砂皮'
                }, {
                    value: '软面皮',
                    label: '软面皮'
                }, {
                    value: '橡胶底/羊皮里',
                    label: '橡胶底/羊皮里'
                }, {
                    value: '橡胶底/软面皮',
                    label: '橡胶底/软面皮'
                }, {
                    value: '羊皮革',
                    label: '羊皮革'
                }, {
                    value: '发泡底',
                    label: '发泡底'
                }, {
                    value: 'PU',
                    label: 'PU'
                }
            ],
            shoesSeasonOptions: [
                {
                    value: '春季',
                    label: '春季'
                }, {
                    value: '夏季',
                    label: '夏季'
                }, {
                    value: '秋季',
                    label: '秋季'
                }, {
                    value: '冬季',
                    label: '冬季'
                }, {
                    value: '初春/初秋',
                    label: '初春/初秋'
                }, {
                    value: '初夏',
                    label: '初夏'
                }, {
                    value: '初冬',
                    label: '初冬'
                }
            ],
            //包包
            packageSize: [],
            packageSash: '',
            packageSex: '',
            packageSeason: '',
            packageMaterial: '',
            packageColor: [],
            packageWeight: '',
            packageMaterialOptions: [
                {
                    value: '牛皮',
                    label: '牛皮'
                }, {
                    value: '牛皮/涤纶',
                    label: '牛皮/涤纶'
                }, {
                    value: 'PU/涤纶',
                    label: 'PU/涤纶'
                }, {
                    value: '牛皮/超纤织物',
                    label: '牛皮/超纤织物'
                }, {
                    value: '牛皮/聚氨脂',
                    label: '牛皮/聚氨脂'
                }, {
                    value: '尼龙/涤纶',
                    label: '尼龙/涤纶'
                }, {
                    value: 'PVC/涤纶',
                    label: 'PVC/涤纶'
                }
            ],
            packageSeasonOptions: [
                {
                    value: '春天',
                    label: '春天'
                }, {
                    value: '夏天',
                    label: '夏天'
                }, {
                    value: '秋天',
                    label: '秋天'
                }, {
                    value: '冬天',
                    label: '冬天'
                }, {
                    value: '春秋',
                    label: '春秋'
                }, {
                    value: '四季皆可',
                    label: '四季皆可'
                }
            ],
            //手机
            electronicFormat: [],
            electronicSystem: '',
            electronicDoubleCard: '',
            electronicColor: [],
            electronicResolution: '', //分辨率
            electronicFront: '',
            electronicBackend: '',
            electronicScreenSize: '',
            phoneFitting: ''
        }
    },
    created(){

    },
    methods: {

        handleSelect(e) {
            console.log(e[0]);
            this.sel_active = e[0];
        },
        nextOne() {
            this.active_public = 'public';
            this.pre_show1 = false;
            this.cur_show1 = true;
            this.pre_show2 = false;
            this.cur_show2 = false;
            this.pre_show3 = false;
            this.cur_show3 = false;
            this.one_icon = '1';
            this.two_icon = '2';
        },
        nextTwo() {
            if (!this.goodsType || !this.goodsDesc || !this.goodsName || !this.goodsPrePrice || !this.goodsPlace ||!this.goodsCurPrice) {
                this.toastMsg = '请输入内容';
                this.icon_show = true;
            }
            else {
                this.active_public = 'sort';
                this.active = this.sel_active;
                this.toastMsg = '';
                this.icon_show = false;
                this.pre_show1 = true;
                this.cur_show1 = false;
                this.one_icon = '✔';
                this.pre_show2 = false;
                this.cur_show2 = true;
            }
        },
        delShow(e) {
            e.target.lastChild.style.display = 'block';
        },
        delHide(e) {
            e.target.lastChild.style.display = 'none'
        },
        //提交添加商品信息  发送信息
        submitInfo() {
                    this.pre_show1 = true;
                    this.cur_show1 = false;
                    this.pre_show2 = true;
                    this.cur_show2 = false;
                    this.pre_show3 = false;
                    this.cur_show3 = true;
                    this.one_icon = '✔';
                    this.two_icon = '✔';
                    if(!this.imageDetail || !this.imageMain ||!this.imageShow){
                        this.toastMsg = '请选择内容';
                        this.icon_show = true;
                    }
                    if (this.active === 'clothes') {
                        if (!this.clothesColor || !this.clothesPerson || !this.clothesSeason || !this.clothesSize) {
                            this.toastMsg = '请选择内容';
                            this.icon_show = true;
                        }else {
                            this.$http.post('http://120.78.64.17:8086/nice-mall-backend/addgoods', {
                                userId:window.localStorage.getItem('userId'),
                                goodsType: this.goodsType,
                                goodsName: this.goodsName,
                                goodsDesc: this.goodsDesc,
                                goodsBrand: this.goodsBrand,
                                goodsCurPrice: this.goodsCurPrice,
                                goodsPrePrice: this.goodsPrePrice,
                                goodsPlace: this.goodsPlace,
                                storeGoodsNumber:this.storeGoodsNumber,
                                clothesColor: this.clothesColor.join(),
                                imageMain: this.imageMain,
                                imageShow: this.imageShow,
                                imageDetail: this.imageDetail,
                                clothesSize: this.clothesSize,
                                clothesSeason: this.clothesSeason,
                                clothesPerson: this.clothesPerson,
                            }, {
                                headers: {'Authorization': window.localStorage.getItem('token')}
                            }).then(res => {
                                alert(res.data.message);
                                this.$router.push('/shop_home/shop_curd');
                            }).catch(res => {
                                alert(res.data.message);
                            })
                        }

                    }
                    else if (this.active === 'shoes') {
                        if (!this.shoesColor || !this.shoesMaterial || !this.shoesPlace || !this.shoesSeason || !this.shoesSize) {
                            this.toastMsg = '请选择内容';
                            this.icon_show = true;
                        }else {
                            this.$http.post('http://120.78.64.17:8086/nice-mall-backend/addgoods', {
                                userId: window.localStorage.getItem('userId'),
                                goodsType: this.goodsType,
                                goodsName: this.goodsName,
                                goodsDesc: this.goodsDesc,
                                goodsBrand: this.goodsBrand,
                                goodsCurPrice: this.goodsCurPrice,
                                goodsPrePrice: this.goodsPrePrice,
                                goodsPlace: this.goodsPlace,
                                shoesColor: this.shoesColor.join(),
                                imageMain: this.imageMain,
                                imageShow: this.imageShow,
                                imageDetail: this.imageDetail,
                                storeGoodsNumber: this.storeGoodsNumber,
                                shoesSize: this.shoesSize,
                                shoesSeason: this.shoesSeason,
                                shoesPlace: this.shoesPlace,
                                shoesMaterial: this.shoesMaterial,
                            }, {
                                headers: {'Authorization': window.localStorage.getItem('token')}
                            }).then(res => {
                                alert(res.data.message);
                                this.$router.push('/shop_home/shop_curd');
                            }).catch(res => {
                                alert(res.data.message);
                            })
                        }
                    }
                    else if (this.active === 'package') {
                        if (!this.packageMaterial || !this.packageColor || !this.packageSash || !this.packageSeason || !this.packageSex) {
                            this.toastMsg = '请选择内容';
                            this.icon_show = true;
                        }else {
                            this.$http.post('http://120.78.64.17:8086/nice-mall-backend/addgoods', {
                                userId: window.localStorage.getItem('userId'),
                                goodsType: this.goodsType,
                                goodsName: this.goodsName,
                                goodsDesc: this.goodsDesc,
                                goodsBrand: this.goodsBrand,
                                goodsCurPrice: this.goodsCurPrice,
                                goodsPrePrice: this.goodsPrePrice,
                                goodsPlace: this.goodsPlace,
                                storeGoodsNumber:this.storeGoodsNumber,
                                imageMain: this.imageMain,
                                imageShow: this.imageShow,
                                imageDetail: this.imageDetail,
                                packageColor: this.packageColor.join(),
                                packageSize: this.packageSize,
                                packageSash: this.packageSash,
                                packageSex: this.packageSex,
                                packageSeason: this.packageSeason,
                                packageMaterial: this.packageMaterial,
                                packageWeight: this.packageWeight
                            }, {
                                headers: {'Authorization': window.localStorage.getItem('token')}
                            }).then(res => {
                                alert(res.data.message);
                                this.$router.push('/shop_home/shop_curd');
                            }).catch(res => {
                                alert(res.data.message);
                            })
                        }
                    }
                    else if (this.active === 'electronic') {
                        if (!this.electronicBackend || !this.electronicColor || !this.electronicDoubleCard || !this.electronicFormat || !this.electronicFront
                        || !this.electronicResolution || !this.electronicScreenSize || !this.electronicSystem) {
                            this.toastMsg = '请选择内容';
                            this.icon_show = true;
                        }else {
                            this.$http.post('http://120.78.64.17:8086/nice-mall-backend/addgoods', {
                                userId: window.localStorage.getItem('userId'),
                                goodsType: this.goodsType,
                                goodsName: this.goodsName,
                                goodsDesc: this.goodsDesc,
                                goodsBrand: this.goodsBrand,
                                goodsCurPrice: this.goodsCurPrice,
                                goodsPrePrice: this.goodsPrePrice,
                                goodsPlace: this.goodsPlace,
                                storeGoodsNumber:this.storeGoodsNumber,
                                imageMain: this.imageMain,
                                imageShow: this.imageShow,
                                imageDetail: this.imageDetail,
                                electronicColor: this.electronicColor.join(),
                                electronicFormat: this.electronicFormat,
                                electronicSystem: this.electronicSystem,
                                electronicDoubleCard: this.electronicDoubleCard,
                                electronicResolution: this.electronicResolution, //分辨率
                                electronicFront: this.electronicFront,
                                electronicBackend: this.electronicBackend,
                                electronicScreenSize: this.electronicScreenSize,
                                phoneFitting: this.phoneFitting

                            }, {
                                headers: {'Authorization': window.localStorage.getItem('token')}
                            }).then(res => {
                                alert(res.data.message);
                                this.$router.push('/shop_home/shop_curd');
                            }).catch(res => {
                                alert(res.data.message);
                            })
                        }
                    }
        },
        // 上传主图片事件
        changeMainImage() {
                    let files = this.$refs.avatarInputMain.files;
                    let that = this;

                    function readAndPreview(file) {
                        if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                            let reader = new FileReader();
                            reader.onload = function () {
                                if (that.imageMain.indexOf(this.result) === -1) {
                                    that.imageMain.push(this.result);
                                }
                            };
                            reader.readAsDataURL(file);
                        }
                    }

                    if (files) {
                        [].forEach.call(files, readAndPreview);
                    }
                    if (files.length === 0) {
                        return;
                    }
        },
        changeShowImage() {
                    let files = this.$refs.avatarInputShow.files;
                    let that = this;

                    function readAndPreview(file) {
                        if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                            let reader = new FileReader();
                            reader.onload = function () {
                                if (that.imageShow.indexOf(this.result) === -1) {
                                    that.imageShow.push(this.result);
                                }
                            };
                            reader.readAsDataURL(file);
                        }
                    }

                    if (files) {
                        [].forEach.call(files, readAndPreview);
                    }
                    if (files.length === 0) {
                        return;
                    }
        },
        changeDetailImage() {
                    let files = this.$refs.avatarInputDetail.files;
                    let that = this;

                    function readAndPreview(file) {
                        if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                            let reader = new FileReader();
                            reader.onload = function () {
                                if (that.imageDetail.indexOf(this.result) === -1) {
                                    that.imageDetail.push(this.result);
                                }
                            };
                            reader.readAsDataURL(file);
                        }
                    }
                    if (files) {
                        [].forEach.call(files, readAndPreview);
                    }
                    if (files.length === 0) {
                        return;
                    }
        },
        // 触发上传图片按钮
        upLoadMain() {
                    this.$refs.avatarInputMain.dispatchEvent(new MouseEvent("click"));
        },
        upLoadShow() {
                    this.$refs.avatarInputShow.dispatchEvent(new MouseEvent("click"));
        },
        upLoadDetail() {
                    this.$refs.avatarInputDetail.dispatchEvent(new MouseEvent("click"));
        },
        //删除图片
        deleteImg(index, objImgArray) {
                    objImgArray.splice(index, 1);
        }

        }
    }
</script>
<style scoped>
    #curd_container{
        width: 80%;
        margin: 0 auto;
    }

    #curd_container form{
        width:90%;
        position: relative;
        font-size: 14px;
        margin: 10px auto;
        color: #3b3b3b;
        padding: 5% 1% 5% 5%;
        border: 1px solid #cccccc;

    }
    #curd_container form textarea{
        font-size: 13px;
        margin-bottom: 13px;
    }
    #curd_container form fieldset{
        border: none;
        border-radius: 2px;
        margin-bottom: 12px;
        overflow: hidden;
        padding: 0 .625em;
    }

    #curd_container form >div{
        position: relative;
    }
    #curd_container form .clothes_info div span,
    .phone_fitting {
        cursor: pointer;
        display: inline-block;
        text-align: right;
        width: 130px;
        vertical-align: top;
    }

    .electronic_info textarea{
        margin-bottom: 10px;
    }

    #curd_container form label span{
        position: absolute;
        top: 12px;
        margin: 0 6px;
    }
    #curd_container form input{
        height: 35px !important;
        font-size: 13px;
    }
    #curd_container form input[type='text'],textarea{
        width: 70%;
    }
    #curd_container form input[type='number']{
        width: 70%;
    }
    #curd_container form span i{
        color: #fe6139;
        display: inline-block;
        font-size: 20px;
        margin-right: 5px;
    }
    #curd_container form input[type='checkbox']{
        width: 16px;
        height: 16px;
        margin-left: 5px;
    }
    .show_img{
        float: left;
        margin-left: 15px;
        margin-right: 1%;
    }
    .show_img:after{
        content: '';
        display: block;
        clear: both;
    }
    .clothes_info{
        width: 100%;
        position: relative;
        margin: 0 auto;
    }
    .clothes_info label{
        position: relative;
    }
    .btn_box{
        width: 100%;
        text-align: center;
        margin-top: 20px;
    }
    .btn_box button:nth-child(2){
        margin-left: 3%;
    }

    /*填写进度*/
    .write_process{
        width: 70%;
        margin: 10px auto 20px;
        position: relative;
    }
    .write_icon {
        width: 19%;
        float: left;
        text-align: center;
        margin-left: 10%;
    }
    .write_icon:after, .write_process:after {
        content: '';
        display: block;
        clear: both;
    }
    .write_icon div:nth-child(1){
        display: inline-block;
        width: 30px;
        height: 30px;
        text-align: center;
        line-height: 30px;
        border: 2px solid #a3a4a4;
        border-radius: 50%;
        font-size: 16px;
        z-index: 10;
        background-color: white;
        margin-top: 5px;
    }
    /*当前*/
    .cur_icon{
        border-color: #fe0e09 !important;
        color: red;
    }
    /*前一个*/
    .pre_icon{
        border-color: #51d551 !important;
        color: #2ac845;
    }
    .write_icon div:nth-child(2){
        margin-top: 20px;
    }
    .write_border{
        width: 59%;
        border-bottom: 3px solid #a3a4a4;
        position: absolute;
        top:20px;
        left: 20%;
        z-index: -1;
    }
    .handlebtn{
        margin-top: 20px;
        background-color: #6ba2cb;
        color: white;
    }
    /*上传图片*/
    .upload_img {
        width: 146px;
        height: 150px;
        font-size: 55px;
        border-radius: 10px;
        margin-left: 10%;
        margin-top: 15px;
        border: 1px dotted #6d6d72;
        display: inline-block;
        text-align: center;
        padding: 0;
        line-height: 150px;
        color: #a3a4a4;
        position: relative;
    }
    .pic_list_box {
        display: block;
        position: relative;
    }
    .upload_btn {
        width: 160px;
        height: 160px;
    }
    .pic_list{
        display: inline-block;
        position: relative;
        width: 85%;

    }
    .pic_list>div{
        margin-left: 10px;
        position: relative;
        float: left;
    }
    .pic_list img {
        width: 150px;
        height: 150px;
        position: relative;
        margin-top: 15px;
    }
    .del_img{
        position: absolute;
        font-size: 40px;
        right: 10px;
        display: none;
        opacity: 1;
        top: 7px;
        z-index: 1000;
        color: #fe0e09;
    }
    /*下拉框样式*/
    .select{
        width: 70%;
        margin-bottom: 10px;
        display: inline-block;
        position:relative;
        cursor:pointer;
    }
    /*这边就是三角形的css代码，使用伪类和定位实现右三角*/
    #curd_container input:focus{
        background-color: rgba(254, 125, 79, 0.2);
    }
    /*input 提示框*/
    .toast_message{
        display: block;
        width: auto;
        position: absolute;
        left: 15.5%;
        top: -22px;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_message span:nth-child(1){
        color: red;
        top: -6px !important;
    }
    .toast_message span:nth-child(2){
        margin-left: 7px;
        width: auto !important;
    }
    /*下拉选择 提示框*/
    .toast_check_message{
        display: block;
        width: auto;
        position: absolute;
        left: 35%;
        margin-top: 10px;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_check_message span:nth-child(1){
        color: red;
    }
    .toast_check_message span:nth-child(2){
        margin-left: 8px;
        width: auto !important;
    }

    /*复选框 提示*/
    .toast_more_message{
        display: block;
        width: auto;
        position: absolute;
        left: 8%;
        top:-3px;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_more_message span:nth-child(1){
        color: red;
        float: left;
        top:-4px !important;
    }
    .toast_more_message span:nth-child(2){
        margin-left: 8px;
        float: left;
        width: auto !important;
        padding: 3px 2px !important;
    }
    .text_item{
        position: relative;
        margin-bottom: 11px;
    }
    .icon-jinggao{
        font-size: 22px;
        display: inline-block;
        width: 0 !important;
        position: relative;
        top: 3px;
        padding: 0;
    }
    .toast_title{
        padding: 0 !important;
        width: auto !important;
    }
    /*单选 提示*/
    .toast_radio_message{
        display: block;
        width: auto;
        position: absolute;
        left: 23%;
        margin-top: -9px;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_radio_message span:nth-child(1){
        color: red;
    }
    .toast_radio_message span:nth-child(2){
        margin-left: 8px;
    }
    .input_message{
        right: 9%;
        top: 6px;
        left: auto;
    }
    .input_message span:nth-child(1){
        color: red;
        float: left;
        top: -6px !important;
    }
    .input_message span:nth-child(2){
        margin-left: 10px;
        width: auto !important;
    }

    /*图片 提示*/
    .toast_img_message{
        position: relative;
        display: block;
        width: auto;
        left: 20px;
        font-size: 13px;
        color: #e5384f;
    }
    .toast_img_message span:nth-child(1){
        color: red;
        float: left;
        top:-4px !important;
    }
    .toast_img_message span:nth-child(2){
        margin-left: 8px;
        float: left;
        width: auto !important;
        padding: 3px 2px !important;
    }
</style>