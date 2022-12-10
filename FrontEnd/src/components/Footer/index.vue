<template>
    <tr>
      <td id="footer" cosplay="2" style="background-color:rgba(255,169,10);  color:#fff; margin-top: 2%; height:6%; width:100%; text-align:center; position:fixed; left:0; bottom:0px; ">
      IT Project (Team: Vault 32)</td>
    </tr>
</template>

<script>
  export default{
  methods:{
      login (){
        console.log("turn to login page");
        this.$router.push('/login');
      },
  }
  }
</script>

<style scoped>
  img{
    height: 12%;
    width: 12%;
  }
</style>
