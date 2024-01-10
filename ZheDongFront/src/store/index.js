//在src下创建一个store文件夹，其中去创建index.js来创建Vuex中最为核心的store
import Vue from "vue"
import Vuex from "vuex"
import axios from 'axios';
import createVuexAlong from 'vuex-along'
Vue.use(Vuex)
// Vue.prototype.$http = axios
axios.defaults.baseURL = "http://localhost:8088"


//准备state--用于存储数据
const state = {
    checkListDate:[],//选中的多选框数据
    poemList:[],//此数组 用于存储用户点击诗人的多选框诗人id
    peopleName:[],//多选框选中的诗人名字存放在数组之中

    account:"",//用于验证账号
    password:"",//用于验证密码
}

//准备mutations -- 仅仅用于操作数据（state） state指是state数据域，response指的是传过来的数据
const mutations = {
    //重制刷新页面
    REFRESH(state,response){
        state.checkListDate = response 
        console.log(response)
    },

    //更新左边的菜单栏
    updateLeftNav(state,response){
        state.checkListDate = response
    },

    //更新诗人列表
    updatePeopleName(state,response){
        state.peopleName = response
    },

    updateLoginForm(state,response){
        state.account = response.account
        state.password = response.password
        
    }
}

//准备actions--用于响应组件中动作  commit指的是通过action去commit到mutations的这个动作 data指的是从组件中dispatch过来的值
const actions = {

    refresh({commit}){
        axios.get("people").then((response)=>{
        commit('REFRESH',response.data)       
    }).catch(function (error) {
        // 处理错误情况
        console.log(error);
      })
    },

    updateLeftNav({commit},data){
        axios.get("people/mohu",{
            params: {
            name: data
           }
         })
         .then((response)=>{
            commit('updateLeftNav',response.data)
         }) 
         .catch(function (error) {
           console.log(error);
         }) 
    },

    updatePeopleName({commit},data){
        commit('updatePeopleName',data)
    },

    updateLoginForm({commit},data){
        commit('updateLoginForm',data)
    }
    
}

//创建并暴露store
export default new Vuex.Store({
    actions,
    mutations,
    state,
    plugins: [createVuexAlong()]
})