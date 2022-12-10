import Vue from "vue";
import Router from "vue-router";
import Login from "@/pages/login/index";
import Four from "@/pages/404/index";
import Home from "@/pages/home/index";
import Register from "@/pages/register/index";
import Information from "@/pages/information/index";
import Product from "@/pages/product/index";
import ProductPage from "@/pages/ProductPage/index";
import Service from "@/pages/service/index";
import ShopCart from "@/pages/ShopCart/index";
import Metaverse from "@/pages/metaversePage/index";
import Header from "@/components/Header/index";
import ProductDetail from "@/pages/ProductDetail/index";

Vue.use(Router);

export const constantRoutes = [
  {
    path: "/login",
    name: "login",
    component: Login,
    meta: {
      alive: true
    }
  },
  {
    path: "/register",
    name: "register",
    component: Register,
    meta: {
      alive: true
    }
  },
  {
    path: "/404",
    name: "404",
    component: Four,
    meta: {
      alive: true
    }
  },
  {
    path: "/information",
    name: "information",
    component: Information,
    meta: {
      alive: true
    }
  },

  {
    path: "/",
    name: "home",
    component: Home,
    meta: {
      alive: true
    }
  },

  {
    path: "/product",
    name: "product",
    component: Product
  },
  {
    path: "/product/:pushId",
    name: "ProductDetail",
    component: ProductDetail,
    meta: {
      alive: true
    }
  },
  {
    path: "/productPage",
    name: "productPage",
    component: ProductPage,
    meta: {
      alive: true
    }
  },
  {
    path: "/metaverse",
    name: "metaverse",
    component: Metaverse,
    meta: {
      alive: true
    }
  },
  {
    path: "/Header",
    name: "Header",
    component: Header
  },
  {
    path: "/service",
    name: "service",
    component: Service,
    meta: {
      alive: true
    }
  },
  {
    path: "/ShopCart",
    name: "ShopCart",
    component: ShopCart,
    meta: {
      alive: true
    }
  },

  {
    path: "/Detail",
    name: "Detail",
    component: ProductDetail,
    meta: {
      alive: true
    }
  }
];
const router = new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
});

//router.beforeEach((to, from, next) => {
//if (to.path === '/login') return next()
//const tokenStr = window.sessionStorage.getItem('token')
//if (!tokenStr) return next('/login')
//next()
//})

export default router;
