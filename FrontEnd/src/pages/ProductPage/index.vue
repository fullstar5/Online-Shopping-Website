<template>
  <div class ='container'>
    <div class="body" style ="position:absolute; left:0; top:15%">
      <br>
      <Product></Product>
      <br>
      <br>
      <br>
    </div>
  </div>
</template>


<script>
  import Product from '@/pages/product';
  export default{
  components: {
          Product,
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#B0E0E6')
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  }
</script>
