<template>
    <div class="detail-container">
        <el-card shadow="never">
            <span class="font-title-medium">商品数据列表</span>
            <el-table
                    border
                    class="standard-margin"
                    ref="productTable"
                    :data="productList">

                <el-table-column label="商品图片" width="160" align="center">
                    <template slot-scope="scope">
                        <img style="height:80px" :src="scope.row.productPic">
                    </template>
                </el-table-column>
                <el-table-column label="货号" width="100" align="center">
                    <template slot-scope="scope">
                     <span class="font-small">NO.{{scope.row.productId}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="商品名称" width="160" align="center">
                    <template slot-scope="scope">
                        <span class="font-small">{{scope.row.productName}}</span><br>
                    </template>
                </el-table-column>
                <el-table-column label="品牌" width="130" align="center">
                    <template slot-scope="scope">
                        <span class="font-small">{{scope.row.productBrand}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="价格" width="130" align="center">
                    <template slot-scope="scope">
                        <span class="font-small">￥{{scope.row.productRealPrice}}</span><br>
                    </template>
                </el-table-column>

                <el-table-column label="标签" width="120" align="center">
                    <template slot-scope="scope">{{scope.row.productAttr}}</template>
                </el-table-column>
                <el-table-column label="数量" width="100" align="center">
                    <template slot-scope="scope">{{scope.row.productNum}}</template>
                </el-table-column>
                <el-table-column label="库存量" width="100" align="center">
                    <template slot-scope="scope">￥{{scope.row.productCount}}</template>
                </el-table-column>
                <el-table-column label="推荐" width="130" align="center">
                    <template slot-scope="scope">
                        <el-switch
                            v-model="scope.row.productShow"
                            active-color="#13ce66"
                            inactive-color="rgb(173, 176, 184)">
                        </el-switch>
                    </template>
                </el-table-column>
            </el-table>
            <el-button
                    class="btn-add"
                    @click=""
                    size="mini"
                    type="primary" plain>
                确认提交
            </el-button>
        </el-card>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                productList:[
                    {
                        productId:'1',
                        productPic:'./src/image/cloth1.jpg',
                        productName: '华为p30',
                        productBrand:'华为',
                        productAttr: '手机',
                        productCount:'10200',
                        productNum:'200',
                        productPrice: '4500',
                        productRealPrice: '5000',
                        productShow:false
                    },
                    {
                        productId:'2',
                        productPic:'./src/image/cloth2.jpg',
                        productName: '华为p30',
                        productBrand:'华为',
                        productAttr: '手机',
                        productCount:'10200',
                        productNum:'200',
                        productPrice: '4500',
                        productRealPrice: '5000',
                        productShow:false
                    },
                    {
                        productId:'3',
                        productPic:'./src/image/cloth3.jpg',
                        productName: '华为p30',
                        productBrand:'华为',
                        productAttr: '手机',
                        productCount:'10200',
                        productNum:'200',
                        productPrice: '4500',
                        productRealPrice: '5000',
                        productShow:false
                    },
                    {
                        productId:'4',
                        productPic:'./src/image/cloth4.jpg',
                        productName: '华为p30',
                        productBrand:'华为',
                        productAttr: '手机',
                        productCount:'10200',
                        productNum:'200',
                        productPrice: '4500',
                        productRealPrice: '5000',
                        productShow:false
                    },
                ],

            }
        },
        created() {

        },
        computed: {

        },
        methods: {

        }

    }
</script>
<style>
    .detail-container {
        width: 90%;
        margin:50px auto;
        position: relative;
    }
    .standard-margin {
        margin-top: 15px;
    }
    .el-table--scrollable-x .el-table__body-wrapper {
        overflow-x: hidden !important;
    }
    .btn-add{
        margin: 10px 0 !important;
        float: right !important;
    }
    .el-card {
        border: 1px solid #dddee1 !important;
        background-color: #FFF;
        color: #303133;
        -webkit-transition: .3s;
        transition: .3s;
    }
</style>


