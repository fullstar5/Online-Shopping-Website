import Vue from "vue";
import vuex from "vuex";
//modularization
import shopcart from "./modules/shopcart";
import detail from "./modules/detail";

Vue.use(vuex);
const state = {
  userInfo: JSON.parse(localStorage.getItem("userInfo")) || {}
};
const mutations = {
  setuserInfo(state, v) {
    localStorage.setItem("userInfo", JSON.stringify(v));
    state.userInfo = v;
  }
};
const actions = {};
const getter = {};

const store = new vuex.Store({
  mutations,
  state,
  actions,
  getter,
  modules: {
    shopcart,
    detail
  }
});
export default store;
