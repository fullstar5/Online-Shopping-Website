<template>
  <div class = 'body'>
<div class ='container' style = 'background-color: #6892CC;position:fixed; top:19%; width:100%'>
  <div class="login-container">
    <el-form label-position="left" class="demo-ruleForm"
      :rules="rules"
      ref="ruleFormRef"
      :model="ruleForm"
>
      <div class="title-container">
        <h1 style="color: #fff;
            text-shadow: 0 0 10px;
            letter-spacing: 1px;
            text-align: center;">Login</h1>

      </div>



      <el-form-item label="userID" prop="userID"  style="width:40%; ">
        <el-input type="text" v-model="ruleForm.userID" autocomplete="off" placeholder="userID" oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
      </el-form-item>
      <br>
      <br>
      <el-form-item label="userPassword" prop="userPassword" style="width:40%;">
        <el-input type="userPassword" v-model="ruleForm.userPassword" autocomplete="off" placeholder="Password"></el-input>
      </el-form-item>
      <br>
      
      <el-form-item>
      <el-button type = 'primary'  @click="login" style='display:block;margin: 0 auto;'>Login</el-button>
    </el-form-item>
    </el-form>
  </div>
</div>
</div>
</template>


<script>
// import axios from "axios"

import { post_user_login_api } from '@/api/api.js'

export default{

  mounted() {
        document.querySelector('body').setAttribute('style', 'background-color:#6892CC')
  },

    beforeDestroy() {
        document.querySelector('body').removeAttribute('style')
    },

data() {
    return {
      ruleForm: {
        userID: "",
        userPassword:"",
        },
        rules: {
          userID: [
          { required: true, message: 'Please enter your userID', trigger: 'blur' }
        ],
        userPassword: [
          { required: true, message: 'Please enter your userPassword', trigger: 'blur' }
        ]
        }
    };
  },

 methods: {
    login() {
      var data = this.ruleForm
      this.$refs.ruleFormRef.validate((valid) => {
        if (valid){
      post_user_login_api(data)
        .then((data) => {
          console.log(data);
          if (this.ruleForm.userID == data.data.userID){
          alert ('Login successful')
          this.user = data.data

          this.$store.commit("setuserInfo", this.user);
          this.$router.push('/information')}else{
     

           console.log("failed!!");
          alert ('Login failed! Please enter the correct account password!')}

        }
          //if (data == false){

          
        )
        .catch((err) => {
          console.log(err);
          alert ('Login failed! Please enter the correct account password!')
        })} else {
          console.log("failed!!");
          return false;
        }})
    }
  }}


  

</script>

<style scoped>
  .container{
    color: #6892CC;
    height: 100%;
  }
  .el-form-item{
    margin: 0 auto;
  }

  img{
    height: 15%;
    width: 15%;
  }

    .el-button--primary{
  width: 200px;

  height: 50px;
 

  border-radius: 10px;

  padding: 0;

      background-color: #F8C874;
    }

    </style>
