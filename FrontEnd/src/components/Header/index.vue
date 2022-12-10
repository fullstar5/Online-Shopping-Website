<template>
  <div class ='header' style="background-color:rgba(104,146,204); position:fixed; z-index:10; height:18%; width:100%; top:0; left:0">
         <div style="position:fixed; text-align:right; z-index:20; top:1%">
           <img @click = "user" class="user" id="User" src="./images/user.png" alt="User" />
           <img @click = "product" class="product" style="margin-left: 3.5%;" id="Product" src="./images/product.png" alt="Product" />

           <img @click = "home" id="Logo" style="margin-left: 20%;" src="./images/logo.png" alt="Logo" />

           <img @click="shopcart" class="car"  id="ShoppingCar" style="margin-left: 15%;" src="./images/shoppingCar.png" alt="ShoppingCar" />
           <img @click="register" class="register" id="Register" style="margin-left: 3%; margin-right: 3%;" src="./images/register.png" alt="Register" />
           <img @click="login" class="login" id="Login" style="margin-left: 1%; margin-right: 3%;" src="./images/login.png" alt="Login" />
         </div>
  </div>
</template>

<script>
  export default{
  methods:{
      login (){
        console.log("turn to login page");
        this.$router.push('/login');
      },
      home (){
        this.$router.push('/');
      },
      register (){
        this.$router.push('/register');
      },
      user (){
        this.$router.push('/information');
      },
      product (){
        this.$router.push('/productPage');
      },
      shopcart (){
        this.$router.push('/ShopCart');
      },
  }
  }
</script>

<style scoped>
  .service{
    height: 16%;
    width: 16%;
  }
  .car{
    height: 9%;
    width: 9%;
  }
  .user{
    height: 7.5%;
    width: 7.5%;
  }
  .product{
    height: 8.5%;
    width: 8.5%;
  }

  .login{
    height: 4.8%;
    width: 4.8%;
  }
  .register{
    height: 6%;
    width: 6%;
  }
  img{
    height: 12%;
    width: 12%;
  }
</style>
