<template>
  <div class="container">
    <div class="body">
      <div class="testID">
        Detail id:
        <Detail :id="productId" />
      </div>
    </div>
  </div>
</template>


<script>
import Detail from "@/pages/Detail/index.vue";

export default {
  components: {
    Detail,
  },
  data() {
    return {
      productId: this.$route.params.pushId,
    };
  },

  methods: {},
};
</script>

<style scoped>
.testID {
  color: #ffac38;
  text-align: center;
  font-size: 100px;
  bottom: 50%;
}
</style>
