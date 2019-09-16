<template>
    <div id="personal_container">
        <el-row class="tac"
                style="float:left;
                       border-right: solid 1px #e6e6e6;">
            <el-col :span="10">
                <div class="block">
                    <el-image style="width: 135px;height: 130px;margin-left: 25px;border: 1px solid #eae8e8"
                              src="./src/image/header.jpg"></el-image>
                </div>
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        @open="handleOpen"
                        @close="handleClose">
                    <el-menu-item index="1">
                        <template>
                            <i class="el-icon-document"></i>
                            <span class='h4_title'>账号管理</span>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <template>
                            <router-link to="/personal_home/person_info">
                                <i class="el-icon-menu"></i>
                                <span>个人资料</span>
                            </router-link>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <template>
                            <router-link to="/personal_home/person_address">
                                <i class="el-icon-document"></i>
                                <span>收货地址</span>
                            </router-link>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <template>
                            <router-link to="/personal_home/not_found">
                                <i class="el-icon-menu"></i>
                                <span>支付宝绑定设置</span>
                            </router-link>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <template>
                            <router-link to="/personal_home/not_found">
                                <i class="el-icon-menu"></i>
                                <span>应用授权</span>
                            </router-link>
                        </template>
                    </el-menu-item>
                    <el-menu-item index="6">
                        <template>
                            <router-link to="/personal_home/not_found">
                                <i class="el-icon-menu"></i>
                                <span>分享绑定</span>
                            </router-link>
                        </template>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </el-row>
        <div class="person_content">
            <transition name="el-zoom-in-center">
                <router-view></router-view>
            </transition>
        </div>

    </div>
</template>
<script>
    export default {
        name:'personal_home.vue',
        data(){
            return{

            }
        },
        methods:{
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
<style>
    #personal_container{
        width: 75%;
        min-width: 1200px;
        margin: 40px auto;
     }
    .person_content{
        display: inline-block;
        width: 73%;
        margin: 0 20px 20px;
        position: relative;
        top: 0;

    }
    .h4_title{
        color: red;
        width: 100px;
        font-size: 17px;
    }
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 530px;
    }
    .el-menu {
        border-right: none !important;
        list-style: none;
        position: relative;
        margin: 0;
        padding-left: 0;
        background-color: #FFF;
    }
    .el-input__inner {
        width: 280px;
    }
</style>