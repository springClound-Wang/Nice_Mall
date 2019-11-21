
import Vue from 'vue'
import App from './App'
import router from './router'
//导入 mint-UI组件
import '../node_modules/mint-ui/lib/style.css'

import './assets/css/index.css'
import './assets/font/font1/iconfont.css'
import './assets/font/font2/iconfont.css'
import './assets/font/font3/iconfont.css'

//轮播图组件
import { Swipe, SwipeItem ,Cell} from 'mint-ui';
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);

//引入 ElementUI组件
import ElementUI from 'element-ui';
Vue.use(ElementUI);

//切换框
import { TabContainer, TabContainerItem } from 'mint-ui';
Vue.component(TabContainer.name, TabContainer);
Vue.component(TabContainerItem.name, TabContainerItem);
Vue.component(Cell.name, Cell);


// // 消息提示
import { Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.component(Message.name, Message);
Vue.prototype.$message = Message;

import Axios from 'axios';
Vue.prototype.$http = Axios;
Axios.defaults.baseURL='http://120.78.64.17:8086/nice-mall-backend';
Vue.config.productionTip = false;

//跳转到页面顶部
router.afterEach(() => {
  window.scrollTo(0,0);
});
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
