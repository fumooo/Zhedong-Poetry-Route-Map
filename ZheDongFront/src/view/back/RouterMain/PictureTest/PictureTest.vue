<template>
  <div>
<!-- 再次提醒，:on-success="函数名"，函数名后面不要跟括号 -->
        <!-- <el-button size="small" type="primary">点击上传</el-button> -->
    <!-- <el-upload
        action="http://localhost:8088/file/upload"
        :on-success="handle_success"
        list-type="picture-card"
       
      >
     
        
        <div slot="tip" class="el-upload__tip">
          只能上传jpg/png文件，且不超过500kb
        </div>
        <i class="el-icon-plus"></i>
    </el-upload> -->

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
    
    <img v-if="test != '' " class="img" alt="example" :src="require(`@/assets/image/${test}`) " width="20%">
    
  </div>
</template>

<script>
export default {
    data() {
      return {
        dialogImageUrl: '',
        dialogVisible: false,
        imgUrl : '', 
        disabled: false,
        test : '1683094157750.png',
        // defaultImg:require('@/assets/images/1683041669771.png')
      };
    },
    methods: {
      handle_success(res) {
        console.log(res.data);
        this.$message.success("图片上传成功");
        this.imgUrl = res.data
        
      //   return (this.homeAdvertise.imgUrl = res.data);
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
     
    }
}
</script>

<style>

</style>