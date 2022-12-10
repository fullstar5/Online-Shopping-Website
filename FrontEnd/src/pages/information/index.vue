<template>
  <div class="personalCenter">
    
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>
         <br>

    <el-container>

      <el-container>
      <el-form 
      :model="ruleform"
      :rules="rules"
      ref="ruleformRef"
    >
        <el-aside width="400px">
          <el-col :span="12">
            <div class="sub-title"></div>
            <div class="demo-basic--circle">
              <div class="img">
                <el-avatar
                  :size="100"
                  :src="ruleform.circleUrl"
                  align="center"
                ></el-avatar>
              </div>
              <div class="block">
                <span>Dear: {{this.userName}}</span>
                
                
              </div>
            </div>
            <h4>Your Money Remain is: {{this.moneyRemain}}</h4> 
          </el-col>        
          <h3 style = 'color: white'>Your userID is: {{this.userID}}</h3>
     
          <el-input
            v-model="ruleform.userName"
            type="text"
            placeholder="userName"
            maxlength="8"
            show-word-limit
          ></el-input>

          <el-input type="text" v-model="ruleform.userEmail" autocomplete="off" placeholder="userEmail" @blur="OnSubscribe()"></el-input>

          <el-input
            v-model="ruleform.userAddress"
            type="text"
            placeholder="Address"
            maxlength="20"
          ></el-input>
          <br>
          <br>

          <el-button-group style="float: right; padding: 3px 0" type="text">
            <el-button type="primary"  @click="save"  size="medium" round>Save</el-button>
          </el-button-group>
          <el-button-group style="float: right; padding: 3px 0" type="text">
            <el-button type="primary"  @click="logout"  size="medium" round>Logout</el-button>
          </el-button-group>
        </el-aside>
      
      </el-form>

        <el-container> 
          <el-form 
                 :model="ruleform1"
                 :rules="rules"
                 ref="ruleformRef1"
            >      
          <el-main>
           
            <el-card class="box-card">

              <div slot="header" class="clearfix">
            
                <span style="float: left"><b>Change password</b></span>
                <el-button-group
                  style="float: right; padding: 3px 0"
                  type="text"
                >

                  <el-button
                    type="primary"
                    icon="el-icon-edit"
                    size="medium"
                    @click="edit"
                    round
                  >
                    Edit
                  </el-button>
      
                </el-button-group>
              </div>

              <div class="text item">
              <el-form-item label="userPass" prop="userPass">
                <el-input
            v-model="ruleform1.userPassword"
            type="text"
            placeholder="Please enter a new password" 
           
          ></el-input></el-form-item>
        </div>
 

             
              <div class="text item">
                <el-form-item label="checkPass" prop="checkPass">
                <el-input
            v-model="ruleform1.checkPass"
            type="text"
            placeholder="Please enter a new password again" 
            show-word-limit
          ></el-input></el-form-item>

              </div>    


            </el-card>
            <el-card class="box-card">

              <div>
                <span style="float: left" shadow="hover"><b>Favorites List</b></span>
                <br />
                <span><h4> You can view your favorite products here! </h4></span>
                <br />
                
                <div class="like">

                   <li v-for="(item,index)  in productList" :key='item.productID'> ID:{{item.productID}} Product : {{item.productName}} Description: {{item.productDescription}} Price: {{item.moneyRequire}} 
                    <div class = 'delete'>
                      <el-button type="primary" size="mini"  v-on:click="dellike(index)" round>Delete</el-button> <el-button type="primary" size="mini"  v-on:click="jumpPage(item.productID)" round>View Product
</el-button>
                    </div>
                  </li>
                 

                 </div> 


     


                <el-divider></el-divider>
                <span></span>
              </div>
            </el-card>
            
          </el-main>
</el-form>
        </el-container>
        
      </el-container>
    </el-container>
         <br>
         <br>
      
  </div>

</template>
<script>
  import {info_api} from '@/api/api.js'
  import {get_fav} from '@/api/api.js'
  import {del_fav} from '@/api/api.js'

  export default {
    name: 'PersonalCenter',
    data() {
      return {
        userID: this.$store.state.userInfo.userID,
        userName: this.$store.state.userInfo.userName,
        moneyRemain:this.$store.state.userInfo.moneyRemain,
        ruleform: {
        text: '',
        userID: this.$store.state.userInfo.userID,
        userName: this.$store.state.userInfo.userName,
        userPassword:this.$store.state.userInfo.userPassword,
        userEmail: this.$store.state.userInfo.userEmail,
        userAddress:this.$store.state.userInfo.userAddress,
        circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        sizeList: ['large'],
        
        }, 

        ruleform1:{ 
        userID: this.$store.state.userInfo.userID,
        userName: this.$store.state.userInfo.userName,
        userEmail: this.$store.state.userInfo.userEmail,
        userAddress:this.$store.state.userInfo.userAddress,
        userPassword: '',
        checkPass:'',},
        productList:[],
    
      
      rules:{
        userName: [{ required: true, message: "Please enter your username", trigger: "blur" }],
        userEmail: [{ required: true, message: "Please enter your email", trigger: "blur" }],
        address: [{ required: true, message: "Please enter your address", trigger: "blur" }],
        userPassword: [{
          required: true,
          message: 'Creat Password',
          trigger: 'blur'
        }],
        checkPass: [{
          required: true,
          message: 'Comfirm Password',
          trigger: 'blur'
        }, {
          validator: (rule, value, callback) => {
            if (value === '') {
              callback(new Error('Please enter your password again'))
            } else if (value !== this.ruleform1.userPassword) {
              callback(new Error('Two times to enter the password does not match'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }],
      }
    }},
    mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#cad8ed')
    this.getproductList();


  },
  
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
    methods: {

      OnSubscribe: function() {
      var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                if (this.ruleform.userEmail != '' && !regEmail.test(this.ruleform.userEmail)) {
                    this.$message({
                        message: 'Email format error',
                        type: 'error'
                    })
                    this.ruleform.userEmail = ''
                }
            },
      logout(){
       
        alert('logout successful!')
        this.$router.push('/')
        this.$store.commit('setuserInfo','')
            
      },

      save() {
        var data = this.ruleform
        this.$refs.ruleformRef.validate((valid) => {
        if (valid){
        
        info_api(data).then((data) => {
          console.log(data);
          alert ('Save successfully')
          this.user = data.data
          this.$store.commit('setuserInfo',this.user)
        })
        .catch((err) => {
          console.log(err);
          alert ('Edit failed')
        })
        }})},

      edit(){
        var data =this.ruleform1
        this.$refs.ruleformRef1.validate((valid) => {
        if (valid){
        
        info_api(data).then((data) => {
          console.log(data);
          alert ('Change successfully')
        })
        .catch((err) => {
          console.log(err);
          alert ('Change failed')
        })
        }})

        },
      getproductList(){
          get_fav(this.userID).then((data) => {
          console.log(data)
          this.productList = data.data
        })
        .catch((err) => {
          console.log(err);
        })
        },
        dellike(index){
          del_fav(this.userID, this.productList[index].productID).then((data) => {
          console.log(data);
          alert ('Delete successfully')
          this.productList = data.data
          
        })
        .catch((err) => {
          console.log(err);
          alert ('Delete failed')
        })
        
        },

      jumpPage(id) {
      let trans = this.$router.push({
        name: "ProductDetail",
        params: {
          pushId: id,
        },
      });
    },
       
      }
      


    
  

  

    }

  
</script>
<style>
  .nav {
    height: 30%;
  }

  .logo{
    height: 8%;
    width: 8%;
  }
  .el-aside {
    line-height: 44px;
    text-align: center;
    background-color: #F8C874;
  }
  .el-main {
    line-height: 36px;
    text-align: center;
    background-color: #cad8ed;
  }

  .demo-basic--circle {
    align: 'center';
    margin-top: 30px;
    margin-left: 150px;
  }
  .block {
    margin-left: 25px;
    font-weight: bold;
  }
  .text {
    font-size: 14px;
  }
  .item {
    margin-bottom: 18px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }
  .clearfix:after {
    clear: both;
  }

  .box-card {
    width: 1000px;
    border-radius: 30px;
  }

  .service{
    height: 16%;
    width: 16%;
  }
  .car{
    height: 5.5%;
    width: 5.5%;
  }
  .user{
    height: 6%;
    width: 6%;
  }
  .login{
    height: 3%;
    width: 3%;
  }
  .register{
    height: 4%;
    width: 4%;
  }
.logo{
  height: 8%;
  width: 8%;
}
</style>

