<template>
  <div>
    <el-menu :default-openeds="['1']" 
    background-color="rgb(48,65,86)" 
    text-color="#fff"
    active-text-color="#ffd04b"
    :collapse-transition="false"
    
    >
      <el-submenu index="1">
        <template slot="title"
          ><i class="el-icon-setting"></i>后台管理</template
        >
        <!-- <span>必要信息</span> -->
        <el-menu-item-group title="">
          <!-- <template slot="title">必要信息</template> -->
          <el-menu-item index="1-1" @click="changeRouter1">城市管理</el-menu-item>
          <el-menu-item index="1-2" @click="changeRouter2">诗人信息管理</el-menu-item>
          <el-menu-item index="1-3">诗人访问景点管理</el-menu-item>
          <el-menu-item index="1-4">诗歌分组管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
    
  </div>
</template>

<script>
export default {
  name: "leftnavB",
  data() {
    return {
      isCollapse:true
    };
  },
  methods:{
    changeRouter1(){
      this.$router.push({path: '/backIndex/CityManage'})
    },
    changeRouter2(){
      this.$router.push({path: '/backIndex/PeopleManage'})
    },
  }
};
</script>

<style scoped>
</style>