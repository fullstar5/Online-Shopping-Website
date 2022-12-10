import axios from "@/utils/axios";

const qs = require("qs");

export const post_user_login_api = data => {
    return axios({
        url: "/user/login",
        method: "POST",
        data
    });
};

export const register_api = data => {
    return axios({
        url: "/user/registerUser",
        method: "POST",
        data
    });
};

export const info_api = data => {
    return axios({
        url: "/user/updaterUser",
        method: "POST",
        data
    });
};

export const product_api = data => {
    return axios({
        url: "/product/findAll",
        method: "POST",
        data
    });
};

export const productAge_api = (suitableAge) => {
    return axios({
        url: "product/findByAgeRange",
        method: "POST",
        params: {
            suitableAge
        }
    });
};
export const findMaterial_api = (productMaterial) => {
    return axios({
        url: "product/findByMaterial",
        method: "POST",
        params: {
            productMaterial
        }
    });
};
export const reqDetailInfo = productID => {
    return axios({
        url: "/product/lookByID",
        method: "POST",
        params: {
            productID
        }
    });
};
export const reqAddOrUpdateCart = (userID, productID) => {
  return axios({
    url: "/user/addToShoppingCart",
    method: "POST",
    params: {
      userID,
      productID
    }
  });

  // return axios.post("/user/addToShoppingCart/" + userID + "/" + skuNum);
};

export const reqCartList = userID => {
  return axios({
    url: "/user/getShoppingCart",
    method: "POST",
    params: {
      userID
    }
  });

  // return axios.get("/user/getShoppingCart");
};

export const reqDeleteCart = (userID, productID) => {
  return axios({
    url: "/user/deleteFromShoppingCart",
    method: "POST",
    params: {
      userID,
      productID
    }
  });

  // return axios.delete(`/user/deleteFromShoppingCart/${skuID}`);
};

export const buyCart = (userID, money_needed) => {
  return axios({
    url: "/user/buyItem",
    method: "POST",
    params: {
      userID,
      money_needed
    }
  });
};

export const add_fav = (userID, productID) => {
    return axios({
        url: "user/addFavorite",
        method: "POST",
        params: {
            userID,
            productID
        }
    });
};

export const get_fav = (userID) => {
    return axios({
        url: "user/getFavorite",
        method: "POST",
        params: {
            userID
        }
    });
};

export const del_fav = (userID, productID) => {
    return axios({
        url: "user/deleteFromFavoriteList",
        method: "POST",
        params: {
            userID,
            productID
        }
    });
};

