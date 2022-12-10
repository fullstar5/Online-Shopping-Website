import {
  reqAddOrUpdateCart,
  reqCartList,
  reqDeleteCart,
  buyCart,
} from "@/api/api";
const state = {
  shopCartList: []
};
const mutations = {
  SHOPCARTLIST(state, value) {
    state.shopCartList = value;
  }
};
const actions = {
  // 删除购物车商品单个
  async setDeleteCartOne({ commit }, { userID, productID }) {
    let result = await reqDeleteCart(userID, productID);
    if (result.status == 200) {
      return "ok";
    } else {
      return "failed";
    }
  },
  // 获取购物车列表
  async getShopCartList({ commit }, userID) {
    let result = await reqCartList(userID);
    if (result.status == 200) {
      let temp = result.data;
      commit("SHOPCARTLIST", temp || []);
    }
  },
  // 购物车添加判断 解构赋值
  async getAddOrUpdateCart({ commit }, { userID, productID }) {
    let result = await reqAddOrUpdateCart(userID, productID);
    if (result.status == 200) {
      return "ok";
    } else {
      return "failed";
    }
  },

  // buyItem
  async buyCartMoney({ commit }, { userID, money_needed }) {
    let result = await buyCart(userID, money_needed);
    console.log(result,'result');
    if (result.data) {
      return "ok";
    } else {
      return "failed";
    }
  },



};

const getters = {};

export default {
  state,
  mutations,
  actions,
  getters
};
