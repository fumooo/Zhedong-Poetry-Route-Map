import store from '@/store/index.js' //可以直接导入其他js文件
// 1.该文件专门用来创建整个应用的路由器
import Router from 'vue-router'
import Vue from 'vue'
Vue.use(Router) 
// 2.引入路由
import Container from "../view/front/Container"
import BackIndex from "../view/back/BackIndex"
import CityManage from "../view/back/RouterMain/City/CityManage.vue"
import PeopleManage from "../view/back/RouterMain/People/PeopleManage.vue"
import SceneryManage from "../view/back/RouterMain/Scenery/SceneryManage.vue"
import Login from "../view/back/Login/Login.vue"
import AdminManage from "../view/back/RouterMain/Admin/AdminManage.vue"
import PeoplePoemManage from "../view/back/RouterMain/PeoplePoem/PeoplePoemManage.vue"
import Password from "../view/back/Login/Password.vue"

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router ({
    mode: 'history',
    base: __dirname,
    routes: [
        {
            path: '*', redirect: '/'
        },
        {
            path: '/', component : Container,
        },
        {
            path: '/login', component : Login,
        },
        {
            path:'/backIndex',component : BackIndex,
            beforeEnter:(to,from,next) => {
                if(store.state.account == "" ){
                    alert("请先登陆")
                    next(false)
                        }
                else{
                    next()
                }
            },
            children:[
                {
                    path: 'CityManage', component : CityManage
                },
                {
                    path: 'PeopleManage', component : PeopleManage
                },
                {
                    path: 'SceneryManage', component : SceneryManage
                },
                {
                    path: 'PeoplePoemManage', component : PeoplePoemManage   
                },
                {
                    path: 'AdminManage', component : AdminManage,  
                },
                {
                    path: 'Password', component : Password,  
                },
               
            ]
        },
    ]
})
