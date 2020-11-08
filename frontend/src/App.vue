<template>
  <v-app>
    <nav-bar v-if="title" :title="title" @getUserInfo="getUserInfo"></nav-bar>
    <router-view
      :key="$route.fullPath"
      @updateTitle="updateTitle"
      :userInfo="userInfo"
    ></router-view>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar";
export default {
  name: "App",
  components: {
    NavBar,
  },
  data: () => ({
    title: null,
    userInfo: {},
  }),
  methods: {
    getUserInfo(user) {
      // console.log(user);
      this.userInfo = user;
    },
    updateTitle(title) {
      this.title = title;
    },
  },
};
</script>
<style lang="scss">
@import "@/assets/css/main.scss";
</style>
