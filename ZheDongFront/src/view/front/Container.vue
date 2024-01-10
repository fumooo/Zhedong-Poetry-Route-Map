<template>
  <el-container style="border: 0px solid red">
    <el-header height="100px">
      <pheader></pheader>
    </el-header>
    <el-container>
      <el-aside width="175px" >
        <leftnav></leftnav>
      </el-aside>
      <el-container>
        <el-main>
          <baidu></baidu>
        </el-main>
      </el-container>
      <el-aside width="300px" style="margin: 3px;">
        <righttext></righttext>
      </el-aside>
    </el-container>
    <!-- <el-footer>Tough task</el-footer> -->
  </el-container>
</template>

<script>
import leftnav from "../front/LeftNav.vue";
import baidu from "../front/BaiduMap.vue";
import righttext from "../front/RightText.vue"
import pheader from "../front/Header.vue"
export default {

  name: "container",
  components: {
    leftnav,
    baidu,
    righttext,
    pheader,
  },
};
</script>

<style scoped>
.el-header{
  background-color: rgb(244, 245, 241);
  color: #333;
  text-align: center;
  line-height: 60px;
  padding : 0;
}
.el-footer {
  background-color: #f2f6ea;
  color: #333;
  text-align: center;
  line-height: 60px;
  padding : 0;
}

.el-aside {
  color: #333;
  line-height: 30px;
  height: 550px;
}

.el-main {
  background-color: white;
  color: #333;
  text-align: center;
  line-height: 160px;
  padding : 0;
}

body > .el-container {
  margin-bottom: 40px;
  
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

</style>