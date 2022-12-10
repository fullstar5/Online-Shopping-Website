<template>
 <div class = 'body' style="position:fixed; top:19%; width:100%">
 <div class ='container' style = 'background-color: #B0E0E6;'>
  </div>
  <div class="login-container">

>
      <div class="title-container">
        <h1 style="color: #fff;
            text-shadow: 0 0 10px;
            letter-spacing: 1px;
            text-align: center;">Register</h1>
      </div>

      <el-form label-position="left" 
      :model="ruleform"
      status-icon
      :rules="rules"
      ref="ruleform"
      class="demo-ruleForm"
    >
       <div class = 'input'>


        <el-form-item label="userName" prop="userName"  style="width:40%; ">
        <el-input type="text" v-model="ruleform.userName" autocomplete="off" placeholder="userName" ></el-input>
      </el-form-item>
  
    
      <el-form-item label="Password" prop="userPassword" style="width:40%;">
        <el-input
          type="password"
          v-model="ruleform.userPassword"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="Check Password" prop="checkPass" style="width:40%;">
        <el-input
          type="password"
          v-model="ruleform.checkPass"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="userEmail" prop="useEmail"  style="width:40%; ">
        <el-input type="text" v-model="ruleform.userEmail" autocomplete="off" placeholder="userEmail" @blur="OnSubscribe()"></el-input>
      </el-form-item>
      <br>
    </div>
    <br>
     
     <el-form-item>
       <el-button type="primary" @click="register"
       style='display:block;margin: 0 auto;'>Register</el-button
       >
     </el-form-item>

    </el-form>
  </div>
</div>
</template>

<script>

import { register_api} from '@/api/api.js'
import { str, string } from 'is2';
export default{
  
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#6892CC')
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  methods:{
    home (){
      this.$router.push('/');
    },
  },
  data() {
    return {
      ruleform: {
        userName: "",
        userPassword: "",
        checkPass: "",
        userEmail:"",
      },   
       rules: {
        userName: [{ required: true, message: "Please enter your username", trigger: "blur" }],
        userPassword: [{
          required: true,
          message: 'Create Password',
          trigger: 'blur'
        }],
        checkPass: [{
          required: true,
          message: 'Confirm Password',
          trigger: 'blur'
        }, {
          validator: (rule, value, callback) => {
            if (value === '') {
              callback(new Error('Please enter your password again'))
            } else if (value !== this.ruleform.userPassword) {
              callback(new Error('Two times to enter the password does not match'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }],
        userEmail: [{ required: true, message: "Please enter your email", trigger: "blur" }],

      }
    }
  },


methods: {
    register() {
      var data = this.ruleform
        this.$refs.ruleform.validate((valid) => {
        if (valid){
          register_api(data).then((data) => {
          console.log(data);
          var ID = data.data.userID
          alert ('Register successfully and your userID is ' + ID )
    
          this.$router.push('/login')
        })
        .catch((err) => {
          console.log(err);
          alert ('Registration failed')
        })
        } else {
          console.log("failed!!");
          return false;
        }})
    },
    OnSubscribe: function() {
      var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                if (this.ruleform.userEmail != '' && !regEmail.test(this.ruleform.userEmail)) {
                    this.$message({
                        message: 'Email format error',
                        type: 'error'
                    })
                    this.ruleform.userEmail = ''
                }
            }
  }}


</script>

<style scoped>

  .container{
    color: #6892CC;
    height: 100%;
    width:100%;
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
