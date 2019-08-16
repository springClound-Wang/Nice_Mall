const path = require('path');
const htmlWebpackPlugin = require('html-webpack-plugin');
const vueLoaderPlugin= require('vue-loader/lib/plugin');
module.exports ={
    entry:path.join(__dirname,'./src/main.js'),
    output: {
        path:path.join(__dirname,'./dist'), //指定哪个目录 打包哪个文件
        filename: "bundle.js"  // 指定住处文件名
    },
    devServer: {
        open:true,
        port:8080,
        // contentBase:'',
        hot:true
    },
    plugins: [
        new htmlWebpackPlugin({
            template:path.join(__dirname,'./src/index.html'),
            filename: 'index.html'
        }),
        new vueLoaderPlugin()
    ],
    module: {
        rules: [
            //处理css路径的loader
            {test:/\.css$/,use:['style-loader','css-loader']},

            //处理图片路径的loader
            {test:/\.(jpg|png|gif|bmp|jpeg)$/,use:'url-loader?limit=99948&name=[hash:8]-[name].[ext]'},
            //limit给定的值是图片大小 单位是type,如果我们引用的图片大于等于 limit则会转换为base64  但小于则不会转换成base64
            //name=[name].[ext] 设置编译后 9不自动分配名字 保持原来图片的名字

            //处理字体的loader
            {test:/\.(ttf|eot|svg|woff|woff2)$/,use:'url-loader'},
            //处理vue文件
            {test:/\.vue$/,use:'vue-loader'}
        ]
    },
    resolve: {
        //相当于import Vue from '../node_modules/vue/dist/vue'
        alias:{
            'vue$':"vue/dist/vue.js"
        }
    }
};