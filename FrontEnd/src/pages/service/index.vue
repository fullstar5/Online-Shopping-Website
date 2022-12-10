<template>
  <div class="body" style="position:absolute; top:25%; text-align:center;">
    <img id="Service" style="margin-right: 50px;" src="./image/service.png" alt="Service" />
  </div>
</template>


<script>
  export default{
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#B0E0E6')
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  }
</script>

<style scoped>
  img{
    height: 40%;
    width: 40%;
  }

</style>

