<template>
    <div>
      <div style="margin-bottom:15px">
        <el-button type="primary" @click="addVisible = true"><i class="el-icon-plus"></i>新增</el-button>
        <el-button type="primary" @click="deleteMultipleCity"><i class="el-icon-plus"></i>批量删除</el-button>
        <!-- <el-button type="primary" style="margin-right:100px" @click="refreshInput"><i class="el-icon-refresh-right"></i>重置</el-button> -->
        
        <!-- 城市搜索： <el-autocomplete @change="CitySearch"
          class="inline-input"
          v-model="CityNameSearch"
          :fetch-suggestions="querySearch1"
          placeholder="请输入城市"
          @select="handleSelect1"
          :trigger-on-focus="false"
        ></el-autocomplete>
        省份搜索：<el-autocomplete @change="ProvinceSearch"
          class="inline-input"
          v-model="ProvinceNameSearch"
          :fetch-suggestions="querySearch2"
          placeholder="请输入省份"
          @select="handleSelect2"
          :trigger-on-focus="false"
        ></el-autocomplete>  -->
      </div>
      
      <!-- 分页查询的表格 -->
      <el-table :data="tableData" border=""
        @selection-change="handleSelectionChange">
        <el-table-column type="selection"> </el-table-column>
        <el-table-column prop="id" label="id" align="center"> </el-table-column>
        <el-table-column prop="account" label="账号" align="center"> </el-table-column>
        <el-table-column prop="password" label="密码" align="center"> </el-table-column>
        
        <el-table-column align="center"  label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
      </el-table-column>
      </el-table>
  
      <!-- 分页查询的 -->
      <div class="block" style="margin-top:15px">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="100"
          :page-sizes="[2, 5, 8, 100]"   
          layout="total, sizes, prev, pager, next, jumper"
          :total="count">
        </el-pagination>
      </div>
  
      <!--添加按钮的代码-->
      <el-dialog title="添加城市信息" :visible.sync="addVisible" width="30%" @close="reset">
         <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="账号">
                  <el-input v-model="form.account"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                  <el-input v-model="form.password"></el-input>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" @click="addAdmin">添加</el-button>
                  <el-button @click="clearInput">重置</el-button>
              </el-form-item>
         </el-form>
      </el-dialog>
  
      <!--更新的按钮-->
      <el-dialog title="更新城市信息" :visible.sync="updateVisible" width="30%" @close="reset">
         <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="账号">
                  <el-input v-model="form.account"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                  <el-input v-model="form.password"></el-input>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" @click="updateAdmin">保存</el-button>
                  <!-- <el-button @click="clearInput">重置</el-button> -->
              </el-form-item>
         </el-form>
      </el-dialog>
  
    </div>
  </template>
  
  <script>
  
  export default {
    name: "CityManage",
    components:{
    },
    mounted:function(){
      this.queryAdmin()
      
    },
    
    data() {
      return {
        tableData : [],
        currentPage : 1,//当前的
        count : 0,//分页查询页面总数
        pageNum : 1,//
        pageSize : 100,//默认的分页大小
        addVisible : false,//添加的显示框
        updateVisible : false,//更新的显示框
        form: {
                  account: '', // 账号
                  password: '', //密码
              },
        multipleSelection : [],
      }
    },
    methods:{
      //将分页查询的axios分装在方法中
      queryAdmin(){
        this.$http.get("queryAdminPage",{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
        }
      })
      .then(response=>{
        this.tableData = response.data.data
        this.count = response.data.count
      })
      .catch(function(error){
        console.log(error)
      })
      },
  
      // 重置两个搜索框

  
      //监听操作按钮
      handleEdit(index, row) {
        this.updateVisible = true
        this.form = row
        // console.log(index, row);
      },
      //删除操作
      //删除按钮
      handleDelete(index, row) {
        // console.log(row.id);
         this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete("/Admin/" + row.id).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryAdmin()
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });          
          });
      },
      //批量删除
      handleSelectionChange(val){
        this.multipleSelection = val
      },
      deleteMultipleCity(){
        let ids = this.multipleSelection.map(v => v.id)
        this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post("/Admin/batch",ids).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryAdmin()
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });          
          });
      },
      //监听pageNum
       handleCurrentChange(pageNum){
        // console.log(pageNum);
        this.pageNum =pageNum
        this.queryAdmin()
      },
  
      //监听pageSize的
      handleSizeChange(pageSize){
        // console.log(pageSize);
        this.pageSize = pageSize
        this.queryAdmin()
      },
  
      //添加窗体的操作
      //清空
      clearInput(){
              this.form.account = ''
              this.form.password = ''
          },
      //添加和更新城市的函数
      saveAdmin(){
        this.$http.post("saveAdmin",this.form)
              .then(res => {
                  if(res){
                    this.$message.success('保存成功');
                    this.addVisible = false
                    this.updateVisible = false                 
                  }
                  else{
                    this.$message.error("保存失败")
                  }
              })
              .catch(function (error) {
                  console.log(error);
              })
          this.queryAdmin()
          
      },
      //添加城市信息
      addAdmin(){
        this.saveAdmin()
        this.queryAdmin()
      },
      //更新城市信息
      updateAdmin(){
        this.saveAdmin()
        this.queryAdmin()
        
      },
      //清空form中信息
      clearForm(){
        this.form.id = Number,
        this.form.account = "",
        this.form.password = ""
      },
      reset(){
        this.clearForm()
        this.queryAdmin()
      },
    }
  };
  </script>
  
  <style>
  .el-pagination{
    text-align: center;
  }
  .el-autocomplete{
    width: 120px;
  }
  </style>