<template>
  <div></div>
</template>
<script>
export default {
  data() {
    return {
      productNo: "",
    };
  },
  created() {
    this.productNo = this.$route.params.productno;
  },
};
</script>

<style lang=""></style>
