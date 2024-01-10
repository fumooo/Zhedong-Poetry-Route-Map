<template>
  <div>
    <el-container>
      
      
        <!-- 主页面查询 -->
        <el-main>
             <div style="margin-bottom:15px">
              <!-- 选择器 选择诗人 -->
              请选择诗人
              <el-select v-model="value" value-key="name" filterable placeholder="请选择" @change="displayDiv">
                <el-option
                  v-for="item in peopleName"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
                  >
                </el-option>
              </el-select>
                <el-button type="primary" style="margin-left:100px" @click="addButton"><i class="el-icon-plus"></i>新增</el-button>
                <el-button type="primary" @click="deleteButton"><i class="el-icon-plus"></i>批量删除</el-button>
                <!-- <el-button type="primary" @click="deleteButton"><i class="el-icon-plus"></i>批量添加</el-button> -->
                <!-- <el-button type="primary" style="margin-right:10px" @click="refreshInput"><i class="el-icon-refresh-right"></i>重置</el-button> -->
                
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
              <el-table :data="tableData"
              border
                @selection-change="handleSelectionChange">
                <el-table-column type="selection"> </el-table-column>
                <el-table-column prop="id" label="id" align="center"> </el-table-column>
                <el-table-column prop="name" label="景点名称" align="center"> </el-table-column>
                <el-table-column prop="introduction" label="景点介绍" align="center" :show-overflow-tooltip="true"> </el-table-column>
                
                <el-table-column label="图片" align="center"> 
                  <template width="40" slot-scope="scope">
                    <el-image 
                      v-if="scope.row.picture != '' "
                      :src="require('@/assets/image/' + scope.row.picture)"
                      style="width: 100px; height: 100px">
                    </el-image>
                  </template>
                  <!-- <img v-if="form.picture != '' " class="img" alt="example" :src="require(`@/assets/image/${form.picture}`) " width="20%"> -->
                </el-table-column>

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
              <el-dialog title="添加城市景点信息" :visible.sync="addVisible" width="50%" @close="reset">
                <el-form ref="form" :model="form" label-width="80px">
                      <el-form-item label="景点名称">
                          <el-input v-model="form.name"></el-input>
                      </el-form-item>
                      <!-- <el-form-item label="景点名称">
                          <el-input v-model="form.peopleId"></el-input>
                      </el-form-item> -->
                      <el-form-item label="景点介绍">
                        <el-input :rows="5" type="textarea" v-model="form.introduction"></el-input>
                      </el-form-item>
                      
                      <!-- 上传照片 :on-success="handle_success" -->
                      <div style="margin-left:80px">
                        上传景点图片限一张
                        <el-upload 
                          action="http://localhost:8088/file/upload"
                          :on-success="handle_success"
                          list-type="picture-card"
                          :on-preview="handlePictureCardPreview"
                          :on-remove="handleRemove"
                          :limit="1"
                          >
                          <div slot="tip" class="el-upload__tip">
                              只能上传jpg/png文件，且不超过500kb
                          </div>
                          <i class="el-icon-plus"></i>
                        </el-upload>
                        <el-dialog :visible.sync="dialogVisible">
                          <img width="100%" :src="dialogImageUrl" alt="">
                        </el-dialog>
                      </div>
                      
                      
                      <el-form-item>
                          <el-button type="primary" @click="addScenery">添加</el-button>
                          <!-- <el-button @click="clearInput">重置</el-button> -->
                      </el-form-item>
                </el-form>
              </el-dialog>

              <!--更新的按钮-->
              <el-dialog title="更新城市信息" :visible.sync="updateVisible" width="50%" @close="reset" >
                <el-form ref="form" :model="form" label-width="80px">
                      <el-form-item label="景点名称">
                          <el-input v-model="form.name"></el-input>
                      </el-form-item>
                      <el-form-item label="景点介绍">
                        <el-input :rows="5" type="textarea" v-model="form.introduction"></el-input>
                      </el-form-item>
                      
                      <div style="margin-left:80px">
                        修改景点图片限一张
                        <el-upload 
                          action="http://localhost:8088/file/upload"
                          :on-success="handle_success"
                          list-type="picture-card"
                          :on-preview="handlePictureCardPreview"
                          :on-remove="handleRemove"
                          :limit="1"
                          >
                          <div slot="tip" class="el-upload__tip">
                              只能上传jpg/png文件，且不超过500kb
                          </div>
                          <i class="el-icon-plus"></i>
                        </el-upload>

                        <el-dialog :visible.sync="dialogVisible">
                          <img width="100%" :src="dialogImageUrl" alt="">
                        </el-dialog>
                      </div>
                    
                      <el-form-item>
                          <el-button type="primary" @click="updateScenery">保存</el-button>
                          <!-- <el-button @click="clearInput">重置</el-button> -->
                      </el-form-item>
                </el-form>
              </el-dialog>
                  </el-main>
              </el-container>

  </div>
</template>

<script>

export default {
  name: "CityManage",
  components:{
  },
  mounted:function(){
    // 查询左侧的一个诗人栏
    this.queryPeople()

    //一开始先进行一次查询表格
    // this.querySceneryByPeopleName()

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
      peopleName : [],//对选框中的诗人姓名
      search : "",//搜索框的模糊查询
      value : "",//选择器中的值

      
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
                peopleId : Number,//诗人外键
                name: '', // 城市名
                introduction: '', //介绍
                picture: '', //图片
            },
      multipleSelection : [],

      dialogImageUrl: '',
      dialogVisible: false,
      imgUrl : '', 
      disabled: false,
      test : '1683094157750.png',

      peopleId : 1,
      
    }
  },
  methods:{
    //选择器改变时 
    displayDiv(val){
      this.form.peopleId = this.value.id
      // console.log(this.form)
      this.querySceneryByPeopleName()
    },
    //查询诗人信息
    queryPeople(){
      this.$http.get("people")
      .then(response=>{
        this.peopleName = response.data
      })
      .catch(function(error){
        console.log(error)
    })
    },
    

    //将分页查询的axios分装在方法中
    querySceneryByPeopleName(){
      this.$http.get("querySceneryByPeopleNamePage",{
      params:{
        pageNum : this.pageNum,
        pageSize : this.pageSize,
        name : this.value.name,
        
      }
    })
    .then(response=>{
      this.tableData = response.data.data
      this.count = response.data.count
      // console.log(this.tableData)
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
          this.$http.delete("/Scenery/" + row.id).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.querySceneryByPeopleName()
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
          this.$http.post("/Scenery/batch",ids).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.querySceneryByPeopleName()
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
      this.querySceneryByPeopleName()
    },

    //监听pageSize的
    handleSizeChange(pageSize){
      // console.log(pageSize);
      this.pageSize = pageSize
      this.querySceneryByPeopleName()
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
            this.form.name = ''
            this.form.introduction = ''
        },
    //添加和更新城市的函数
    saveScenery(){
      // this.form.peopleId = 
      this.$http.post("saveScenery",this.form)
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
        this.querySceneryByPeopleName()
        
    },
    //添加城市信息
    addScenery(){  
      this.saveScenery()
      this.querySceneryByPeopleName()
      this.clearForm()
    },
    //删除图片
    handleRemove(file) {
      console.log(file);
    },
      //放大看图片函数
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    

    //更新城市信息
    updateScenery(){
      
      this.saveScenery()
      this.querySceneryByPeopleName()
      this.clearForm()
    },
    //清空form中信息
    clearForm(){
      this.form.id = null,
      this.form.peopleId = this.value.id,
      this.form.name = "",
      this.form.introduction = "",
      this.form.picture = ""
    },
    // diolag点击叉叉后
    reset(){
        this.clearForm()
        this.querySceneryByPeopleName()
    },
    // 新增的按钮
    addButton(){
      console.log(this.value)
      if(this.value.id == null){
        this.$message('请先选择诗人');
      }
      else{
        this.addVisible = true
      }
    },
    //批量删除按钮
    deleteButton(){
      if(this.value.id == null){
        this.$message('请先选择诗人');
      }
      else{
        this.deleteMultipleCity()
      }
    },
    //上传图片
    handle_success(res) {
        console.log(res.data);
        this.$message.success("图片上传成功");
        this.form.picture = res.data
        
      //   return (this.form.imgUrl = res.data);
      },
  },
  watched : {
    
  }
};
</script>

<style scoped>

.el-pagination{
  text-align: center;
}
.el-autocomplete{
  width: 120px;
}
</style>