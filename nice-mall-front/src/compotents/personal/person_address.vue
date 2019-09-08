<template>
    <div id="person_info">
        <div class="info_title">收货地址</div>
        <div style="margin: 20px; color: #e35151;">新增收获地址 > </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="居住地" prop="address" style="margin-top: 20px">
                <el-input v-model="ruleForm.address" placeholder="省/市/ 区/县 镇"></el-input>
            </el-form-item>
            <el-form-item label="地址详情" prop="addressDetails" class="form_item">
                <el-input type="textarea" v-model="ruleForm.addressDetails" placeholder="请输入详细地址信息，如街道门牌号、小区、楼栋号、单元等信息"></el-input>
            </el-form-item>
            <el-form-item label="邮政编码" prop="postCode" style="margin-top: 20px">
                <el-input v-model="ruleForm.postCode" placeholder="请输入邮政编码"></el-input>
            </el-form-item>
            <el-form-item label="收货人姓名" prop="name" style="margin-top: 20px">
                <el-input v-model="ruleForm.name" placeholder="请输入收货人姓名"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phoneNum" style="margin-top: 20px">
                <el-input v-model="ruleForm.phoneNum" placeholder="请输入手机号码"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 20px">
                <el-button type="primary" @click="submitForm('ruleForm')" style="float:left;">保存</el-button>
                <el-checkbox v-model="tacitAddress" style="margin-left: 50%;">设为默认地址</el-checkbox>
            </el-form-item>
        </el-form>
        <el-alert
                title="已保存5了条地址，还能保存15条地址"
                style="width: 93%;
                height:45px;
                margin: 20px auto;
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
                    prop="name"
                    label="收货人"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="所在地区"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="addressDetails"
                    label="详细地址">
            </el-table-column>
            <el-table-column
                    prop="postCode"
                    label="邮编">
            </el-table-column>
            <el-table-column
                    prop="phoneNum"
                    label="电话/手机">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <el-button
                            @click.native.prevent="deleteRow(scope.$index, tableData)"
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
                    address: '',
                    addressDetails: '',
                    name: '',
                    postCode: '',
                    phoneNum:''
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
                tableData: [{
                    name: '王小虎',
                    address: '上海市普陀区',
                    addressDetails:'金沙江路 1518 弄',
                    postCode:'720000',
                    phoneNum:'11112132131'
                }, {
                    name: '王小虎',
                    address: '上海市普陀区',
                    addressDetails:'金沙江路 1518 弄',
                    postCode:'720000',
                    phoneNum:'11112132131'
                }, {
                    name: '王虎',
                    address: '上海市普陀区',
                    addressDetails:'金沙江路 1518 弄',
                    postCode:'720000',
                    phoneNum:'11112132131'
                }, {
                    name: '王小',
                    address: '上海市普陀区',
                    addressDetails:'金沙江路 1518 弄',
                    postCode:'720000',
                    phoneNum:'11112132131'
                }]
            };
        },
        methods: {
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
            },
            deleteRow(index, rows) {
                rows.splice(index, 1);
            }
        }
    }
</script>
<style>

    .el-button {
       width: 90px !important;
       float: left;
       margin-bottom:20px;
   }
    .el-alert__title {
        font-size: 14px !important;
        line-height: 18px;
    }
</style>