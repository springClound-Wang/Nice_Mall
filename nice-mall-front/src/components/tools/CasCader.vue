<template>
    <div style="margin: 50px">
      <div class="block">
        <span class="demonstration">默认 click 触发子菜单</span>
        <el-cascader
          v-model="value"
          :options="options"
          @change="handleChange"></el-cascader>
      </div>

    </div>
</template>

<script>
  export default {
    data() {
      return {
        value: [],
        options:[]
      };
    },
    created(){
      this.getTypeGoodsList();
    },
    methods: {
      handleChange(value) {
        console.log(value);
      },
      getTypeGoodsList(){
        this.$http.get('/home/sort').then(res=>{
          let arr = res.data.data;
          console.log(arr);
          for(let i =0;i<arr.length;i++){
            this.options.push({
              value:arr[i].nameList,
              label:arr[i].nameList,
              children:arr[i].goodsList.map(item=>{
                return{
                  value:item.goodsListName,
                  label:item.goodsListName,
                  children:item.goodsAll.map(item1=>{
                    return{
                      value:item1,
                      label:item1,
                    }
                  })
                }
              })
            });
          }
        }).catch(err=>{
          console.log(err);
        })
      },
    }
  };
</script>

<style scoped>
  /*children:this.options.children.push({*/
  /*value:arr[i].goodsList[i].goodsListName,*/
  /*label:arr[i].goodsList[i].goodsListName,*/
  /*children:this.options.children.children.push({*/
    /*value:arr[i].goodsList[i].goodsAll[i],*/
    /*label:arr[i].goodsList[i].goodsAll[i],*/
  /*})*/
  /*})*/
</style>
<!--options: [-->
<!--{-->
<!--value: 'zhinan',-->
<!--label: '指南',-->
<!--children: [{-->
<!--value: 'shejiyuanze',-->
<!--label: '设计原则',-->
<!--children: [{-->
<!--value: 'yizhi',-->
<!--label: '一致'-->
<!--}, {-->
<!--value: 'fankui',-->
<!--label: '反馈'-->
<!--}, {-->
<!--value: 'xiaolv',-->
<!--label: '效率'-->
<!--}, {-->
<!--value: 'kekong',-->
<!--label: '可控'-->
<!--}]-->
<!--}, {-->
<!--value: 'daohang',-->
<!--label: '导航',-->
<!--children: [{-->
<!--value: 'cexiangdaohang',-->
<!--label: '侧向导航'-->
<!--}, {-->
<!--value: 'dingbudaohang',-->
<!--label: '顶部导航'-->
<!--}]-->
<!--}]-->
<!--}, {-->
<!--value: 'zujian',-->
<!--label: '组件',-->
<!--children: [{-->
<!--value: 'basic',-->
<!--label: 'Basic',-->
<!--children: [{-->
<!--value: 'layout',-->
<!--label: 'Layout 布局'-->
<!--}, {-->
<!--value: 'color',-->
<!--label: 'Color 色彩'-->
<!--}, {-->
<!--value: 'typography',-->
<!--label: 'Typography 字体'-->
<!--}, {-->
<!--value: 'icon',-->
<!--label: 'Icon 图标'-->
<!--}, {-->
<!--value: 'button',-->
<!--label: 'Button 按钮'-->
<!--}]-->
<!--},-->
<!--{-->
<!--value: 'data',-->
<!--label: 'Data',-->
<!--children: [{-->
<!--value: 'table',-->
<!--label: 'Table 表格'-->
<!--}, {-->
<!--value: 'tag',-->
<!--label: 'Tag 标签'-->
<!--}, {-->
<!--value: 'progress',-->
<!--label: 'Progress 进度条'-->
<!--}, {-->
<!--value: 'tree',-->
<!--label: 'Tree 树形控件'-->
<!--}, {-->
<!--value: 'pagination',-->
<!--label: 'Pagination 分页'-->
<!--}, {-->
<!--value: 'badge',-->
<!--label: 'Badge 标记'-->
<!--}]-->
<!--}, {-->
<!--value: 'others',-->
<!--label: 'Others',-->
<!--children: [{-->
<!--value: 'dialog',-->
<!--label: 'Dialog 对话框'-->
<!--}, {-->
<!--value: 'tooltip',-->
<!--label: 'Tooltip 文字提示'-->
<!--}, {-->
<!--value: 'popover',-->
<!--label: 'Popover 弹出框'-->
<!--}, {-->
<!--value: 'card',-->
<!--label: 'Card 卡片'-->
<!--}, {-->
<!--value: 'carousel',-->
<!--label: 'Carousel 走马灯'-->
<!--}, {-->
<!--value: 'collapse',-->
<!--label: 'Collapse 折叠面板'-->
<!--}]-->
<!--}]-->
<!--}, {-->
<!--value: 'ziyuan',-->
<!--label: '资源',-->
<!--children: [{-->
<!--value: 'axure',-->
<!--label: 'Axure Components'-->
<!--}, {-->
<!--value: 'sketch',-->
<!--label: 'Sketch Templates'-->
<!--}, {-->
<!--value: 'jiaohu',-->
<!--label: '组件交互文档'-->
<!--}]-->
<!--}]-->
