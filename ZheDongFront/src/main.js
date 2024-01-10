import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'//这边是需要从创建的文件夹中去引用
import BaiduMap from 'vue-baidu-map'
import axios from 'axios';
import store from './store'
Vue.config.productionTip = false

Vue.use(ElementUI)

Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'gK4WauCOqGhn9GU9nGeTWGWdasr6Y79O'
})
Vue.prototype.$http = axios
axios.defaults.baseURL = "http://localhost:8088"
new Vue({
  render: h => h(App),
  store,
  router,
}).$mount('#app')
