<template>
  <div>
    <Header v-if="$route.meta.alive"></Header>
    <router-view></router-view>
    <Footer v-if="$route.meta.alive"></Footer>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
//引入swipercss

// ajax
export default {
  name: "App",
  mounted(){
  },
  components: {
    Header,
    Footer,
  },
};
</script>

<style>
</style>
