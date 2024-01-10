<template>
  <div>
    <div style="margin-bottom:15px">
      <el-button type="primary" @click="addVisible = true"><i class="el-icon-plus"></i>新增</el-button>
      <el-button type="primary" @click="deleteMultiplePeople"><i class="el-icon-plus"></i>批量删除</el-button>
      <el-button type="primary" @click="refreshInput" style="margin-right: 60px;"><i class="el-icon-refresh-right"></i>重置</el-button>
      <el-upload
        class="upload-demo"
        action="http://localhost:8088/People/upload"
        :show-file-list="false"
        accept="xlsx"
        :on-success="handleImportSuccess"
        >
        <el-button size="small" type="primary">导入</el-button>
      </el-upload>

      诗人搜索： <el-autocomplete @change="PeopleSearch"
        class="inline-input"
        v-model="PeopleNameSearch"
        :fetch-suggestions="querySearch1"
        placeholder="请输入姓名"
        @select="handleSelect1"
        :trigger-on-focus="false"
      ></el-autocomplete>
      
    </div>
    
    <!-- 分页查询的表格 -->
    <el-table :data="tableData"
      border
      @selection-change="handleSelectionChange">
      <el-table-column type="selection"> </el-table-column>
      <el-table-column prop="id" label="id" align="center" min-width="46%"> </el-table-column>
      <el-table-column prop="name" label="诗人姓名" align="center"> </el-table-column>
      <el-table-column prop="birthDate" label="出生日期" align="center"> </el-table-column>
      <el-table-column prop="deathDate" label="死亡日期" align="center"> </el-table-column>
      <el-table-column prop="introduction" label="诗人介绍" align="center" :show-overflow-tooltip="true"> </el-table-column>
      
      <el-table-column label="图片" align="center"> 
        <template width="40" slot-scope="scope">
          <el-image 
            v-if="scope.row.pictureP != '' && scope.row.pictureP != null"
            
            :src="require('@/assets/image/' + scope.row.pictureP)"
            style="width: 100px; height: 100px">
          </el-image>
        </template>
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
    <el-dialog title="添加诗人信息" :visible.sync="addVisible" width="50%" @close="reset">
       <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="诗人姓名">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="出生日期">
                <el-input v-model="form.birthDate"></el-input>
            </el-form-item>
            <el-form-item label="死亡日期">
                <el-input v-model="form.deathDate"></el-input>
            </el-form-item>
            <el-form-item label="诗人简介">
                <el-input :rows="5" type="textarea" v-model="form.introduction"></el-input>
            </el-form-item>
            <!-- 上传照片 :on-success="handle_success" -->
            <div style="margin-left:80px">
                        上传诗人图片限一张
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
                <el-button type="primary" @click="addPeople">添加</el-button>
                <el-button @click="clearInput">重置</el-button>
            </el-form-item>
       </el-form>
    </el-dialog>

    <!--更新的按钮-->
    <el-dialog title="更新诗人信息" :visible.sync="updateVisible" width="50%" @close="reset">
       <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="诗人姓名">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="出生日期">
                <el-input v-model="form.birthDate"></el-input>
            </el-form-item>
            <el-form-item label="死亡日期">
                <el-input v-model="form.deathDate"></el-input>
            </el-form-item>
            <el-form-item label="诗人简介">
                <el-input :rows="5" type="textarea" v-model="form.introduction"></el-input>
            </el-form-item>
            <div style="margin-left:80px" >
                        修改诗人图片限一张
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
                <el-button type="primary" @click="updatePeople">保存</el-button>
                <!-- <el-button @click="clearInput">重置</el-button> -->
            </el-form-item>
       </el-form>
    </el-dialog>

  </div>
</template>

<script>

export default {
  name: "PeopleManage",
  components:{
  },
  mounted:function(){
    this.queryPeople()
    //查询cname搜索框
    this.$http.get("people")
      .then(response=>{
        for(let i = 0; i < response.data.length; i++){
          response.data[i].value = response.data[i].name
          // this.autoCityNameSearch[i].value = response.data[i].cname
        }
        this.autoPeopleNameSearch = response.data
        // console.log(this.autoCityNameSearch)
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
      PeopleNameSearch : "",
      
      autoPeopleNameSearch : [],
      
      addVisible : false,//添加的显示框
      updateVisible : false,//更新的显示框
      form: {
                name: '', // 城市名
                birthDate: '', //经度
                deathDate: '', //纬度
                introduction: '',//省份   
                pictureP: ''//图片目录
            },
      multipleSelection : [],
      dialogImageUrl: '',
      dialogVisible: false,
    }
  },
  methods:{
    //将分页查询的axios分装在方法中
    queryPeople(){
      this.$http.get("queryPeoplePage",{
      params:{
        pageNum : this.pageNum,
        pageSize : this.pageSize,
        name : this.PeopleNameSearch
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
      this.PeopleNameSearch = ""
      this.queryPeople()
    },

    //监听操作按钮
    handleEdit(index, row) {
      this.updateVisible = true
      this.form = row
      // console.log(index, row);
    },
    //删除操作
    //单个删除
    handleDelete(index, row) {
      // console.log(row.id);
       this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.delete("/People/" + row.id).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.queryPeople()
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
    deleteMultiplePeople(){
      let ids = this.multipleSelection.map(v => v.id)
      this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post("/People/batch",ids).then(res=>{
            if(res){
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
              this.queryPeople()
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
      this.queryPeople()
    },

    //监听pageSize的
    handleSizeChange(pageSize){
      // console.log(pageSize);
      this.pageSize = pageSize
      this.queryPeople()
    },

    // 监听PeopleNameSearch
    PeopleSearch(){
      this.queryPeople()
    },


    //两个el-autocomplete的选择
    handleSelect1(item) {
        console.log(item.value);
    },
    querySearch1(queryString,cb){
       var results = queryString ? this.autoPeopleNameSearch.filter(this.createFilter(queryString)) : this.autoPeopleNameSearch;
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
            this.form.birthDate = ''
            this.form.deathDate = ''
            this.form.introduction = ''
        },
    //添加和更新城市的函数
    savePeople(form){
      this.$http.post("savePeople",form)
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
    },
    //添加城市信息
    addPeople(){
      this.savePeople(this.form)
      this.queryPeople()
    },
    //更新城市信息
    updatePeople(){
      this.savePeople(this.form)
      this.queryPeople()
    },
    clearForm(){
      this.form.id = Number
      this.form.name = ''
      this.form.birthDate = ''
      this.form.deathDate = ''
      this.form.introduction = ''
      this.form.pictureP = ''
    },
    reset(){
      this.clearForm()
      this.queryPeople()
    },
    handle_success(res) {
        console.log(res.data);
        this.$message.success("图片上传成功");
        this.form.pictureP = res.data
      //   return (this.form.imgUrl = res.data);
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
    handleImportSuccess(){
      this.$message.success("导入成功")
      this.queryPeople()
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
.upload-demo{
  display: inline-block;
  
}
</style>