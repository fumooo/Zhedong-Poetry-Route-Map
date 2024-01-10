<template>
  <div>
    <baidu-map :center="center" :zoom="zoom" @ready="handler" class="baidu" MapType="BMAP_HYBRID_MAP">
      <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
      <bm-overview-map anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :isOpen="false"></bm-overview-map>
      <bm-map-type :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
      

      <div v-for='(item,index) in positionList'>
      <bm-marker :dragging="true" v-for='(position,indexx) in item' :icon="{url: point[index], size: {width: 32, height: 32}}" @click="infoWindowOpen(index,indexx)" :key="indexx" :position="{lng: position.lng, lat: position.lat}">
        <bm-label :content="position.cname" 
        :labelStyle="style" 
        :offset="{width: -8, height: 29}"
        />
        <bm-info-window ref="infoWindow" :show="position.showFlag" @close="infoWindowClose(index,indexx)" :autoPan='true' :width="500" :height="300">
          <clickwindow 
          :cityName="positionList[index][indexx].cname"
          :peopleName="$store.state.peopleName[index]"
          :PeopleCityInformation="PeopleCityInformation"
          ></clickwindow>
        </bm-info-window>
      </bm-marker>      
      </div>
      
      <div v-for='(item,index) in lineList' >
      <bml-curve-line :points="item" :stroke-color="lineColor[index]" :stroke-opacity="1" :stroke-weight="2" ></bml-curve-line>
      </div>
      
      <!-- <bm-polyline :path="lineList" stroke-color="blue" :stroke-opacity="1" :stroke-weight="2" :editing="true" ></bm-polyline> -->
    </baidu-map>
  </div>
</template>

<script>
import {BmlCurveLine} from 'vue-baidu-map'
import clickwindow from "../front/Popup_window/ClickWindow.vue"
import {mapState} from 'vuex'
export default {
  components:{BmlCurveLine,window,clickwindow},
  name: "baidu",
  
  updated:function(){
    // console.log(this.positions.length)
    //  console.log(this.$store.state.poemList.length)
    //  console.log(this.positions.length)
    //  console.log("-------")
  },

  mounted:function(){
    //用于初始化坐标点
    this.point.push(require('../../assets/point/marker.azure.png'))
    this.point.push(require('../../assets/point/marker.green.png'))
    this.point.push(require('../../assets/point/marker.red.png'))
    this.point.push(require('../../assets/point/marker.azure1.png'))
    
  },
  
  data() {
    return {
      style:{border:'1px solid rgb(187, 187, 187)', font:'12px arial'},
      center: { lng: 0, lat: 0 },
      zoom: 8,
      positionList:[],//用来存储城市信息
      point:[],//用来存储需要使用的覆盖物点的图片路径
      lineList:[], //用来存储曲线的经纬度
      lineColor:['pink','purple','red','grey'],//用来存储曲线的颜色
      PeopleCityInformation:[],//用来存储诗人城市信息
    };
  },
  methods: {
    // 舒适化地图
    handler({ BMap, map }) {
      console.log(BMap, map);
      // // 经度
      // this.center.lng = 116.404
      // // 维度
      // this.center.lat = 39.915
      this.center = "台州";
      // 地图放大等级
      
      // 个性化样式
      map.setMapStyleV2({
        styleId: "2896c69336cf847cc5ab917cd1e19d44",
      });
      map.enableScrollWheelZoom(true);
      // map.setMapType(BMAP_HYBRID_MAP);
    },
    // 设置关闭窗口
    infoWindowClose(index,indexx){
      this.positionList[index][indexx].showFlag = false
    },
    // 设置打开窗口
    infoWindowOpen(index,indexx){
      // console.log(index)   
      // console.log(indexx)  
      //设置这个窗体不刷新 这个坑是vue-baidu-map里面的 
      setTimeout(() => {
      this.$nextTick(() => {
      this.$refs.infoWindow[(index+1)*(indexx+1)-1].observer.disconnect();
      // this.$refs.infoWindow[(index+1)*(indexx+1)].observer.disconnect();
      })
    }, 500);
      this.positionList[index][indexx].showFlag = true
      console.log(this.positionList[index][indexx].cname,this.$store.state.peopleName[index])
      this.$http.get("queryInformation",{
        params:{
          cname : this.positionList[index][indexx].cname,
          pname : this.$store.state.peopleName[index]
        }
      }).then((response)=>{        
        this.PeopleCityInformation = response.data
      }).catch(function(error){
        console.log(error)
      })
      // this.PeopleCityInformation = this.PeopleCityInformation.replace("\\n", "\n");
    }
  },
  computed:{
    poemList(){
			return this.$store.state.poemList
		},

  },
  watch:{
    poemList(newVal,oldVal){
      // console.log(typeof(newVal[0]))
      // console.log("长度"+newVal.length)
      if(newVal.length == 0){
        this.positionList = []
        this.lineList = []
        return
      }
      if(newVal.length > 0){
        this.positionList = []
        this.lineList = []

        //axios查询覆盖物点 且按照顺序
        let position = []
        for(let i of newVal){
          position.push(
            new Promise((resolve,reject)=>{
              this.$http.get(
                `queryByPeopleId/?id=${i}`
              ).then(response=>{
                resolve(response)
              }),error=>{
                reject(error)
              }
            })
          )
        }
        Promise.all(position).then(res=>{
          for(let i of res){
            // console.log(i.data)
            for(let m = 0; m < i.data.length; m++){
              i.data[m].showFlag = false
            }
            this.positionList.push(i.data)
          }
        })

        //axios查询覆盖物曲线 且按照顺序
        let line = []
        for(let i of newVal){
          line.push(
            new Promise((resolve,reject)=>{
              this.$http.get(
                `queryLineByPeopleId/?id=${i}`
              ).then(response=>{
                resolve(response)
              }),error=>{
                reject(error)
              }
            })
          )
        }
        Promise.all(line).then(res=>{
          for(let i of res){
            this.lineList.push(i.data)
          }
        })

        // for(let i = 0; i < newVal.length; i++){
        //   //axios查询覆盖物点
        //   this.$http.get("queryByPeopleId",{
        //       params: {
        //       id: newVal[i]
        //     }
        //   })
        //   .then((response)=>{ 
        //     // console.log(response.data.length)
        //     //先给每一个对象加一条showFlag属性 用于去判断点覆盖物上面的弹窗
        //       for(let i = 0 ; i < response.data.length ; i++){
        //         response.data[i].showFlag = false
        //       }
        //       this.positions.push(response.data)//通过键值对形式去存储数据
        //   }) 
        //   .catch(function (error) {
        //     console.log(error);
        //   }) 

        //   //axios查询覆盖物曲线
        //   this.$http.get("queryLineByPeopleId",{
        //       params: {
        //       id: newVal[i]
        //     }
        //   })
        //   .then((response)=>{ 
        //       this.lineList.push(response.data)//通过键值对形式去存储数据
        //       // console.log(response.data)
        //       // console.log(typeof(response.data))
        //   }) 
        //   .catch(function (error) {
        //     console.log(error);
        //   }) 
        //   // console.log(this.lineList)
        // } 
      }
    },
  }
};
</script>

<style scoped>
.baidu {
  width: 100%;
  height: 550px;
  display: flex;
}
.baidu-map {
  width: 100%;
  height: 550px;
  display: flex;
}
.bm-info-window{
  display: flex;
  overflow: auto;
}
</style>