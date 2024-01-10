<template>
<div>
  <el-row>
    <el-col :span="8"><div style="text-align:center;line-height:40px">查询：</div></el-col>
    <el-col :span="16">
      <div class="">
        <el-input v-model="search" placeholder="诗人名" clearable @input="submit"></el-input>
      </div>
    </el-col>
  </el-row>

  <el-checkbox-group v-model="$store.state.poemList" @change="handlePoemChange" :min="0"
              :max="4">
    <el-checkbox v-for="checklist in $store.state.checkListDate" :key="checklist.id" :label="checklist.id">
      {{checklist.name}}({{checklist.birthDate}}-{{checklist.deathDate}})
    </el-checkbox>
  </el-checkbox-group>
</div>
</template>

<script>
import { computed } from 'vue';

export default {
  name: "leftnav",
  created:function(){ 
    this.$store.dispatch('refresh')
    
  },
  data() {
    return {
      search: "",
    };
  },
  methods:{
    //提交查询诗人功能
    submit(){
      // alert(this.search)
      this.$store.dispatch('updateLeftNav',this.search)
    },
    //当多选框改变时
    handlePoemChange(){
      // console.log(this.$store.state.poemList)
    }
  },
  computed:{
    
  }
};

</script>

<style>
.el-checkbox{
  display: none;
}
.el-row {
  margin-bottom: 20px;
}
.el-row :last-child {
    margin-bottom: 0;
  }
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
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
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-checkbox{
  text-align: center;
}
</style>