<template>
    <div id="hot_container">
        <el-card shadow="never">
            <div class="title">热门分类管理</div>
            <div class="content">
                <span>热门类型:</span> &nbsp;&nbsp;
                <div class="upload_type">
                    <el-select v-model="uploadType" placeholder="请选择" value="">
                        <el-option
                                v-for="item in uploadTypeList"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </div>
                <div class="toast_img_message" v-show="!uploadType">
                    <span class="iconfont icon-jinggao" v-if="icon_show"></span>
                    <span v-text="toastMsg"></span>
                </div>
                <div class="type_img">上传主类型的图片</div>
                <input type="file" accept="image/*" @change="changeMainImage" ref="avatarInputMain" style="display:none">
                <div class="pic_list_box">
                    <div class="pic_list" v-show="imageMains.length<2">
                        <div v-for="(src,index) in imageMains" :key="index"
                             @mouseenter="delShow" @mouseleave="delHide">
                            <img :src="src" width="80" height="80" alt srcset class="img_list">
                            <span class="del_img" @click="deleteMainImg(index,imageMains)">×</span>
                        </div>
                        <div class="upload_img" v-show="imageMains.length < 1">
                            <span class="upload_btn" @click="upLoadMains">+</span>
                        </div>
                    </div>
                </div>
                <div class="type_img">输入类型及其图片：<span style="font-size: 12px;color: red">(不少于十个)</span></div>
                <input type="file" accept="image/*" @change="changeDetailImage" ref="avatarInputDetail"
                       style="display: none">
                <div class="pic_list_box">
                    <div class="pic_list" v-show="imageDetails.length<11">
                        <div v-for="(item,index) in imageDetails" :key="index">
                            <div @mouseenter="delShow" @mouseleave="delHide">
                                <img :src="item" width="80" height="80" alt srcset class="img_list">
                                <span class="del_img" @click="deleteImg(index,imageDetails,ruleForm.typename)">×</span>
                            </div>
                            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
                                     class="demo-ruleForm">
                                <el-form-item label="" prop="typename">
                                    <el-input v-model="ruleForm.typename[index]" placeholder="请输入对应类型" style="width: 150px !important;"></el-input>
                                </el-form-item>
                            </el-form>
                        </div>
                        <div class="upload_img" v-show="imageDetails.length < 10">
                            <span class="upload_btn" @click="upLoadDetails">+</span>
                        </div>

                    </div>
                </div>
            </div>

            <hr>
            <el-button type="primary" @click="submitForm" style="float: right;margin-bottom: 20px">上传<i
                    class="el-icon-upload el-icon--right"></i></el-button>

        </el-card>
    </div>
</template>
<script>
export default {
    data(){
        let checkInput = (rule, value, callback) => {
            if (!value.length) {
                return callback(new Error('对应类型不能为空'));
            }
            setTimeout(() => {
                if (value[0].length > 6) {
                    callback(new Error('字数少于6'));
                } else {
                    callback();
                }
            }, 300);
        };
        return{
            ruleForm: {
                typename:[]
            },
            rules: {
                typename: [
                    { validator: checkInput, trigger: 'blur' ,require:true}
                ]
            },
            imageDetails:[],
            imageMains:[],
            uploadType:'',
            toastMsg:'',
            icon_show:false,
            uploadTypeList:[
                {
                    value: '女装',
                    label: '女装'
                },{
                    value: '男装',
                    label: '男装'
                },{
                    value: '包包',
                    label: '包包'
                },{
                    value: '女鞋',
                    label: '女鞋'
                },{
                    value: '男鞋',
                    label: '男鞋'
                },{
                    value: '母婴',
                    label: '母婴'
                },{
                    value: '电子产品',
                    label: '电子产品'
                },
            ]
        }
    },
    methods: {
        changeDetailImage() {
            let files = this.$refs.avatarInputDetail.files;
            let that = this;
            function readAndPreview(file) {
                if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                    let reader = new FileReader();
                    reader.onload = function () {
                        if (that.imageDetails.indexOf(this.result) === -1) {
                            that.imageDetails.push(this.result);
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
        changeMainImage() {
            let files = this.$refs.avatarInputMain.files;
            let that = this;
            function readAndPreview(file) {
                if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                    let reader = new FileReader();
                    reader.onload = function () {
                        if (that.imageMains.indexOf(this.result) === -1) {
                            that.imageMains.push(this.result);
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
        upLoadDetails() {
            this.$refs.avatarInputDetail.dispatchEvent(new MouseEvent("click"));
        },
        upLoadMains() {
            this.$refs.avatarInputMain.dispatchEvent(new MouseEvent("click"));
        },
        //删除图片
        deleteImg(index, objImgArray,objInput) {
            objImgArray.splice(index, 1);
            objInput.splice(index,1);
        },
        deleteMainImg(index, objImgArray){
            objImgArray.splice(index, 1);
        },
        delShow(e) {
            e.target.lastChild.style.display = 'block';
        },
        delHide(e) {
            e.target.lastChild.style.display = 'none'
        },
        submitForm() {
            if(!this.uploadType){
                this.toastMsg='请选择类型';
                this.icon_show =true;
            }
            if (this.ruleForm.typename.length <10){
                this.$message.error('请输入完整类型')
            }
            this.$http.post('http://120.78.64.17:8086/nice-mall-backend/addpopular',{
                imageDetails:this.imageDetails,
                imageMains:this.imageMains,
                uploadType:this.uploadType,
                typeName:this.ruleForm.typename
            },{
                headers: {'Authorization': window.localStorage.getItem('token')}
            }).then(res=>{
                alert(res.data.message);
            }).catch(err=>{

            })
        },
    }

}
</script>
<style>
    #hot_container{
        width: 80%;
        margin: 50px auto;
        position: relative;
    }
    #hot_container .title{
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid #dddddd;
    }
    #hot_container .content{
        width: 100%;
        margin-top: 10px;
        padding: 20px;
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
        width: 100%;

    }
    .pic_list>div{
        margin-left: 10px;
        position: relative;
        float: left;
        width: 150px;
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
    .upload_type{
        display: inline-block;
    }
    .type_img{
        margin-top: 20px;
    }
    .img_list{
        border: 1px dotted #6d6d72;
        border-radius: 8px;
    }
    .toast_img_message{
        color: red;
        display: inline-block;
        font-size: 13px;
    }
    .toast_img_message span:nth-child(1){
        color: red;
        position: relative;
        top: 4px;
        margin-right: 0;
    }
    .el-input {
        position: relative;
        font-size: 14px;
        height: 50px;
        display: inline-block;
        width: 100%;
    }

    .el-select>.el-input {
        display: block;
        width: 100%;
    }
    .el-button--primary {
        color: #FFF;
        background-color: #409EFF;
        border-color: #409EFF;
        margin-top: 20px;
        float: right;
    }
    .el-input__icon {
        height: 100%;
        width: 25px;
        margin-top: 6px;
        text-align: center;
    }
    .el-input--suffix .el-input__inner {
        padding-right: 30px;
        width: 250px;
        height: 35px;
        margin: 10px 0;
    }
    .el-input__prefix, .el-input__suffix {
        position: absolute;
        top: -14px !important;
    }
    .el-form-item {
        margin-bottom: 22px;
        width: 150px;
        margin-top: 15px;
    }
    .el-form-item__content {
        line-height: 40px;
        width:150px !important;
        position: relative;
        font-size: 14px;
        height: 40px;
    }
    .el-input__inner {
         width: 150px;
     }
</style>