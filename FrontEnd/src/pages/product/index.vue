<template>
  <div class ='container'>
    <div class="body">
      				<div class="" >
      					<!-- v-for to get list og product -->
      					<div class="contain" style="text-align:center;  padding-bottom: 8%;">
      					  <br>
      					  <br>
      					  <br>
      						<ul class="">
      							<!-- v-for key值 -->

      							<li class="toy_item" v-for='(toy,i) in dataList' :key='toy.productID' @click="jumpPage(toy.productID)">
      									<div class="toy_image">
      										<img :src="path+toy.image" width="70%" />
      									</div>
      									<div class="toy_title">{{toy.productName}}</div>
      									<p class="price">
      									  $
      										<span >{{toy.moneyRequire}}</span>
      									</p>
      									<p class="numberOfStock">
      									  Stock:
      									  <span>{{toy.numberOfStock}}</span>
      									</p>
      							</li>
      						</ul>
      					</div>
      				</div>

      			</div>
    </div>
  </div>
</template>


<script>
import { product_api } from "@/api/api.js";

export default {
  mounted() {
    this.getData();
    document
      .querySelector("body")
      .setAttribute("style", "background-color:#B0E0E6");
  },
  beforeDestroy() {
    document.querySelector("body").removeAttribute("style");
  },
  data() {
    return {
      dataList: [],
      path: "../static/Image/",
    };
  },

  methods: {
    // get data from back-end
    getData() {
      this.$ajax
        .post("/product/findAll")
        .then((data) => {
          this.dataList = data.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // push product id to product detail page
    jumpPage(id) {
      let trans = this.$router.push({
        name: "ProductDetail",
        params: {
          pushId: id,
        },
      });
    },
  },
};
</script>

<style scoped>
.body{
  position:relative;
  width:100%;
  bottom: 70px;
}
.toy_item {
  display: block;
  float: left;
  width: 30%;
  height: 30%;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 15px;
  margin-left: 2.5%;
  margin-top: 30px;
}

.toy_image {
  text-align: center;
  margin: 10px 0;
}

.toy_title {
  color: #000000;
  text-align: center;
  font-size: 20px;
  margin-bottom: 5px;
}

.price {
  color: #ffac38;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin: 10px 0;
}

.numberOfStock {
  color: #696969;
  text-align: right;
  font-size: 15px;
  margin-bottom: 2%;
  margin-right: 3%;
}
</style>
