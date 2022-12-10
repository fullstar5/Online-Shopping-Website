<template>
  <div class ='container'>
    <div class="body">
      <div class="login-container">
         <el-form label-position="left">

          <div class="content" style="background-color:rgba(255,255,255,0.5); width:65%; position:absolute; left:23%; top:15%; color:rgba(104,146,204);">
              <br>
   						<ul class="">
      							<!-- v-for key值 -->
      							<li class="toy_item" v-for='(toy,i) in dataList' :key='toy.productID' @click="jumpPage(toy.productID)">
      								<a href="#">
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
      								</a>
      							</li>
      						</ul>
          </div>

          <div id="menu" style="background-color:rgba(255,169,10); font-size:25px; text-align:center; height:5.5%; width:20%; position:fixed; top:21%; left:2%; color:#fff;">
            <b>Classification</b>
          </div>

          <div id="menu" style="background-color:rgba(255,169,10); font-size:20px; border-radius: 30px; width:20%; position:fixed; top:26%; left:2%; color:#fff;">
            <div style="margin-top: 2%;">
              <div class="inner" style="margin-left: 5%;">
                <h3> Age </h3>
              </div>
              <div class="inner" style="margin-left: 25%; line-height:2;">
                <d @click = "Age03"> 0 - 3 </d><br>
                <d @click = "Age46"> 4 - 6 </d><br>
                <d @click = "Age710"> 7 - 10 </d><br>
                <d @click = "Age1115"> 11 - 15 </d><br>
                <d @click = "Age1618"> 16 - 18 </d><br>
              </div>
            </div>
            <div style="margin-top: 10px;">
              <div class="inner"  style="margin-left: 5%;">
                <h3> Material </h3>
              </div>
              <div class="inner" style="margin-left: 9%; line-height:2;">
                <d @click = "Plastic"> Plastic </d><br>
                <d @click = "Wooden"> Wooden </d><br>
                <d @click = "Plush"> Plush </d><br>
              </div>
            </div>
          </div>
        </el-form>

        <div>
          <div style="position:fixed; top:70%; left:5%; z-index:10">
            <img @click = "service" class="service" id="Service" style="margin-right: 50px;" src="./image/service.png" alt="Service" />
          </div>
          <div style="position:fixed; top:68%; left:80%; z-index:10">
            <img @click = "metaverse" class="metaverse" id="Service" style="margin-right: 50px;" src="./image/metaverse.png" alt="Service" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
  import {product_api} from '@/api/api.js'
  import {productAge_api} from '@/api/api.js'
  import {findMaterial_api} from '@/api/api.js'
  import Product from '@/pages/product';

  export default{
  components: {
    Product,
  },

  data(){
    return {
      dataList:[],
      path: "../static/Image/",
    }
  },

  methods:{
    // get data from back-end
    getData(){
      this.$ajax.post('/product/findAll').then((data)=>{
      this.dataList = data.data;
      }
      ).catch((err)=>{
        console.log(err)
      })
    },

    // push product id to product detail page
    jumpPage(id){
      let trans = this.$router.push({
        name: 'ProductDetail',
        params:{
          pushId: id
        }
      })
    },

    service (){
      window.open(this.$router.resolve('/service').href, '_blank');
    },
    metaverse (){
      this.$router.push('/metaverse');
    },


    Age03(){
      this.dataList = [];
      productAge_api("0-3").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Age46(){
      this.dataList = [];
      productAge_api("4-6").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Age710(){
      this.dataList = [];
      productAge_api("7-10").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Age1115(){
      this.dataList = [];
      productAge_api("11-15").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Age1618(){
      this.dataList = [];
      productAge_api("16-18").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },

    Plastic(){
      this.dataList = [];
      findMaterial_api("Plastic").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Wooden(){
      this.dataList = [];
      findMaterial_api("Wooden").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
    Plush(){
      this.dataList = [];
      findMaterial_api("Plush").then((data)=>{
        this.dataList = data.data;
        }
        ).catch((err)=>{
        console.log(err)
      })
    },
  },
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#B0E0E6')
    this.getData()
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  }
</script>

<style scoped>
  .content{
    padding-bottom: 8%;
  }
  .container{
    color: #6892CC;
    height: 100%;
    weight: 100%;
  }
  .el-form-item{
    margin: 0 auto;
  }
  .service{
    height: 15%;
    width: 15%;
  }

  .metaverse{
    height: 90%;
    width: 90%
  }

  .inner {
    display: inline-block;
    vertical-align: middle;
   }

  .toy_item{
  	display: block;
  	float: left;
  	width: 30%;
  	height: 30%;
  	background-color: rgba(255,255,255,0.6);
  	border-radius:15px;
  	margin-left: 2.5%;
  	margin-top: 30px;
  }

  .toy_image{
  	text-align: center;
  	margin: 10px 0;
  }

  .toy_title{
  	color: #000000;
  	text-align: center;
  	font-size: 20px;
  	margin-bottom: 5px;
  }

  .price{
  	color: #ffac38;
  	text-align: center;
  	font-size: 20px;
  	font-weight: bold;
  	margin: 10px 0;
  }

 .numberOfStock{
   color: #696969;
   text-align: right;
   font-size: 15px;
   margin-bottom: 2%;
   margin-right: 3%;
 }
</style>
