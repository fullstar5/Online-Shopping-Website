<template>
  <div class="shopcart-container">
    <!-- shopcart non-empty -->
    <template v-if="shopCartList.length">
      <div class="cart">
        <h4>Shopping Cart</h4>
        <div class="cart-main">
          <div class="cart-th">
            <div class="cart-th2">item</div>
            <div class="cart-th3">description</div>
            <div class="cart-th4">price</div>
            <div class="cart-th5">operation</div>
          </div>
          <div class="cart-body">
            <ul
              class="cart-list"
              v-for="(shopCart, index) in shopCartList"
              :key="index"
            >
              <li class="cart-list-con2">
                <img :src="path+shopCart.image" />
                <div class="item-msg">
                  {{ shopCart.productName }}
                </div>
              </li>
              <li class="cart-list-con3">
                <!-- price -->
                <span class="price">{{ shopCart.productDescription }}</span>
              </li>
              <li class="cart-list-con4">
                <!-- price -->
                <span class="price">{{ shopCart.moneyRequire }}</span>
              </li>
              <!-- <li class="cart-list-con5">
                <a
                  href="javascript:void(0)"
                  class="mins"
                  @click="
                    changeCartNum(shopCart, true, -1, $event, shopCart.skuNum)
                  "
                  >-</a
                >
                <input
                  autocomplete="off"
                  type="text"
                  :value="shopCart.skuNum"
                  @change="
                    changeCartNum(
                      shopCart,
                      false,
                      $event.target.value * 1,
                      $event,
                      shopCart.skuNum
                    )
                  "
                  minnum="1"
                  class="itxt"
                />
                <a
                  href="javascript:void(0)"
                  class="plus"
                  @click="
                    changeCartNum(shopCart, true, 1, $event, shopCart.skuNum)
                  "
                  >+</a
                >
              </li> -->
              <!-- <li class="cart-list-con6">
                <span class="sum">{{
                  shopCart.cartPrice * shopCart.skuNum
                }}</span>
              </li> -->
              <li class="cart-list-con5">
                <a
                  href="javascript:;"
                  class="sindelet"
                  @click="deleteOne(userInfo.userID, shopCart.productID)"
                  >delete</a
                >
                <br />
              </li>
            </ul>
          </div>
        </div>
        <div class="cart-tool">
          <div class="money-box">
            <div class="chosed">
              subtotal <span>({{ totalAmount }})</span>items
            </div>
            <div class="sumprice">
              <i>Total（FREE Shipping） ：$</i>
              <b class="summoney">{{ totalAccount }}</b>
            </div>
            <div class="sumbtn">
              <a class="sum-btn" @click="toTrade">proceed to check out</a>
            </div>
          </div>
        </div>
      </div>
    </template>
    <!-- shopcart empty -->
    <template v-else>
      <div class="empty-cart">
        <!-- image area -->
        <div class="img">
          <img src="./images/emptyCart.png" alt="空购物车" />
        </div>
        <div class="info">
          <div class="title">Your monbrear cart is empty</div>
          <router-link to="/" class="toshop">Shop Now!</router-link>
        </div>
      </div>
    </template>
  </div>
</template>
  
<script>
import { mapState } from "vuex";
export default {
  name: "ShopCart",
  data() {
    return {
      userInfo: window.localStorage.getItem("userInfo")
        ? JSON.parse(window.localStorage.getItem("userInfo"))
        : "",
        path:"../static/Image/",
    };
  },
  mounted() {
    document.body.style.backgroundColor = "#6892CC";
    this.getShopCartList();
  },
  methods: {
    async toTrade() {
      if (!this.userInfo) {
        this.$router.push("/login");
        return;
      }

      var result = await this.$store.dispatch("buyCartMoney", {
        userID: this.userInfo.userID,
        money_needed: this.totalAccount,
      });

      if (result == "ok") {
        this.$message.success({
          message: "congratulations",
          duration: 1000,
        });
        // ! 购买了  再次  查询列表
        this.getShopCartList();

      } else {
        this.$message.error({
          message: "Insufficient Balance",
          duration: 1000,
        });
      }
    },

    async deleteAll() {
      try {
        await this.$store.dispatch("setDeleteCartAll");
        this.getShopCartList();
      } catch (error) {
        this.$message.error(error.message);
      }
    },
    // delete
    async deleteOne(userID, productID) {
      try {
        await this.$store.dispatch("setDeleteCartOne", { userID, productID });

        this.getShopCartList();
      } catch (error) {
        this.$message.error("DeleteFail");
        console.log(error.message);
      }
    },
    //change shopcart num
    async changeCartNum(shopCart, flag, endNumber, event, originNumber = 0) {
      let changeNumber;
      if (!flag) {
        if (!parseInt(endNumber)) {
          event.target.value = originNumber;
          return;
        }
        if (endNumber <= 0) {
          changeNumber = 1 - shopCart.skuNum;
        } else {
          changeNumber = endNumber - shopCart.skuNum;
        }
      } else {
        if (endNumber <= 0) {
          if (originNumber === 1) {
            return;
          }
          changeNumber = 1 - shopCart.skuNum;
        } else {
          changeNumber = endNumber;
        }
      }
      //post to backEnd
      try {
        await this.$store.dispatch("getAddOrUpdateCart", {
          skuId: shopCart.skuId,
          skuNum: changeNumber,
        });
      } catch (error) {
        this.$message.error("Fail to Detele" + error.message);
      }
      this.getShopCartList();
    },
    getShopCartList() {
      if (!this.userInfo) {
        this.$router.push({
          path: "/login",
        });
        return;
      }
      this.$store.dispatch("getShopCartList", this.userInfo.userID);
    },
  },
  computed: {
    ...mapState({
      shopCartList: (state) => state.shopcart.shopCartList,
    }),
    totalAccount() {
      return this.shopCartList.reduce((prev, shop) => {
        // prev += shop.cartPrice * shop.skuNum;
        prev += shop.moneyRequire;
        return prev;
      }, 0);
    },
    totalAmount() {
      return this.shopCartList.length;

      // return this.shopCartList.reduce((prev, shop) => {
      //   prev += shop.skuNum;
      //   return prev;
      // }, 0);
    },
  },
};
</script>
  
<style lang="less" scoped>
.empty-cart {
  width: 600px;
  margin: 0 auto;
  text-align: center;
  height: 350px;
  border: 1px solid #eee;
  border-radius: 10px;
  box-shadow: 0 0 50px 5px rgba(0, 0, 0, 0.2);
  margin-bottom: 500px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  background: #f8af8f;
  position:fixed;
  top: 200px;
  left: 25%;
  .info {
    .title {
      font-size: 16px;
    }

    .toshop {
      font-size: 20px;
      color: rgb(141, 141, 218);
    }
  }
}

.cart {
  width: 1200px;
  margin: 0 auto;

  h4 {
    margin: 9px 0;
    font-size: 14px;
    line-height: 21px;
  }

  .cart-main {
    .cart-th {
      background: #f8af8f;
      border: 1px solid #ddd;
      padding: 10px;
      overflow: hidden;
      width: 50%;
      position: relative;
      top: 100px;
      & > div {
        float: left;
      }

      .cart-th2 {
        width: 25%;
      }

      .cart-th3{
        width: 30%;
      }
      .cart-th4{
        width: 25%;
      }
      .cart-th5{
        width: 12.5%;
      }
    }

    .cart-body {
      width: 50%;
      margin: 15px 0;
      border: 1px solid #ddd;
      background: #f8af8f;
      position: relative;
      top: 100px;
      .cart-list {
        padding: 10px;
        border-bottom: 5px solid #ddd;
        overflow: hidden;

        & > li {
          float: left;
        }

        .cart-list-con1 {
          // width: 4.1667%;
          width: 13%;
        }

        .cart-list-con2 {
          width: 25%;

          img {
            width: 82px;
            height: 82px;
            float: left;
          }

          .item-msg {
            float: left;
            width: 150px;
            margin: 0 10px;
            line-height: 18px;
          }
        }

        .cart-list-con3 {
          // width: 12.5%;
          width: 30%;
        }

        .cart-list-con4 {
          // width: 12.5%;
          width: 25%;

          input {
            border: 1px solid #ddd;
            width: 40px;
            height: 31px;
            float: left;
            text-align: center;
            font-size: 14px;
          }
        }

        .cart-list-con5 {
          width: 12.5%;
        }

        .cart-list-con7 {
          // width: 12.5%;
          width: 13%;

          a {
            color: #666;
          }
        }
      }
    }
  }

  .cart-tool {
    overflow: hidden;
    border: 1px solid #ddd;
    position: absolute;
    width: 35%;
    right: 50px;
    top: 120px;
    float: right;
    height: 350px;
    background: #f8af8f;
    position: fixed;
    top: 200px;

    .money-box {
      float: right;

      .chosed {
        line-height: 26px;
        float: left;
        padding: 0 10px;
      }

      .sumprice {
        width: 252px;
        line-height: 22px;
        float: left;
        padding: 0 10px;
        position: absolute;
        bottom: 20%;
        right: 25%;

        .summoney {
          color: #c81623;
          font-size: 16px;
        }
      }

      .sumbtn {
        float: right;

        a {
          display: block;
          position: absolute;
          width: 50%;
          height: 52px;
          left: 25%;
          bottom: 0px;
          line-height: 52px;
          color: #fff;
          text-align: center;
          font-size: 18px;
          font-family: "Microsoft YaHei";
          background: #f6b243;
          overflow: hidden;
        }
      }
    }
  }
}
</style>