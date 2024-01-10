<template>
  <div>
    <RigthWindow></RigthWindow>
  </div>
</template>

<script>
import RigthWindow from "../front/Rigth_window/Right_window.vue"
export default {
    components:{RigthWindow},
    name:'righttext',
    data(){
      return{
        
      }
    },
    methods:{

    },
    computed:{
      poemList(){
        return this.$store.state.poemList;
      }
    },
    watch:{
      poemList(newVal,oldVal){

      }
    }
}
</script>

<style scoped>

</style>