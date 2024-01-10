<template>
  <div style="height:300px; overflow:auto">
    <el-descriptions >
        <el-descriptions-item label="诗人名">{{peopleName}}</el-descriptions-item>
        <el-descriptions-item label="所在城市">{{cityName}}</el-descriptions-item>
    </el-descriptions>
    <div v-for="(item,index) in PeopleCityInformation" :key="index"> 
        <el-collapse v-model="activeNames"  @change="activeNamesChange" >
            <el-collapse-item :title="connectString(item.beginYear,item.endYear)" :name="index">
                <div v-for="(experience,indexx) in item.peopleAssExperiences" :key="indexx" style="text-align:center">
                    <span>背景：{{experience.beginYear}}年   --   {{experience.document}}</span>
                    <div v-for="(poem,indexxx) in experience.poem" :key="indexxx" style="white-space: pre-line;">
                        <div style="background:#f2f2e7;text-align:center">
                            <span style="font-size:15px">{{poem.poemTitle}}&nbsp</span>
                            <span style="font-size:15px"> {{poem.dynasty}}&nbsp</span>
                            <span style="font-size:15px">{{poem.type}}&nbsp</span>
                            <span style="font-size:15px">{{poem.year}}年书</span>
                        </div>
                        <div style="text-align:center">{{poem.content}}</div>
                        <div style="text-align:center"><span style="font-size:10px">{{poem.source}}</span></div>
                    </div>
                    <el-divider></el-divider>
                </div>
            </el-collapse-item>
        </el-collapse>
    </div>
  </div>
</template>

<script>
export default {
    name:'clickwindow',
    props:[
        'peopleName', 'cityName', 'PeopleCityInformation'
    ],
    data(){
        return{
           activeNames:'1'
        }
    },
    methods:{
        activeNamesChange(){

        },
        connectString(beginYear,endYear){
            if(endYear == null){
                return beginYear + "年"
            }
            else if(endYear != null){
                return beginYear + " -- " + endYear + "年"
            }
        }
    },
    mounted:function(){
        
    },
    computed:{
    },
    watch:{
    }
}
</script>

<style>

</style>