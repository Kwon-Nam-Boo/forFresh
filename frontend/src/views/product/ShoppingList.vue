<template>
  <div>
    장바구니페이지
    {{ $route.params.email }}
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "장바구니",
    };
  },
  created() {
    this.$emit("updateTitle", this.title);
  },
};
</script>
<style lang="">
</style>