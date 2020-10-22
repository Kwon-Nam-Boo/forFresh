<template>
  <div>
    <nav-bar :title="title"></nav-bar>
    <div></div>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
export default {
  components: {
    NavBar,
  },
  data() {
    return {
      title:"메인페이지",
    }
  },
};
</script>