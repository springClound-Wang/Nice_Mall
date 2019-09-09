<template>
    <div id="person_info">
        <div class="info_title">基本资料</div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="昵称" prop="petname" >
                <el-input v-model="ruleForm.petname" placeholder="请输入昵称"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名" prop="truename" class="form_item">
                <el-input v-model="ruleForm.truename" placeholder="请输入真实姓名"></el-input>
            </el-form-item>
            <div class="head_img"><i>*</i>修改头像</div>
            <input type="file" accept="image/*" @change="changeMainImage" ref="avatarInputMain" style="display:none">
            <div class="pic_list_box">
                <div class="pic_item" v-show="imageHeader.length<2" >
                    <div v-for="(src,index) in imageHeader" :key="index"
                         @mouseenter="delShow" @mouseleave="delHide">
                        <img :src="src" width="80" height="80" alt srcset class="img_list">
                        <span class="del_img" @click="deleteImg(index,imageHeader)">×</span>
                    </div>
                    <div class="upload_img" v-show="imageHeader.length < 1" >
                        <span class="upload_btn" @click="upLoadHeader" >+</span>
                    </div>
                </div>
            </div>
            <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="ruleForm.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="生日" required prop="birth">
                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birth" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="居住地" prop="address" style="margin-top: 20px">
                <el-input v-model="ruleForm.address" placeholder="省/市/ 区/县 镇/街道"></el-input>
            </el-form-item>
            <hr>
            <el-form-item style="margin-top: 40px">
                <el-button type="primary" @click="submitForm('ruleForm')" style="float:left;width: 100px;">立即修改</el-button>
                <el-button @click="resetForm('ruleForm')" style="position: absolute">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                imageHeader:[],
                dialogVisible: false,
                disabled: false,
                ruleForm: {
                    petname: '',
                    truename: '',
                    sex: '',
                    birth: '',
                    address: ''
                },
                rules: {
                    petname: [
                        { required: true, message: '请输入昵称', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 1 到 8 个字符', trigger: 'blur' }
                    ],
                    truename: [
                        { required: true, message: '请输入真实姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 6 个字符', trigger: 'blur' }
                    ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    birth: [
                        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    address: [
                        { required: true, message: '请选择时间', trigger: 'change' }
                    ],
                }
            };
        },
        methods: {
            changeMainImage() {
                let files = this.$refs.avatarInputMain.files;
                let that = this;
                function readAndPreview(file) {
                    if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                        let reader = new FileReader();
                        reader.onload = function () {
                            if (that.imageHeader.indexOf(this.result) === -1) {
                                that.imageHeader.push(this.result);
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
            delShow(e) {
                e.target.lastChild.style.display = 'block';
            },
            delHide(e) {
                e.target.lastChild.style.display = 'none'
            },
            upLoadHeader() {
                this.$refs.avatarInputMain.dispatchEvent(new MouseEvent("click"));
            },
            //删除图片
            deleteImg(index, objImgArray) {
                objImgArray.splice(index, 1);
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
<style>
    #person_info{
        width: 100%;
        padding-bottom: 5%;
        border: 1px solid #e6e6e6;
        min-height: 560px;
    }
    .info_title{
        padding: 10px;
        font-size: 19px;
        border-bottom: 1px solid #e6e6e6;
    }
    .head_img{
        width: 100px;
        font-weight: bolder;
        font-size: 14px;
        color: #606266;
        line-height: 40px;
        padding: 0 12px 0 0;
        text-align: right;
        vertical-align: middle;
        float: left;
    }
    .head_img i{
        color: #F56C6C;
        display: inline-block;
        margin-right: 5px;
    }
    #person_info .form_item{
        width: 400px !important;
        height: 60px !important;
    }
    #person_info form label:nth-child(1) {
        margin-top: 0 !important;
    }
    .pic_item {
        display: inline-block;
        position: relative;
        width: 100%;
        margin-left: 12px;
        margin-top: -5%;
    }
    .el-form-item__content {
        /*line-height: 98px !important;*/
        position: relative;
        font-size: 14px;
    }
     .el-form-item {
         width: 50%;
         margin-bottom: 0 !important;
     }
    .el-form-item__content {
        line-height: 40px;
        width: 400px;
        position: relative;
        font-size: 14px;
        height: 40px;
    }
    .el-input__icon {
        margin-top: 17px;
    }
    .el-upload__input {
        display: none !important;
    }
</style>