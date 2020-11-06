<template>
  <div>
    <CategoryList @categoryList="category" />
  </div>
</template>

<script>
import CategoryList from "../../components/product/CategoryList";
export default {
  components: {
    CategoryList,
  },
  data() {
    return {
      title: "상품페이지",
      category: "",
      keyword: "",
    };
  },
  created() {
    this.$emit("updateTitle", "상품페이지");
  },
  methods: {
    categoryList(category) {
      this.category = category;
    },
  },
};
</script>

<style lang=""></style>
