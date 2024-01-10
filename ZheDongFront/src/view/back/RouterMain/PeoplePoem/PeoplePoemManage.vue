<template>
    <div>
      <el-container>
          <!-- 主页面查询 -->
          <el-main>
               <div style="margin-bottom:15px">
                <!-- 选择器 选择诗人 -->
                请选择诗人
                <el-select v-model="PeopleValue" value-key="name" filterable placeholder="请选择" @change="displayDiv1" clearable>
                  <el-option
                    v-for="item in peopleName"
                    :key="item.id"
                    :label="item.name"
                    :value="item"
                    >
                  </el-option>
                </el-select>

                请选择城市
                <!-- <el-cascader ref="cascaderAddr" v-model="CityValue" :options="options" :show-all-levels="false" clearable @change="displayDiv2" ></el-cascader> -->
                <el-select v-model="CityValue" value-key="cname" filterable placeholder="请选择" @change="displayDiv2" clearable>
                  <el-option
                    v-for="item in cityName"
                    :key="item.id"
                    :label="item.cname"
                    :value="item"
                    >
                  </el-option>
                </el-select>
                <br>

                请选择组选
                <el-select v-model="GroupValue" value-key="id" filterable placeholder="请选择" @change="displayDiv3" clearable>
                  <el-option
                    v-for="item in groupName"
                    :key="item.id"
                    :label="change(item)"
                    :value="item"
                    >
                  </el-option>
                </el-select>
                请选择经历
                <el-select v-model="ExperienceValue" value-key="id" filterable placeholder="请选择" @change="displayDiv4" clearable>
                  <el-option
                    v-for="item in experienceName"
                    :key="item.id"
                    :label="changeP(item)"
                    :value="item"
                    >
                  </el-option>
                </el-select>
                  <div style="margin-top:10px" v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id == undefined && this.ExperienceValue.id == undefined">
                    <el-button type="primary" style="margin-left:20px" @click="addButtonG"><i class="el-icon-plus"></i>新增</el-button>
                    <el-button type="primary" @click="deleteButtonG"><i class="el-icon-plus"></i>批量删除</el-button>
                  </div>
                  <div style="margin-top:10px" v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id != undefined && this.ExperienceValue.id == undefined">
                    <el-button type="primary" style="margin-left:20px" @click="addButtonE"><i class="el-icon-plus"></i>新增</el-button>
                    <el-button type="primary" @click="deleteButtonE"><i class="el-icon-plus"></i>批量删除</el-button>
                  </div>
                  <div style="margin-top:10px" v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id != undefined && this.ExperienceValue.id != undefined">
                    <el-button type="primary" style="margin-left:20px" @click="addButtonP"><i class="el-icon-plus"></i>新增</el-button>
                    <el-button type="primary" @click="deleteButtonP"><i class="el-icon-plus"></i>批量删除</el-button>
                  </div>
                </div>
              <!-- Group---------------------------------------------------------------------------------------- -->
              <div v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id == undefined && this.ExperienceValue.id == undefined">
                <!-- 分页查询的表格 G -->
                <el-table :data="GroupData" 
                border
                  @selection-change="handleSelectionChangeG">
                  <el-table-column type="selection"> </el-table-column>
                  <el-table-column prop="id" label="id" align="center"> </el-table-column>
                  <el-table-column prop="beginYear" label="开始年份" align="center"> </el-table-column>
                  <el-table-column prop="endYear" label="结束年份" align="center" :show-overflow-tooltip="true"> </el-table-column>
                  <el-table-column align="center"  label="操作">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        @click="handleEditG(scope.$index, scope.row)">编辑</el-button>
                      <el-button
                        size="mini"
                        type="danger"
                        @click="handleDeleteG(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
  
                <!-- 分页查询的 G -->
                <div class="block" style="margin-top:15px">
                  <el-pagination
                    @size-change="handleSizeChangeG"
                    @current-change="handleCurrentChangeG"
                    :current-page="currentPageG"
                    :page-size="100"
                    :page-sizes="[2, 5, 8, 100]"   
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="countG">
                  </el-pagination>
                </div>
              </div>
                <!--添加按钮的代码 G-->
                <el-dialog title="添加诗人城市组" :visible.sync="addVisibleG" width="50%" @close="resetG">
                  <el-form ref="formG" :model="formG" label-width="80px">
                        <el-form-item label="开始年份">
                            <el-input v-model="formG.beginYear"></el-input>
                        </el-form-item>
                        <el-form-item label="结束年份">
                            <el-input v-model="formG.endYear"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addGroup">添加</el-button>
                            <el-button @click="clearInputG">重置</el-button>
                        </el-form-item>
                  </el-form>
                </el-dialog>
                <!--更新的按钮 G-->
                <el-dialog title="更新诗人城市组" :visible.sync="updateVisibleG" width="50%" @close="resetG" >
                  <el-form ref="formG" :model="formG" label-width="80px">
                        <el-form-item label="开始年份">
                            <el-input v-model="formG.beginYear"></el-input>
                        </el-form-item>
                        <el-form-item label="结束年份">
                          <el-input v-model="formG.endYear"></el-input>
                        </el-form-item>
  
                        <el-form-item>
                            <el-button type="primary" @click="updateGroup">保存</el-button>
                            <!-- <el-button @click="clearInput">重置</el-button> -->
                        </el-form-item>
                  </el-form>
                </el-dialog>
                <!-- Experience---------------------------------------------------------------------------------------- -->
                <div v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id != undefined && this.ExperienceValue.id == undefined">
                <!-- 分页查询的表格 G -->
                <el-table :data="ExperienceData" 
                border
                  @selection-change="handleSelectionChangeE">
                  <el-table-column type="selection"> </el-table-column>
                  <el-table-column prop="id" label="id" align="center"> </el-table-column>
                  <el-table-column prop="beginYear" label="开始年份" align="center"> </el-table-column>
                  <el-table-column prop="endYear" label="结束年份" align="center" > </el-table-column>
                  <el-table-column prop="document" label="经历介绍" align="center" :show-overflow-tooltip="true"> </el-table-column>
                  <el-table-column align="center"  label="操作">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        @click="handleEditE(scope.$index, scope.row)">编辑</el-button>
                      <el-button
                        size="mini"
                        type="danger"
                        @click="handleDeleteE(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
  
                <!-- 分页查询的 G -->
                <div class="block" style="margin-top:15px">
                  <el-pagination
                    @size-change="handleSizeChangeE"
                    @current-change="handleCurrentChangeE"
                    :current-page="currentPageE"
                    :page-size="100"
                    :page-sizes="[2, 5, 8, 100]"   
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="countE">
                  </el-pagination>
                </div>
              </div>
                <!--添加按钮的代码 G-->
                <el-dialog title="添加诗人经历" :visible.sync="addVisibleE" width="50%" @close="resetE">
                  <el-form ref="formE" :model="formE" label-width="80px">
                        <el-form-item label="开始年份">
                            <el-input v-model="formE.beginYear"></el-input>
                        </el-form-item>
                        <el-form-item label="结束年份">
                            <el-input v-model="formE.endYear"></el-input>
                        </el-form-item>
                        <el-form-item label="经历">
                            <el-input :rows="5" type="textarea" v-model="formE.document"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addExperience">添加</el-button>
                            <el-button @click="clearInputE">重置</el-button>
                        </el-form-item>
                  </el-form>
                </el-dialog>
                <!--更新的按钮 G-->
                <el-dialog title="更新诗人经历" :visible.sync="updateVisibleE" width="50%" @close="resetE" >
                  <el-form ref="formE" :model="formE" label-width="80px">
                        <el-form-item label="开始年份">
                            <el-input v-model="formE.beginYear"></el-input>
                        </el-form-item>
                        <el-form-item label="结束年份">
                          <el-input v-model="formE.endYear"></el-input>
                        </el-form-item>
                        <el-form-item label="经历">
                            <el-input :rows="5" type="textarea" v-model="formE.document"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="updateExperience">保存</el-button>
                            <!-- <el-button @click="clearInput">重置</el-button> -->
                        </el-form-item>
                  </el-form>
                </el-dialog>
                 <!-- Poem---------------------------------------------------------------------------------------- -->
                 <div v-if="this.CityValue.id != undefined && this.PeopleValue.id != undefined && this.GroupValue.id != undefined && this.ExperienceValue.id != undefined">
                <!-- 分页查询的表格 P -->
                <el-table :data="PoemData" 
                border
                  @selection-change="handleSelectionChangeP">
                  <el-table-column type="selection"> </el-table-column>
                  <el-table-column prop="id" label="id" align="center"> </el-table-column>
                  <el-table-column prop="poemTitle" label="诗歌题目" align="center"> </el-table-column>
                  <el-table-column prop="content" label="诗歌内容" align="center" :show-overflow-tooltip="true"> </el-table-column>
                  <el-table-column prop="dynasty" label="朝代" align="center"> </el-table-column>
                  <el-table-column prop="year" label="年份" align="center"> </el-table-column>
                  <el-table-column prop="source" label="来源" align="center" :show-overflow-tooltip="true"> </el-table-column>
                  <el-table-column prop="type" label="类型" align="center"> </el-table-column>
                  <el-table-column align="center"  label="操作">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        @click="handleEditP(scope.$index, scope.row)">编辑</el-button>
                      <el-button
                        size="mini"
                        type="danger"
                        @click="handleDeleteP(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
  
                <!-- 分页查询的 P -->
                <div class="block" style="margin-top:15px">
                  <el-pagination
                    @size-change="handleSizeChangeP"
                    @current-change="handleCurrentChangeP"
                    :current-page="currentPageP"
                    :page-size="100"
                    :page-sizes="[2, 5, 8, 100]"   
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="countP">
                  </el-pagination>
                </div>
              </div>
                <!--添加按钮的代码 P-->
                <el-dialog title="添加诗歌" :visible.sync="addVisibleP" width="50%" @close="resetP">
                  <el-form ref="formP" :model="formP" label-width="80px">
                        <el-form-item label="诗歌题目">
                            <el-input v-model="formP.poemTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="诗歌内容">
                            <el-input :rows="5" type="textarea" v-model="formP.content"></el-input>
                        </el-form-item>
                        <el-form-item label="朝代">
                            <el-input v-model="formP.dynasty"></el-input>
                        </el-form-item>
                        <el-form-item label="年份">
                            <el-input v-model="formP.year"></el-input>
                        </el-form-item>
                        <el-form-item label="来源">
                            <el-input :rows="3" type="textarea" v-model="formP.source"></el-input>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-input v-model="formP.type"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="addPoem">添加</el-button>
                            <el-button @click="clearInputP">重置</el-button>
                        </el-form-item>
                  </el-form>
                </el-dialog>
                <!--更新的按钮 P-->
                <el-dialog title="更新诗歌" :visible.sync="updateVisibleP" width="50%" @close="resetP" >
                  <el-form ref="formP" :model="formP" label-width="80px">
                    <el-form-item label="诗歌题目">
                            <el-input v-model="formP.poemTitle"></el-input>
                        </el-form-item>
                        <el-form-item label="诗歌内容">
                            <el-input :rows="5" type="textarea" v-model="formP.content"></el-input>
                        </el-form-item>
                        <el-form-item label="朝代">
                            <el-input v-model="formP.dynasty"></el-input>
                        </el-form-item>
                        <el-form-item label="年份">
                            <el-input v-model="formP.year"></el-input>
                        </el-form-item>
                        <el-form-item label="来源">
                            <el-input :rows="5" type="textarea" v-model="formP.source"></el-input>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-input v-model="formP.type"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="updatePoem">保存</el-button>
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
      this.queryCity()
      //在页面打开后先将城市的信息放入选择框中
      // this.$http.get("Province")
      //   .then(response=>{
      //     this.CityDate = response.data

      //     for(let i = 0; i < this.CityDate.length; i++){
      //         var tmp = {}
      //         tmp.value = (i+1).toString()
      //         tmp.label = this.CityDate[i].province
      //         this.options.push(tmp)
              
      //         //通过省份查询城市
      //         this.$http.get("City",{
      //           params: {
      //             province : this.CityDate[i].province
      //           }
      //         })
      //         .then(response=>{
      //           var children = []
      //           for(let n = 0; n < response.data.length; n++){
      //             var tmp = {}
      //             tmp.value = (n+1).toString()
      //             tmp.label = response.data[n].cname
      //             children.push(tmp)
      //           }
      //           this.options[i].children = children
      //           // console.log(children)
      //         })
      //     }   
      //     //将获得的信息添加到option数组对象当中
      //     console.log(this.options)  
      //   })
      //   .catch(function(error){
      //     console.log(error)
      // })
    },
    
    data() {
      return {
        PeopleValue : "",//第一个选择器中诗人的值
        CityValue : "",//第二个选择器中城市的值
        GroupValue : "",//第三个选择器中组的值
        ExperienceValue : "",//第四个选择器诗人经历的值
        
        peopleName : [],//选择器中的诗人姓名
        cityName :[],//选择器中的城市名称
        groupName : [],//选择器中的诗人城市组名称
        experienceName : [],//选择器中的诗人经历

        options: [{
          value: 'zhinan',
          label: '指南',
          children: [{
            value: 'shejiyuanze',
            label: '设计原则',
                    }]
          }],
 
        //--------------Group的设置
        multipleSelectionG : [],
        GroupData:[],//所有诗人城市组信息
        currentPageG : 1,//当前的
        countG : 0,//分页查询页面总数
        pageNumG : 1,//
        pageSizeG : 100,//默认的分页大小
        addVisibleG : false,//添加的显示框
        updateVisibleG : false,//更新的显示框
        formG: {
                  peopleId : Number,//诗人外键
                  cityId : Number,//城市外键
                  beginYear: '', // 开始年份
                  endYear: '', // 结束年份
              },
        //--------------Experience的设置
        multipleSelectionE : [],
        ExperienceData:[],//所有诗人城市组信息
        currentPageE : 1,//当前的
        countE : 0,//分页查询页面总数
        pageNumE : 1,//
        pageSizeE : 100,//默认的分页大小
        addVisibleE : false,//添加的显示框
        updateVisibleE : false,//更新的显示框
        formE: {
                  groupId : Number,//诗人城市组外键
                  beginYear : '', // 开始年份
                  endYear : '', // 结束年份
                  document : ''//诗人经历
              },
        //--------------Poem的设置
        multipleSelectionP : [],
        PoemData:[],//所有诗人城市组信息
        currentPageP : 1,//当前的
        countP : 0,//分页查询页面总数
        pageNumP : 1,//
        pageSizeP : 100,//默认的分页大小
        addVisibleP : false,//添加的显示框
        updateVisibleP : false,//更新的显示框
        formP: {
                  experienceId : "",//经验外键
                  poemTitle : "",//诗歌题目
                  content: "",// 诗歌内容
                  dynasty : '', // 诗歌朝代
                  year : '', // 结束年份
                  source : '',//诗人经历
                  type : '' //诗歌内形
              },
      }
    },

    methods:{
      //选择器改变时 
      displayDiv1(val){
       
        this.formG.peopleId = this.PeopleValue.id
        if(this.PeopleValue.name != undefined && this.CityValue.cname != undefined && this.GroupValue.id == undefined && this.ExperienceValue.id == undefined){
          this.queryGroup()
        }
     },
      displayDiv2(val){
        // console.log(this.CityValue.id)
        this.formG.cityId = this.CityValue.id
        if(this.PeopleValue.name != undefined && this.CityValue.cname != undefined && this.GroupValue.id == undefined && this.ExperienceValue.id == undefined){
          this.queryGroup()
          
        }
        
        // if(this.CityValue.length == 0){
        //   return
        // }

        // const checkedNodes = this.$refs['cascaderAddr'].getCheckedNodes() // 获取当前点击的节点
        // var cname = checkedNodes[0].data.label
        // console.log(cname)
      
        // console.log(checkedNodes)
        // console.log(checkedNodes[0].data.label) // 获取当前点击的节点的label
       
        // console.log(checkedNodes[0].pathLabels) // 获取由 label 组成的数组
      },
      displayDiv3(val){
        this.formE.groupId = this.GroupValue.id
        this.queryExperience()
       
      },
      displayDiv4(val){
        this.formP.experienceId = this.ExperienceValue.id
        this.queryPoem()
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
      queryCity(){
        this.$http.get("City")
        .then(response=>{
          this.cityName = response.data
          // console.log(this.cityName)
        })
        .catch(function(error){
          console.log(error)
      })
      },
      queryGroup(){
        this.$http.get("queryGroupByPeopleAndCityPage",{params:{
          pageNum : this.pageNumG,
          pageSize : this.pageSizeG,
          name : this.PeopleValue.name,
          cname : this.CityValue.cname
        }})
        .then(response=>{
          this.GroupData = response.data.data
          this.countG = response.data.count
          this.groupName = this.GroupData
          // console.log(this.groupName)
        })
        .catch(function(error){
          console.log(error)
      })

      },
      queryExperience(){
        this.$http.get("queryExperienceByGroupIdPage",{params:{
          pageNum : this.pageNumE,
          pageSize : this.pageSizeE,
          groupId : this.GroupValue.id,
          
        }})
        .then(response=>{
          this.ExperienceData = response.data.data
          this.countE = response.data.count
          this.experienceName = this.ExperienceData
          // this.groupName = this.GroupData
          // console.log(this.groupName)
        })
        .catch(function(error){
          console.log(error)
      })
      },
      queryPoem(){
        this.$http.get("queryPoemByExperienceIdPage",{params:{
          pageNum : this.pageNumP,
          pageSize : this.pageSizeP,
          experienceId : this.ExperienceValue.id,
          
        }})
        .then(response=>{
          this.PoemData = response.data.data
          this.countP = response.data.count
          // this.groupName = this.GroupData
          // console.log(this.groupName)
        })
        .catch(function(error){
          console.log(error)
      })
      },
      //监听操作按钮
      handleEditG(index, row) {
        this.updateVisibleG = true
        this.formG = row
        // console.log(index, row);
      },
      //删除操作
      //删除按钮
      handleDeleteG(index, row) {
        // console.log(row.id);
         this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete("/Egroup/" + row.id).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryGroup()
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
      handleSelectionChangeG(val){
        this.multipleSelectionG = val
        // console.log(this.multipleSelectionG)
      },
      deleteMultipleGroup(){
        let ids = this.multipleSelectionG.map(v => v.id)
        this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post("/Egroup/batch",ids).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryGroup()
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
       handleCurrentChangeG(pageNum){
        // console.log(pageNum);
        this.pageNumG =pageNum
        this.queryGroup()
      },
      //监听pageSize的
      handleSizeChangeG(pageSize){
        // console.log(pageSize);
        this.pageSizeG = pageSize
        this.queryGroup()
      },
      //添加窗体的操作
      //清空
      clearInputG(){
              this.formG.beginYear = ''
              this.formG.endYear = ''
      },
      //添加和更新城市的函数
      saveGroup(){
        this.$http.post("saveEgroup",this.formG)
              .then(res => {
                  if(res){
                    this.$message.success('保存成功');
                    this.addVisibleG = false
                    this.updateVisibleG = false
                  }
                  else{
                    this.$message.error("保存失败")
                  }
              })
              .catch(function (error) {
                  console.log(error);
              })
          this.queryGroup()
      },
      //添加城市信息
      addGroup(){  
        this.saveGroup()
        this.queryGroup()
        this.clearFormG()
      },
      //更新城市信息
      updateGroup(){
        
        this.saveGroup()
        this.queryGroup()
        this.clearFormG()
      },
      //清空form中信息
      clearFormG(){
        this.formG.id = null,
        this.formG.peopleId = this.PeopleValue.id,
        this.formG.cityId = this.CityValue.id,
        this.formG.beginYear = "",
        this.formG.endYear = ""
      },
      // diolag点击叉叉后
      resetG(){
          this.clearFormG()
          this.queryGroup()
      },
      // 新增的按钮
      addButtonG(){
        console.log(this.value)
        if(this.PeopleValue.id == null || this.CityValue.id == null){
          this.$message('请先选择诗人和城市');
        }
        else{
          this.addVisibleG = true
        }
      },
      //批量删除按钮
      deleteButtonG(){
          this.deleteMultipleGroup()
      },

      //Experience-----------------------------------------------------
      //返回的是label
      change(item){
        if(item.endYear == null){
          return item.beginYear
        }
        else{
          return item.beginYear.toString() + ' - ' + item.endYear.toString()
        }
      },
      //监听操作按钮
      handleEditE(index, row) {
        this.updateVisibleE = true
        this.formE = row
        // console.log(index, row);
      },
      //删除操作
      //删除按钮
      handleDeleteE(index, row) {
        // console.log(row.id);
         this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete("/Experience/" + row.id).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryExperience()
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
      handleSelectionChangeE(val){
        this.multipleSelectionE = val
        // console.log(this.multipleSelectionG)
      },
      deleteMultipleGroup(){
        let ids = this.multipleSelectionE.map(v => v.id)
        this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post("/Experience/batch",ids).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryExperience()
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
       handleCurrentChangeE(pageNum){
        // console.log(pageNum);
        this.pageNumE = pageNum
        this.queryExperience()
      },
      //监听pageSize的
      handleSizeChangeE(pageSize){
        // console.log(pageSize);
        this.pageSizeE = pageSize
        this.queryExperience()
      },
      //添加窗体的操作
      //清空
      clearInputE(){
              this.formE.beginYear = ''
              this.formE.endYear = ''
              this.formE.document = ''
      },
      //添加和更新城市的函数
      saveExperience(){
        this.$http.post("saveExperience",this.formE)
              .then(res => {
                  if(res){
                    this.$message.success('保存成功');
                    this.addVisibleE = false
                    this.updateVisibleE = false
                  }
                  else{
                    this.$message.error("保存失败")
                  }
              })
              .catch(function (error) {
                  console.log(error);
              })
          this.queryExperience()
      },
      //添加城市信息
      addExperience(){  
        this.saveExperience()
        this.queryExperience()
        this.clearFormE()
      },
      //更新城市信息
      updateExperience(){
        this.saveExperience()
        this.queryExperience()
        this.clearFormE()
      },
      //清空form中信息
      clearFormE(){
        this.formE.id = null,
        this.formE.groupId = this.GroupValue.id,
        this.formE.beginYear = "",
        this.formE.endYear = "",
        this.formE.document = ""
      },
      // diolag点击叉叉后
      resetE(){
          this.clearFormE()
          this.queryExperience()
      },
      // 新增的按钮
      addButtonE(){
        // console.log("GroupSelect")
        if(this.PeopleValue.id == null || this.CityValue.id == null){
          this.$message('请先选择诗人和城市');
        }
        else{
          this.addVisibleE = true
        }
      },
      //批量删除按钮
      deleteButtonE(){
          this.deleteMultipleGroup()
      },
      //Poem -----------------------------------------------------
      
      //监听操作按钮
      handleEditP(index, row) {
        this.updateVisibleP = true
        this.formP = row
        // console.log(index, row);
      },
      //删除操作
      //删除按钮
      handleDeleteP(index, row) {
        // console.log(row.id);
         this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.delete("/Poem/" + row.id).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryPoem()
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
      handleSelectionChangeP(val){
        this.multipleSelectionP = val
        // console.log(this.multipleSelectionG)
      },
      deleteMultipleGroup(){
        let ids = this.multipleSelectionP.map(v => v.id)
        this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http.post("/Poem/batch",ids).then(res=>{
              if(res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                this.queryPoem()
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
       handleCurrentChangeP(pageNum){
        // console.log(pageNum);
        this.pageNumP = pageNum
        this.queryPoem()
      },
      //监听pageSize的
      handleSizeChangeP(pageSize){
        // console.log(pageSize);
        this.pageSizeP = pageSize
        this.queryPoem()
      },
      //添加窗体的操作
      //清空
      clearInputP(){
              this.formE.beginYear = ''
              this.formE.endYear = ''
              this.formE.document = ''
      },
      //添加和更新城市的函数
      savePoem(){
        this.$http.post("savePoem",this.formP)
              .then(res => {
                  if(res){
                    this.$message.success('保存成功');
                    this.addVisibleP = false
                    this.updateVisibleP = false
                  }
                  else{
                    this.$message.error("保存失败")
                  }
              })
              .catch(function (error) {
                  console.log(error);
              })
          this.queryPoem()
      },
      //添加城市信息
      addPoem(){  
        this.savePoem()
        this.queryPoem()
        this.clearFormP()
      },
      //更新城市信息
      updatePoem(){
        this.savePoem()
        this.queryPoem()
        this.clearFormP()
      },
      //清空form中信息
      clearFormP(){
        this.formP.id = null,
        this.formP.experienceId = this.ExperienceValue.id,
        this.formP.poemTitle = "",
        this.formP.content = "",
        this.formP.dynasty = "",
        this.formP.year = "",
        this.formP.source = "",
        this.formP.type = ""
      },
      // diolag点击叉叉后
      resetP(){
          this.clearFormP()
          this.queryPoem()
      },
      // 新增的按钮
      addButtonP(){
        // console.log("GroupSelect")
        if(this.PeopleValue.id == null || this.CityValue.id == null){
          this.$message('请先选择诗人和城市');
        }
        else{
          this.addVisibleP = true
        }
      },
      //批量删除按钮
      deleteButtonP(){
          this.deleteMultipleGroup()
      },
      changeP(item){
        if(item.endYear == null){
          return item.beginYear
        }
        else{
          return item.beginYear.toString() + ' - ' + item.endYear.toString()
        }
      }
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