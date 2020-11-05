<template>
  <div>
    <v-form class="mx-2 mt-7" onsubmit="return false;">
      <v-text-field
        rounded
        solo
        dense
        label="식품 검색"
        append-icon="mdi-magnify"
      ></v-text-field>
    </v-form>
    <!-- @keydown.enter="searchSummoner" -->

    <CategoryList @categoryList="category" />
    <Advertise />
    <RecommendFood />
  </div>
</template>

<script>
import CategoryList from "../../components/product/CategoryList";
import Advertise from "../../components/product/Advertise";
import RecommendFood from "../../components/product/RecommendFood";
export default {
  components: {
    CategoryList,
    Advertise,
    RecommendFood,
  },
  data() {
    return {
      title: "상품페이지",
      category: "",
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

<style lang="">
</style>