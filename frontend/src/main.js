import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import routes from "./routes";
import store from "./vuex/store";
import vuetify from "./plugins/vuetify";
import VueSession from 'vue-session';

// hostname setting
// const hostname = 'localhost';
const hostname = 'k3a407.p.ssafy.io';
const BASE_URL = 'http://' + hostname + ':8080';
export default BASE_URL ;


Vue.config.productionTip = false;

Vue.use(VueRouter);
var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)

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
