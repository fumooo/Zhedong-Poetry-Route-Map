<template>
  <div class="header">
    <el-row>
      <el-col align="middle" :span="20"><div style="font-size:40px; height:100%; line-height:100px; font-family:Helvetica Neue;">浙东唐诗之路可视化系统</div></el-col>
      <el-col :span="4">
        <div style="height:100%; line-height:100px;">
          <el-button round @click="clickToBack">后台管理</el-button>
        </div>
      </el-col>
    </el-row>
    <!-- <span>浙东唐诗之路</span>
    <el-button round @click="clickToBack">后台管理</el-button> -->
  </div>
</template>

<script>
export default {
  name: "pheader",
  data() {
    return {
    };
  },
  methods:{
    //跳转后台
    clickToBack(){
      console.log(this.$store.state.account)
      if(this.$store.state.account == "" ){
        this.$router.push({path: '/login'})
      }
      else {
        this.$router.push({path: '/backIndex/CityManage'})
      }
    }
    
  }
};
</script>

<style scoped>
.el-row {
  height: 100%;
  margin-bottom: 20px;
  /* background: #f9fafc; */
}
.el-row :last-child {

    margin-bottom: 0;
}
.el-col {
  height: 100px;
  border-radius: 4px;
}

.bg-purple-dark {
  background: #e0e4e8;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  
  background-color: #f9fafc;
}
.header{
  height: 100%;
  vertical-align: middle;
}
</style>