<template>
    <div class="not_container">
        <el-row>
            <el-col :span="12">
            <img src="../assets/images/gif_404.gif" alt="404" class="img-style">
        </el-col>
            <el-col :span="12">
                <div style="margin-left: 100px;margin-top: 60px">
                    <h2 class="color-main">Not Found!</h2>
                    <h3 style="color: #606266">很抱歉，页面它不小心迷路了！</h3>
                    <div style="color:#909399;font-size: 14px">请检查您输入的网址是否正确，请点击以下按钮返回主页或者发送错误报告</div>
                    <el-button style="margin-top: 20px" type="primary" round @click="handleGoMain">返回</el-button>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: 'wrongPage',
        data() {
            return {

            }
        },
        methods: {
            handleGoMain() {
                this.$router.go(-1);
            }
        },
    }
</script>

<style scoped>
    .not_container {
        width: 80%;
        margin:70px auto 100px;
        position: relative;
    }
    .img-style {
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
    }
</style>
