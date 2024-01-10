<template>
  <div class="wrapper"  >
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 280px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>管理员登录</b></div>
      <el-form :model="admin" :rules="rules" ref="userForm" @keyup.enter.native="login">
        <el-form-item prop="account">
          <el-input size="medium" prefix-icon="el-icon-user" v-model="admin.account"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" prefix-icon="el-icon-lock" show-password 
                    v-model="admin.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: center">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="logist">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {setRoutes} from "@/router";

export default {
  name: "Login",
  data() {
    return {
      admin: {},
      rules: {
        account: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.$http.post("/login", this.admin).then(res => {
            // console.log(res.data)
            if (res.data) {

              this.$store.dispatch('updateLoginForm',res.data)
              
              this.$message.success("登录成功")
              this.$router.push({path: '/backIndex/CityManage'})
              
            } else {
              this.$message.error("账号或密码错误")
            }
          })
        }
      });
    },
    logist(){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          console.log(this.admin)
          this.$http.post("/saveAdmin", this.admin).then(res => {
            // console.log(res.data)
            if (res.data) {
              this.$message.success("注册成功")
            } else {
              this.$message.error("注册失败")
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
   /* background-image: linear-gradient(to bottom right, #fc7446, #fb973f);  */
 
  overflow: hidden;
  background: url("@/assets/background.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
</style>
