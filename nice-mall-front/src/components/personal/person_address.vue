<template>
    <div id="person_info">
        <div class="info_title">收货地址</div>
        <div style="margin: 20px; color: #e35151;">新增收获地址 > </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="居住地" prop="receiptArea" class="form_item">
                <el-input v-model="ruleForm.receiptArea" placeholder="省/市/ 区/县 镇"></el-input>
            </el-form-item>
            <!--<br>-->
            <el-form-item label="地址详情" prop="detailAddress" class="form_item">
              <el-input type="textarea" v-model="ruleForm.detailAddress" placeholder="请输入详细地址信息，如街道门牌号、小区、楼栋号、单元等信息"></el-input>
            </el-form-item>
            <el-form-item label="邮政编码" prop="postalCode" class="form_item">
                <el-input v-model="ruleForm.postalCode" placeholder="请输入邮政编码"></el-input>
            </el-form-item>
            <el-form-item label="收货人姓名" prop="receiptName"  class="form_item">
                <el-input v-model="ruleForm.receiptName" placeholder="请输入收货人姓名"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="receiptPhone"  class="form_item">
                <el-input v-model="ruleForm.receiptPhone" placeholder="请输入手机号码"></el-input>
            </el-form-item>
            <el-form-item class="form_item">
                <el-button type="primary" @click="submitForm()" style="margin-left: 15%" >保存</el-button>
                <el-checkbox v-model="tacitAddress" style="margin-left: 22%;">设为默认地址</el-checkbox>
            </el-form-item>
        </el-form>
        <el-alert
                title="已保存5了条地址，还能保存15条地址"
                style="width: 93%;
                height:45px;
                margin: 50px auto 20px;
                background-color: #e3f2fd;
                color: #87878d"
                type="info"
                :closable="false"
                show-icon>
        </el-alert>
        <el-table
                :data="tableData"
                border
                style="width: 93%;
                margin: 0 auto;">
            <el-table-column
                    prop="receiptName"
                    label="收货人"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="receiptArea"
                    label="所在地区"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="detailAddress"
                    width="180"
                    label="详细地址">
            </el-table-column>
            <el-table-column
                    prop="postalCode"
                    width="120"
                    label="邮编">
            </el-table-column>
            <el-table-column
                    prop="receiptPhone"
                    width="140"
                    label="电话/手机">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, tableData,tableData[scope.$index])"
                            type="text"
                            size="small">
                        移除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                tacitAddress:'',//默认地址
                ruleForm: {
                  receiptName:"",
                  receiptArea:'',
                  detailAddress:'',
                  postalCode:'',
                  receiptPhone:'',
                  sparePhone:'',
                  receiptTime:'',
                },
                rules: {
                    address: [
                        { required: true, message: '请输入地址', trigger: 'blur' },
                    ],
                    name: [
                        { required: true, message: '请输入真实姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 6 个字符', trigger: 'blur' }
                    ],
                    addressDetails: [
                        { required: true, message: '请输入详细地址', trigger: 'blur' }
                    ],
                    phoneNum: [
                        { required: true, message: '请输入手机号', trigger: 'blur' }
                    ],
                    postCode: [
                        { required: true, message: '请输入邮政编码', trigger: 'blur' },
                        { min: 3, max: 6, message: '长度为6个字符', trigger: 'blur' }
                    ],
                },
                tableData: []
            };
        },
        created(){
            this.getAddress();
        },
        methods: {
            getAddress(){
              this.$http.get('http://120.78.64.17:8086/nice-mall-backend/personal/getaddr',{
                params:{userId:window.localStorage.getItem('userId')},
                headers: {Authorization: window.localStorage.getItem('token')}
              }).then(res=>{
                this.tableData = res.data.data;
              }).catch(err=>{
                this.$message.error(err.data.message);
              })
            },
            submitForm() {
              this.$http.post('http://120.78.64.17:8086/nice-mall-backend/personal/address',{
                userId:window.localStorage.getItem('userId'),
                receiptName:this.ruleForm.receiptName,
                receiptArea:this.ruleForm.receiptArea,
                detailAddress:this.ruleForm.detailAddress,
                postalCode:this.ruleForm.postalCode,
                receiptPhone:this.ruleForm.receiptPhone,
                sparePhone:null,
              }, {
                headers: {Authorization: window.localStorage.getItem('token')}
              }).then(res=>{
                this.$message.success(res.data.message);
              }).catch(err=>{
                this.$message.success(err.data.message);
              })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },


            //TODO 删除地址  delete请求 发送给后台 addressId
            deleteRow(index, rows,cur) {
                rows.splice(index, 1);
                console.log(cur.addressId);
                this.$http.delete('/personal/deladdr',{
                  params:{
                    addressId:cur.addressId
                  },
                  headers:{Authorization: window.localStorage.getItem('token')}
                }).then(res=>{
                    this.$message.success(res.data.message)
                }).catch(err=>{
                    this.$message.error(err.data.message)
                })
            }
        }
    }
</script>
<style>
    .form_item{
      margin-left: 0;
    }
    #person_info .el-button {
       width: 90px;
       margin-bottom:20px;
   }
    #person_info .el-alert__title {
        font-size: 14px !important;
        line-height: 18px;
    }
    #person_info .el-form-item__content {
        line-height: 40px;
        width: 325px !important;
        position: relative;
        font-size: 14px;
        height: 40px;
    }
    #person_info .cell{
      text-align: center;
    }
    #person_info .el-button--primary {
        color: #FFF;
        background-color: #409EFF;
        border-color: #409EFF;
         margin-top: 0;
    }
    #person_info .el-form-item__content {
      line-height: 40px;
      width: 400px;
      position: absolute;
      font-size: 14px;
      height: 40px;
      display: inline-block;
      margin-left: 0;
    }
</style>
