<template>
  <div>
    <el-container style="height: 580px; border: 1px solid #eee">
      <el-aside width="sideWidth + 'px'" >
        <el-menu
          :default-openeds="['1']"
          background-color="rgb(48,65,86)"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse-transition="false"
          :collapse = "isCollapse"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-setting"></i></template>
              <span slot="title">后台管理</span>
            <!-- <span>必要信息</span> -->
            <el-menu-item-group title="">
              <!-- <template slot="title">必要信息</template> -->
              <el-menu-item index="1-1" @click="changeRouter1">城市管理</el-menu-item>
              <el-menu-item index="1-2" @click="changeRouter2">诗人信息管理</el-menu-item>
              <el-menu-item index="1-3" @click="changeRouter3">诗人访问景点管理</el-menu-item>
              <!-- <el-menu-item index="1-4">诗歌分组管理</el-menu-item> -->
              <el-menu-item index="1-5" @click="changeRouter5">诗人经历诗歌管理</el-menu-item>
              <el-menu-item index="1-6" @click="changeRouter6">管理员信息管理</el-menu-item>
              
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <!--后台头-->
        <el-header
          style="font-size: 12px, border-bottom: 1px solid #ccc; line-height: 60px; display: flex"
        >
          <div style="flex: 1; font-size: 18px">
            <span style="cursor: pointer" @click="collapse"
              ><i class="el-icon-s-fold" style="margin-right: 15px"></i
            ></span>
          </div>

          <!-- <div style="height:100%;">
            <el-button round @click="clickToBack">后台管理</el-button>
          </div> -->

          <el-dropdown>
            <span>{{$store.state.account}}</span>
            <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="ChangePassword">更换密码</el-dropdown-item>
              <el-dropdown-item @click.native="LoggingOut">退出登陆</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

       
        <!-- 后台主页面 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    
  </div>
</template>

<script>
import leftnavB from "./LeftNav/LeftNavB.vue";

import CityManage from "./RouterMain/City/CityManage.vue";

export default {
  components: {
    leftnavB,
    CityManage,
  },
  name: "backIndex",
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄",
 
    };
    return {
      isCollapse: false,
      sideWidth: 200,
    };
    
  },
  methods: {
    changeRouter1(){
      this.$router.push({path: '/backIndex/CityManage'})
    },
    changeRouter2(){
      this.$router.push({path: '/backIndex/PeopleManage'})
    },
    changeRouter3(){
      this.$router.push({path: '/backIndex/SceneryManage'})
    },
    changeRouter5(){
      this.$router.push({path: '/backIndex/PeoplePoemManage'}) 
    },
    changeRouter6(){
      this.$router.push({path: '/backIndex/AdminManage'}) 
    },
    ChangePassword(){
      this.$router.push({path: '/backIndex/Password'}) 
    },
  
    collapse() {//点击按钮后收纳aside
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 64
      }
    },
    LoggingOut(){
      
      // this.$store.dispatch('updateLoginForm',res.data)
      clearVuexAlong()
      window.location.href= "/login"
      // this.$router.push({path: '/login'})
      console.log(this.$store.state.account)
    }
  },
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
  background-color: rgb(48, 65, 86);
}
</style>