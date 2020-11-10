<template>
  <div>
    <CategoryList />
    <ProductHome />
  </div>
</template>

<script>
import CategoryList from "../../components/product/CategoryList";
import ProductHome from "../../components/product/ProductHome";

export default {
  components: {
    CategoryList,
    ProductHome,
  },
  data() {
    return {
      title: "상품페이지",
    };
  },
  created() {
    this.$emit("updateTitle", "상품페이지");
  },
  methods: {},
};
</script>

<style lang=""></style>
