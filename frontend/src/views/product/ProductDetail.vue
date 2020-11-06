<template>
  <div></div>
</template>
<script>
import ProductApi from "../../api/ProductApi";

export default {
  data() {
    return {
      productNo: "",
      productInfo: {},
    };
  },
  created() {
    this.productNo = this.$route.params.productno;
    ProductApi.requestProductDetail(
      this.productNo,
      (res) => {
        console.log(res.data);
        this.productInfo = res.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style lang=""></style>
