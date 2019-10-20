<template>
    <div id="person_info">
        <div class="info_title">基本资料</div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="昵称" prop="petname" class="form_item">
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
                         @mouseenter="delShow" @mouseleave="delHide" class="icon">
                        <img :src="src" width="80" height="80" alt srcset class="img_list">
                        <span class="del_img" @click="deleteImg(index,imageHeader)">×</span>
                    </div>
                    <div class="upload_img" v-show="imageHeader.length < 1" >
                        <span class="upload_btn" @click="upLoadHeader" >+</span>
                    </div>
                </div>
            </div>
            <el-form-item label="性别" prop="sex" class="form_item">
                <el-radio-group v-model="ruleForm.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="生日" required prop="birth" class="form_item">
                <el-input type="date" placeholder="选择日期" v-model="ruleForm.birth" style="width: 100%;"></el-input>
            </el-form-item>
            <el-form-item label="居住地" prop="address"  class="form_item">
                <el-input v-model="ruleForm.address" placeholder="省/市/ 区/县 镇/街道"></el-input>
            </el-form-item>
            <hr>
            <el-form-item style="margin: 40px 0 " class="form_item">
                <el-button type="primary" @click="submitForm" style="float:left;width: 100px;margin-left: 30%;">立即修改</el-button>
                <el-button @click="resetForm('ruleForm')" style="position: absolute;top: 0;left: 70%;">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        inject:['reload'],
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
                        { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
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
        created(){
            this.getPersonInfo();
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
            //查询信息
            getPersonInfo(){
              this.$http.get('/personal/getdata',{
                params:{
                  userId:window.localStorage.getItem('userId')
                },
                headers:{Authorization: window.localStorage.getItem('token')}
              }).then(res=>{
                this.ruleForm.petname = res.data.data.userName;
                this.ruleForm.truename = res.data.data.userTrueName;
                this.ruleForm.address = res.data.data.address;
                this.ruleForm.birth = res.data.data.userBirth;
                this.ruleForm.sex = res.data.data.userSex;
                this.ruleForm.address = res.data.data.userAddress;
                window.localStorage.setItem("userAvatar",res.data.data.userAvatar);
                window.localStorage.setItem("username",res.data.data.userName);
              }).catch(err=>{
                this.$message.error('查询个人信息失败！')
              })
            },
            //提交修改
            submitForm() {
                this.$http.put('/personal/change',{
                  userId:window.localStorage.getItem('userId'),
                  userName:this.ruleForm.petname,
                  userTrueName:this.ruleForm.truename,
                  userSex:this.ruleForm.sex,
                  userBirth:this.ruleForm.birth,
                  userAvatar:this.imageHeader,
                  userAddress:this.ruleForm.address
                },{
                  headers:{Authorization: window.localStorage.getItem('token')}
                }).then(res =>{
                    this.$message.success(res.data.message);
                    window.location.reload();
                }).catch(err=>{
                  this.$message.success(err.data.message);
                })
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
    #person_info .el-form{
      margin-top: 20px;
    }
    .head_img{
        width: 85px;
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
    /*上传图片*/
    .upload_img {
      width: 146px;
      height: 150px;
      font-size: 55px;
      border-radius: 10px;
      margin-left: 10%;
      margin-top: 15px;
      border: 1px dotted #cfd0d3;
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
      top: 0;
      z-index: 1000;
      color: #fe0e09;
    }
    #person_info .el-form-item__content {
        /*line-height: 98px !important;*/
        position: relative;
        font-size: 14px;
    }
    #person_info .el-form-item {
         width: 50%;
         margin-bottom: 0 !important;
     }
    #person_info .el-form-item__content {
        line-height: 40px;
        width: 400px;
        position: absolute;
        font-size: 14px;
        height: 40px;
        display: inline-block;
        margin-left: 0 !important;
    }
    #person_info .el-input__icon {
      margin-top: 0px;
    }
    #person_info .el-upload__input {
        display: none !important;
    }
    #person_info .el-input {
      position: relative;
      font-size: 14px;
      height: 50px;
      display: inline-block;
      width: 100%;
    }
    .icon{
      width: 150px;
      height: 150px;
      position: relative;
      margin-left: 10%;
    }
    .icon img{
      width: 100%;
      height: 100%;
      position: relative;
    }
</style>
