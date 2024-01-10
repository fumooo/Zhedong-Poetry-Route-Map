<template>
  <div>
    <div style="margin-bottom:15px">
      <el-button type="primary" @click="addVisible = true"><i class="el-icon-plus"></i>新增</el-button>
      <el-button type="primary" @click="deleteMultipleCity"><i class="el-icon-plus"></i>批量删除</el-button>
      <el-button type="primary"  @click="refreshInput" style="margin-right: 60px;"><i class="el-icon-refresh-right"></i>重置</el-button>
      <!-- <el-button type="primary" @click="importCity" style="margin-right:100px"><i class="el-icon-plus"></i>导入</el-button> -->
      <el-upload
        class="upload-demo"
        action="http://localhost:8088/City/upload"
        :show-file-list="false"
        accept="xlsx"
        :on-success="handleImportSuccess"
        >
        <el-button size="small" type="primary">导入</el-button>
      </el-upload>
      城市搜索： <el-autocomplete @change="CitySearch"
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
      ></el-autocomplete> 
    </div>
    
    <!-- 分页查询的表格 -->
    <el-table :data="tableData" border=""
      @selection-change="handleSelectionChange">
      <el-table-column type="selection"> </el-table-column>
      <el-table-column prop="id" label="id" align="center"> </el-table-column>
      <el-table-column prop="cname" label="城市名称" align="center"> </el-table-column>
      <el-table-column prop="lng" label="经度" align="center"> </el-table-column>
      <el-table-column prop="lat" label="纬度" align="center"> </el-table-column>
      <el-table-column prop="province" label="省份" align="center"> </el-table-column>
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
            <el-form-item label="城市名称">
                <el-input v-model="form.cname"></el-input>
            </el-form-item>
            <el-form-item label="城市经度">
                <el-input v-model="form.lng"></el-input>
            </el-form-item>
            <el-form-item label="城市维度">
                <el-input v-model="form.lat"></el-input>
            </el-form-item>
            <el-form-item label="省份">
                <el-input v-model="form.province"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addCity">添加</el-button>
                <el-button @click="clearInput">重置</el-button>
            </el-form-item>
       </el-form>
    </el-dialog>

    <!--更新的按钮-->
    <el-dialog title="更新城市信息" :visible.sync="updateVisible" width="30%" @close="reset">
       <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="城市名称">
                <el-input v-model="form.cname"></el-input>
            </el-form-item>
            <el-form-item label="城市经度">
                <el-input v-model="form.lng"></el-input>
            </el-form-item>
            <el-form-item label="城市维度">
                <el-input v-model="form.lat"></el-input>
            </el-form-item>
            <el-form-item label="省份">
                <el-input v-model="form.province"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="updateCity">保存</el-button>
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
    this.queryCity()
    //查询cname搜索框
    this.$http.get("queryCity")
      .then(response=>{
        for(let i = 0; i < response.data.length; i++){
          response.data[i].value = response.data[i].cname
          // this.autoCityNameSearch[i].value = response.data[i].cname
        }
        this.autoCityNameSearch = response.data
        // console.log(this.autoCityNameSearch)
      })
      .catch(function(error){
        console.log(error)
      })
      //查询province搜索框
    this.$http.get("queryProvince")
      .then(response=>{
        for(let i = 0; i < response.data.length; i++){
          response.data[i].value = response.data[i].province
        }
      this.autoProvinceSearch = response.data
      // console.log(this.autoProvinceSearch)
    })
    .catch(function(error){
      console.log(error)
    })
  },
  
  data() {
    return {
      tableData : [],
      currentPage : 1,//当前的
      count : 0,//分页查询页面总数
      pageNum : 1,//
      pageSize : 100,//默认的分页大小
      CityNameSearch : "",
      ProvinceNameSearch : "",
      autoCityNameSearch : [],
      autoProvinceSearch : [],
      addVisible : false,//添加的显示框
      updateVisible : false,//更新的显示框
      form: {
                cname: '', // 城市名
                lng: '', //经度
                lat: '', //纬度
                province: '',//省份   
            },
      multipleSelection : [],
    }
  },
  methods:{
    //将分页查询的axios分装在方法中
    queryCity(){
      this.$http.get("queryCityPage",{
      params:{
        pageNum : this.pageNum,
        pageSize : this.pageSize,
        cname : this.CityNameSearch,
        province : this.ProvinceNameSearch,
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
    refreshInput(){
      this.CityNameSearch = ""
      this.ProvinceNameSearch = ""
      this.queryCity()
    },

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
          this.$http.delete("/City/" + row.id).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.queryCity()
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
          this.$http.post("/City/batch",ids).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.queryCity()
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
      this.queryCity()
    },

    //监听pageSize的
    handleSizeChange(pageSize){
      // console.log(pageSize);
      this.pageSize = pageSize
      this.queryCity()
    },

    // 监听CityNameSearch
    CitySearch(){
      this.queryCity()
    },

    // 监听CityProvinceSearch
    ProvinceSearch(){
      this.queryCity()
    },

    //两个el-autocomplete的选择
    handleSelect1(item) {
        console.log(item.value);
    },
    querySearch1(queryString,cb){
       var results = queryString ? this.autoCityNameSearch.filter(this.createFilter(queryString)) : this.autoCityNameSearch;
       cb(results);
    },
    handleSelect2(item) {
        console.log(item.value);
    },
    querySearch2(queryString,cb){
       var results = queryString ? this.autoProvinceSearch.filter(this.createFilter(queryString)) : this.autoProvinceSearch;
       cb(results);
    },
    createFilter(queryString) {
      return (item) => {
        return item.value.toUpperCase().match(queryString.toUpperCase());
      };
    },

    //添加窗体的操作
    //清空
    clearInput(){
            this.form.cname = ''
            this.form.lng = ''
            this.form.lat = ''
            this.form.province = ''
        },
    //添加和更新城市的函数
    saveCity(){
      
      this.$http.post("saveCity",this.form)
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
        this.queryCity()
        
    },
    //添加城市信息
    addCity(){
      
      this.saveCity()
      this.queryCity()
    },
    //更新城市信息
    updateCity(){
      
      this.saveCity()
      this.queryCity()
      
    },
    //清空form中信息
    clearForm(){
      this.form.id = Number,
      this.form.cname = "",
      this.form.lat = "",
      this.form.lng = "",
      this.form.province = ""
    },
    reset(){
        this.clearForm()
        this.queryCity()
      },
    importCity(){
      console.log(111)
    },
    handleImportSuccess(){
      this.$message.success("导入成功")
      this.queryCity()
    }
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