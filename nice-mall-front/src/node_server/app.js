const express = require('express');
const app = express();
const bodyParser = require('body-parser');
app.use('/src/',express.static('./src'));
app.set('views','vue-project/src');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());

//允许跨域访问
app.all('*', function (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    res.header('Access-Control-Allow-Methods', '*');
    res.header('Content-Type', 'application/json;charset=utf-8');
    next();
});
//女装
let woman_data ={
    hot_head_img:'../src/image/1.jpg',
    hot_type:[
        {goods_type_img:'../src/image/cloth1.jpg', goods_type_name:'T恤'},
        {goods_type_img:'../src/image/cloth2.jpg', goods_type_name:'衬衫'},
        {goods_type_img:'../src/image/cloth3.jpg', goods_type_name:'卫衣'},
        {goods_type_img:'../src/image/cloth4.jpg', goods_type_name:'短裤'},
        {goods_type_img:'../src/image/cloth5.jpg', goods_type_name:'背带裤'},
        {goods_type_img:'../src/image/cloth6.jpg', goods_type_name:'连衣裙'},
        {goods_type_img:'../src/image/cloth7.jpg', goods_type_name:'文胸'},
        {goods_type_img:'../src/image/cloth8.jpg', goods_type_name:'睡衣/浴袍'},
        {goods_type_img:'../src/image/cloth9.jpg', goods_type_name:'围巾'},
        {goods_type_img:'../src/image/cloth10.jpg', goods_type_name:'外套'}
    ],
    goods:[
        {
            goods_id:1,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:2,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:3,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:4,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:5,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:6,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:7,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:8,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:9,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:10,
            goods_img:'../src/image/goods1.jpg',
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        }
    ]
};
//男装
let man_data = {
    hot_head_img:'../src/image/2.jpg',
    hot_type: [
        {goods_type_img: '../src/image/cloth1.jpg', goods_type_name: 'T恤'},
        {goods_type_img: '../src/image/cloth2.jpg', goods_type_name: '衬衫'},
        {goods_type_img: '../src/image/cloth3.jpg', goods_type_name: '卫衣'},
        {goods_type_img: '../src/image/cloth4.jpg', goods_type_name: '短裤'},
        {goods_type_img: '../src/image/cloth5.jpg', goods_type_name: '内裤'},
        {goods_type_img: '../src/image/cloth6.jpg', goods_type_name: '背心'},
        {goods_type_img: '../src/image/cloth7.jpg', goods_type_name: '睡衣'},
        {goods_type_img: '../src/image/cloth8.jpg', goods_type_name: '鞋子'},
        {goods_type_img: '../src/image/cloth9.jpg', goods_type_name: '帽子'},
        {goods_type_img: '../src/image/cloth10.jpg', goods_type_name: '外套'}
    ],
    goods: [
        {
            goods_id: 1,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '金利来男装精选专场',
            goods_price: '99',
            goods_orgprice: '129',
        },
        {
            goods_id: 2,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 3,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 4,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 5,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 6,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 7,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 8,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 9,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        },
        {
            goods_id: 10,
            goods_img: '../src/image/goods2.jpg',
            goods_name: '海澜之家',
            goods_price: '69',
            goods_orgprice: '99',
        }
    ]
};
//分类选择
let select_data = {
    goods:[
        {
            goods_id:1,
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:2,
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        },
        {
            goods_id:3,
            goods_name:'日韩风木耳边不卷边日韩风木耳边不卷边',
            goods_price:'45',
            goods_orgprice:'99',
        }
    ],
    goodsSelect:[
        {
            goods_type_id:1,
            all_type_name:'女装',
            type_title:'精选女装 心动价格，等你来选',
            type_desc:'3'
        },
        {
            goods_type_id:2,
            all_type_name:'男装',
            type_title:'精选男装 热销专场',
            type_desc:'5'
        },
        {
            goods_type_id:3,
            all_type_name:'母婴',
            type_title:'母婴用品 颜值与质量共存',
            type_desc:'4.2'
        },
        {
            goods_type_id:4,
            all_type_name:'鞋包',
            type_title:'鞋子包包 做时尚的弄潮儿',
            type_desc:'7.5'
        },
        {
            goods_type_id:5,
            all_type_name:'运动',
            type_title:'运动潮品 运动魅力无限',
            type_desc:'5.3'
        },
        {
            goods_type_id:6,
            all_type_name:'食品',
            type_title:'零食大礼包 想吃的 我们应有尽有',
            type_desc:'3'
        },
        {
            goods_type_id:7,
            all_type_name:'居家',
            type_title:'居家 为生活加一抹色彩',
            type_desc:'6.3'
        },
        {
            goods_type_id:8,
            all_type_name:'更多',
            type_title:'更多精品 发现身边的美丽 ',
            type_desc:'4'
        }

    ]
};
//所有商品的类型列表
let type_data = {
    goods_type_list: [
        {
            name_list: '女装/男装/内衣',
            goods_list: [{
                goods_list_name: '人气美衣',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣', '雪纺衫', '针织衫', '小西装', '风衣', '马甲', '羽绒', '棉衣', '毛衣', '大衣', '羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '女下装',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '男下装',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套', '皮肤衣', '针织衫', 'Polo衫', '夹克', '西装', '卫衣', '衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '女士内衣',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '男士内衣',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '女鞋/男鞋/箱包',
            goods_list: [{
                goods_list_name: '女单鞋',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣', '雪纺衫', '针织衫', '小西装', '风衣', '马甲', '羽绒', '棉衣', '毛衣', '大衣', '羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '休闲鞋',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '男凉鞋',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套', '皮肤衣', '针织衫', 'Polo衫', '夹克', '西装', '卫衣', '衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '女凉鞋',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '单鞋',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '护肤彩妆/个护',
            goods_list: [{
                goods_list_name: '面部护肤',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣', '雪纺衫', '针织衫', '小西装', '风衣', '马甲', '羽绒', '棉衣', '毛衣', '大衣', '羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '彩妆香水',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '洗发护发',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套', '皮肤衣', '针织衫', 'Polo衫', '夹克', '西装', '卫衣', '衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '沐浴润体',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '美妆工具',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '运动户外',
            goods_list: [{
                goods_list_name: '运动服',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套']
            }, {
                goods_list_name: '运动鞋包',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '户外服',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣']
            }, {
                goods_list_name: '户外鞋',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '运动用品',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '家电数码',
            goods_list: [{
                goods_list_name: '热销配件',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣', '雪纺衫', '针织衫', '小西装', '风衣', '马甲', '羽绒', '棉衣', '毛衣', '大衣', '羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '手机配件',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '智能影音',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套', '皮肤衣', '针织衫', 'Polo衫', '夹克', '西装', '卫衣', '衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '电脑办公',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '大家电',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '母婴童装',
            goods_list: [{
                goods_list_name: '婴幼专区',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', '风衣', '马甲', '羽绒', '棉衣', '毛衣', '大衣', '羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '童鞋专区',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '奶粉食品',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套', '皮肤衣','羊绒/羊毛衫']
            }, {
                goods_list_name: '尿裤纸品',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '孕妈专区',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '居家用品',
            goods_list: [{
                goods_list_name: '家纺床品',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣','羊绒/羊毛衫', '皮衣/皮草', '套装']
            }, {
                goods_list_name: '杯壶餐具',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '家饰布艺',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤','衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '烹饪炊具',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }, {
                goods_list_name: '卫浴/灯饰',
                goods_all: ['男士内裤', '组合内裤', '平角裤', '男袜', '背心/T恤']
            }]
        },
        {
            name_list: '零食礼包',
            goods_list: [{
                goods_list_name: '良品铺子',
                goods_all: ['T恤', '衬衫', '连衣裙', '外套', ' 短外套', '卫衣', '防晒衣', '雪纺衫', '针织衫','皮衣/皮草', '套装']
            }, {
                goods_list_name: '各种坚果',
                goods_all: ['休闲裤', '半身裙', '打底裤', '西装裤', '牛仔裤', '阔腿裤', '短裤', '连体/背带裤', '哈伦裤', '牛仔裙']
            }, {
                goods_list_name: '手工食品',
                goods_all: ['短袖T恤', '棉衣', '大衣', '棒球服', '毛衣', '羽绒服', '皮衣', '马甲/背心', '风衣', '长袖T恤', '外套','夹克', '西装', '卫衣', '衬衫', '羊绒/羊毛衫']
            }, {
                goods_list_name: '早餐面包',
                goods_all: ['聚拢文胸', '无钢圈文胸', '文胸套装', '薄杯文胸', '运动文胸', '调整型文胸', '美背文胸', '睡衣/家居服', '纯棉睡衣', '女士内裤', '丝袜/裤袜', '吊带']
            }]
        }
    ]
};

//购物车列表
let car_data = [
    {
        car_goods_id:1,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '香影新款很仙的印花裙子夏款流行荷叶边裙超仙雪纺连衣裙',
        car_goods_price:'45',
        car_goods_num:2
    },
    {
        car_goods_id:2,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '香影新款很仙的印花裙子夏款流行',
        car_goods_price:'49',
        car_goods_num:1
    },
    {
        car_goods_id:3,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '香影新款很仙的印花裙子夏款流行荷叶边裙超仙雪纺连衣裙',
        car_goods_price:'75',
        car_goods_num:1
    },
    {
        car_goods_id:4,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '印花裙子夏款流行荷叶边裙超仙雪纺连衣裙',
        car_goods_price:'112',
        car_goods_num:1
    },
    {
        car_goods_id:5,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '香影新款很仙的印花裙子夏款流行荷叶边裙超仙雪纺连衣裙',
        car_goods_price:'45',
        car_goods_num:2
    },
    {
        car_goods_id:6,
        car_goods_img:'../src/image/cloth1.jpg',
        car_goods_name: '香影新款很仙的印花裙子超仙雪纺连衣裙',
        car_goods_price:'98',
        car_goods_num:3
    }
];
app.get('/',function (req,res) {
    res.send(type_data);
});
app.get('/home',function (req,res) {
    res.send(select_data);
});

//根据类型 获得数据
app.get('/gettypegoodslist',function (req,res) {
    if(req.query.type === '女装'){
        res.send(woman_data);
    }
    else if(req.query.type === '男装'){
        console.log(req.query.type);
        res.send(man_data);
    }
});
app.get('/getgoodslist',function (req,res) {
    console.log(req.query.goodsname);
    res.send(woman_data);
});
app.post('/buy',function (req,res) {
   console.log(req.body);
   res.send('buy_ok');
});
app.post('/addcar',function (req,res) {
    console.log(req.body);
    res.send('car_ok');
});

app.get('/goods_car',function (req,res) {
   res.end('ok')
});
app.get('/getcarlist',function (req,res) {
   res.send(car_data)
});
app.listen(3030,function () {
    console.log("http://localhost:3030");
});