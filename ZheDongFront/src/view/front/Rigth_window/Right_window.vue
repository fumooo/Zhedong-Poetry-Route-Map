<template>
  <div>
    <!-- 主页面下的一个 -->
    <el-tabs v-if='tabIs' v-model="activeName" type="card" @tab-click="handleClick">
    <el-tab-pane v-for="(item,index) in peopleInformation" :key="index" :name="item.name">
      <span slot="label"><img :src="imgsrc[index]" width="15" height="15">{{item.name}}</span>
      <el-collapse v-model="activeNames">
        <el-collapse-item title="诗人简介" name="0">
            <div>{{item.introduction}}</div>
            <!-- <div>{{item.pictureP}}</div> -->
            <img v-if="item.pictureP != '' " class="img" alt="example" :src="require(`@/assets/image/${item.pictureP}`) " width="40%">
        </el-collapse-item>
        <el-collapse-item title="诗人途径景点" name="1">
            <div v-for="(item,indexx) in sceneryInfom[index]" :key="indexx">
              景点名称：<a :href="GetFullHref(item.name)" target="_blank">{{item.name}}</a><br>
              <p>{{item.introduction}}</p>
              <img v-if="item.picture != '' " class="img" alt="example" :src="require(`@/assets/image/${item.picture}`) " width="60%">
            </div>
        </el-collapse-item>
      </el-collapse>
    </el-tab-pane>
    </el-tabs>

    <!-- 用于显示唐诗作品按地区统计 -->
    <div v-if="cityInfom">
      <span>
      <p style="text-align: center; margin:10px">唐诗作品按地区统计</p>
      </span>
      <hr style="margin:0px;background-color:grey;height:0.7px;border:none;">
      
      <el-table
      size="small"
      :data="tableData"
      style="width: 100%">
      <el-table-column 
        header-align="center"
        align="center"
        prop="cname"
        label="城市"
        width="150">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        prop="cityPoemNumber"
        label="诗歌数量"
        width="150">
      </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "RigthWindow",
  data() {
    return {
      tabIs : false,//是否显示右侧诗人信息，在左边多选框改变之后
      cityInfom : true,//是否显示唐诗作品按地区统计信息
      tableData:[],//用来存储每个城市诗歌的数量
      activeName:"",//用于指定是哪一个tab打开
      editableTabs:[],//用于存储tab内容
      activeNames:['0','1'],
      peopleInformation:[],//诗人的信息存储
      sceneryInfom:[],//通过诗人id查询出其访问的景点名称
      imgsrc:[],//用来存储标签上图片的路径
      img:""
    };
  },
  methods:{
      handleClick(tab, event) {
        // console.log(tab, event);
      },
      //用于去返回一个链接
      GetFullHref(name){
        return 'https://search.bilibili.com/all?keyword=' + name
      },
      //使用此方法去查询诗人的信息
      GetpeopleInformation(id){
        return this.$http.get("peopleById",{
          params:{
            id : id
          }
        })
        .then((response)=>{
          this.peopleInformation.push(response.data)
          console.log(this.peopleInformation)
        })
         .catch(function (error) {
               console.log(error)
        }) 
      },
      //使用此方法去查询诗人所拜访景点的信息
      GetsceneryInfom(id){
        return this.$http.get("querySceneryById",{
          params:{
            id : id
          }
        })
        .then((response)=>{
          this.sceneryInfom.push(response.data)
          console.log(this.sceneryInfom)
        })
         .catch(function (error) {
               console.log(error)
        }) 
      },
    },
    mounted:function(){
      //用来查询唐诗作品按地区统计
      this.$http.get("queryCityPoemNumber")
      .then((response)=>{ 
        this.tableData = response.data
      }) 
      .catch(function (error) {
        console.log(error);
      })
      this.imgsrc.push(require('../../../assets/point/marker.azure.png'))
      this.imgsrc.push(require('../../../assets/point/marker.green.png'))
      this.imgsrc.push(require('../../../assets/point/marker.red.png'))
      this.imgsrc.push(require('../../../assets/point/marker.azure1.png'))
    },
    computed:{
      poemList(){
			return this.$store.state.poemList
		},
    },
    watch:{
      poemList(newVal){
        
        this.peopleInformation = []
        this.sceneryInfom = []
        //判断是否显示右边菜单
        if(newVal.length == 0 ){
          this.tabIs = false
          this.cityInfom = true
          return
        }
        else if(newVal.length > 0){
          this.tabIs = true
          this.cityInfom = false
        }
        // console.log(newVal);
        //用promise.all去进行使用
        var resdata
        let people = []
        let peopleName = [] 
        for(let i of newVal){
          people.push(
            new Promise((resolve,reject)=>{
              this.$http.get(
                `peopleById/?id=${i}`
              ).then(response=>{
                resolve(response)
              }),error=>{
                reject(error)
              }
            })
          )
        }
        Promise.all(people).then(res=>{
          for(let i of res){
            this.peopleInformation.push(i.data)
            resdata=JSON.parse(JSON.stringify(i.data))
            this.activeName = resdata.name
            peopleName.push(i.data.name)
            
          }
        })
        // console.log(peopleName)
        this.$store.dispatch('updatePeopleName',peopleName)
        // console.log(resdata)
        // console.log(this.peopleInformation[this.peopleInformation.length-1].name)
        // console.log(people[people.length-1].data.name)
        // this.activeName = people[people.length-1].name
        //查询诗人访问的景点信息
        let scenery = []
        for(let i of newVal){
          scenery.push(
            new Promise((resolve,reject)=>{
              this.$http.get(
                `querySceneryById/?id=${i}`
              ).then(response=>{
                resolve(response)
              }),error=>{
                reject(error)
              }
            })
          )
        }
        Promise.all(scenery).then(res=>{
          for(let i of res){
            this.sceneryInfom.push(i.data)
          }
        })

      }
    },

};
</script>

<style scoped>

</style> 

