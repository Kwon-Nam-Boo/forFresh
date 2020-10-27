<template>
  <v-app>
    <nav-bar v-if='title' :title='title'></nav-bar>
    <router-view @updateTitle = "updateTitle"></router-view>
  </v-app>
</template>

<script>
import NavBar from './components/NavBar'
export default {
  name: 'App',
  components: {
    NavBar,
  },
  data: () => ({
    title: "",
  }),
  methods: {
    updateTitle(title) {
      this.title = title;
    }
  }
};
</script>
<style lang="scss">
@import "@/assets/css/main.scss";
</style>
