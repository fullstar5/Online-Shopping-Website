<template>
  <div class="detail">
    <!-- <Nav /> -->

    <section class="con">
      <div class="mainCon">
        <div class="previewWrap">
        <img :src="path+skuInfo.image"/>
        </div>
        <div class="InfoWrap">
          <div class="goodsDetail">
            <h3 class="InfoName">{{ skuInfo.productName }}</h3>
            <p class="news">{{ skuInfo.productDescription }}</p>
            <div class="priceArea">
              <div class="priceArea1">
                <div class="title">Price</div>
                <div class="price">
                  <i>¥</i>
                  <em>{{ skuInfo.moneyRequire }}</em>
                </div>
              </div>
              <div class="priceArea2">
                <div class="title">
                  <i>Suitable&nbsp;&nbsp;Age</i>
                </div>
                <div class="fixWidth">
                  <em class="t-gray">{{ skuInfo.suitableAge }}</em>
                </div>
              </div>
            </div>
            <div class="support">
              <div class="supportArea">
                <div class="title">Number&nbsp;of&nbsp;Stock</div>
                <div class="fixWidth">{{ skuInfo.numberOfStock }}</div>
              </div>
              <div class="supportArea">
                <div class="title">Product&nbsp;&nbsp;Material</div>
                <div class="fixWidth">{{ skuInfo.productMaterial }}</div>
              </div>
            </div>
          </div>

          <div class="choose">
            <div class="cartWrap">

              <div class="add">
                <a href="javascript:;" @click="addCart">AddToCart</a>
              </div>
              <div class="addfav">
                <a href="javascript:;" @click="addFavorite">AddToFavorites</a>
              </div>
            </div>
          </div>
        </div>

        
      </div>
    </section>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import {add_fav} from '@/api/api.js';
export default {
  name: "Detail",
  components: {},
  data() {
    return {
      defaultChecked: 0,
      //skunum = 1
      skuNum: 1,

      skuId: this.$route.params.pushId,
      userInfo: window.localStorage.getItem("userInfo")
      ? JSON.parse(window.localStorage.getItem("userInfo"))
      : "",
      path:"../static/Image/",
      };
},
mounted() {
    document.body.style.backgroundColor = "#B0E0E6";
  },
  created() {
    //sent ajax
    this.getSkuDetailInfo(); 
  },
  computed: {
    //avoid wrong error
    skuImageList() {
      return this.skuInfo.skuImageList || [];
    },

    ...mapGetters(["categoryView", "skuInfo", "spuSaleAttrList"]),
  },

  methods: {

    userInputShopCount(event) {
      let value = event.target.value; //get user input
      let parseValue = parseInt(value); 
      if (parseValue && parseValue > 0) {
        this.skuNum = parseValue;
      } else {
        event.target.value = this.skuNum;
      }
    },
    async addCart() {
      if (!this.userInfo) {
        this.$router.push({
          path: "/login",
        });
        return;
      }

      try {
        let result = await this.$store.dispatch("getAddOrUpdateCart", {
          productID: Number(this.skuId),
          userID: Number(this.userInfo.userID),
        });

        if (result == "ok") {
          this.$message.success({
            message: "Added to shopping cart",
            duration: 1000,
          });
          this.$router.push({
            path: "/ShopCart",
          });
        }
      } catch (error) {
        this.$message.error("failed to add" + error.message);
      }
    },
    userInputShopCount(event) {
      let value = event.target.value; //get user input
      let parseValue = parseInt(value); 
      if (parseValue && parseValue > 0) {
        this.skuNum = parseValue;
      } else {
        event.target.value = this.skuNum;
      }
    },

    async addFavorite() { 
        add_fav(this.userInfo.userID, this.skuId).then((data) => {
          console.log(data);
          alert ('Save successfully')
        })
        .catch((err) => {
          console.log(err);
          alert ('Edit failed')
        })
        
    },

    changeChecked(nowData, allData) {
      allData.forEach((item) => {
        item.isChecked = "0";
      });
      nowData.isChecked = "1";
    },
    getSkuDetailInfo() {
      this.$store.dispatch("getDetailInfo", this.skuId);
    },
  },
};
</script>

<style lang="less" scoped>
.detail {
  .con {
    width: 1200px;
    margin: 15px auto 0;

    .mainCon {
      overflow: hidden;
      margin: 5px 0 15px;

      .previewWrap {
        float: left;
        width: 400px;
        position: relative;
        top: 25px;
      }
      img {
            width: 500px;
            height: 450px;
            float: left;
          }

      .InfoWrap {
        width: 550px;
        float: right;
        background: #f8af8f;
        height: 380px;
        position: relative;
        top: 25px;
        .InfoName {
          font-size: 30px;
          line-height: 21px;
          margin-top: 15px;
          color: #fff;
        }

        .news {
          color: #fff;;
          margin-top: 15px;
          font-size: 20px;
        }

        .priceArea {
          padding: 7px;
          margin: 13px 0;

          .priceArea1 {
            overflow: hidden;
            line-height: 28px;
            margin-top: 10px;

            .title {
              float: left;
              margin-right: 15px;
              font-size: 16px;
              color: #fff;
            }

            .price {
              

              i {
                font-size: 16px;
                color: #fff;
              }

              em {
                font-size: 16px;
                font-weight: 700;
                color: #fff;
              }

              span {
                font-size: 16px;
                color: #fff;
              }
            }
          }

          .priceArea2 {
            overflow: hidden;
            line-height: 28px;
            margin-top: 10px;

            .title {
              margin-right: 15px;
              float: left;
              font-size: 16px;
              color: #fff;
            }

            .fixWidth {
              width: 520px;
              float: left;
              font-size: 16px;
              color: #fff;
            }
          }
        }

        .support {
          border-bottom: 1px solid #ededed;
          padding-bottom: 5px;

          .supportArea {
            overflow: hidden;
            line-height: 28px;
            margin-top: 10px;

            .title {
              margin-right: 15px;
              float: left;
              font-size: 16px;
              color: #fff;
              
            }

            .fixWidth {
              width: 520px;
              float: left;
              font-size: 16px;
              color: #fff;
            }
          }
        }
        .choose {
        .cartWrap {

            .add {
              float: left;
              width: 200px;

              a {
                background-color: #e1251b;
                padding: 0 25px;
                font-size: 16px;
                color: #fff;
                height: 36px;
                line-height: 36px;
                display: block;
              }
            }
            .addfav {
              float: left;
              width: 200px;
              position: relative;
              left: 150px;

              a {
                background-color: #e1251b;
                padding: 0 25px;
                font-size: 16px;
                color: #fff;
                height: 36px;
                line-height: 36px;
                display: block;
              }
            }
          }
        }
      }
    }
  }
}
</style>