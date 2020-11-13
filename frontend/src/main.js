import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import routes from "./routes";
import store from "./vuex/store";
import vuetify from "./plugins/vuetify";
import VueSession from "vue-session";
import * as firebase from "firebase/app";
import "firebase/auth";
import "firebase/database";

// hostname setting
// const hostname = 'localhost';
const hostname = "k3a407.p.ssafy.io";
// const hostname = "localhost:8080";
const BASE_URL = "http://" + hostname + "/api";
export default BASE_URL;

Vue.config.productionTip = false;
firebase.initializeApp({
  apiKey: "AIzaSyDNTrL7pTJ259mmU1qc8a4u6rlZNAQMYYE",
  authDomain: "forfresh-ea84c.firebaseapp.com",
  databaseURL: "https://forfresh-ea84c.firebaseio.com",
  projectId: "forfresh-ea84c",
  storageBucket: "forfresh-ea84c.appspot.com",
  messagingSenderId: "92658353742",
  appId: "1:92658353742:web:7f459c13e4c9cc1979a51f",
  measurementId: "G-NPBXKL6C57",
});

Vue.use(VueRouter);
var sessionOptions = {
  persist: true,
};
Vue.use(VueSession, sessionOptions);

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
