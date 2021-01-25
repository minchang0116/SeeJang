import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

import Login from "@/page/Login.vue"
import HelloWorld from "@/page/HelloWorld.vue"
import SearchProduct from "@/components/SearchProduct.vue"
// import SearchProduct from "@/components/SearchProduct.vue"
Vue.use(VueRouter);

const routes = [
  { 
    path: '/',
    name: 'hello',
    component: HelloWorld,
  },
  { 
    path: '/serachProduct',
    name: 'serachProduct',
    component: SearchProduct,
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
