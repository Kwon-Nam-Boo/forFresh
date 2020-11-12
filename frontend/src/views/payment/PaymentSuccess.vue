<template>
  <v-container class="text-center fill-height">
    <v-row class="align-center">
      <v-col>
        <img src="@/assets/img/isnoitem.png" width="200" />
        <h1 class="display-2 primary--text">결제 성공!</h1>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      title: "결제 성공페이지",
    };
  },
  created() {
    this.$emit("updateTitle", this.title);
  },
};
</script>
<style lang="">
</style>