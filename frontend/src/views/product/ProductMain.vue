<template>
  <div>
    <CategoryList @categoryList="category" />

    <!-- onsubmit="return false;" -->
    <v-container>
      <v-row>
        <v-col cols="8">
          <v-text-field
            rounded
            solo
            dense
            label="식품 검색"
            v-model="keyword"
          ></v-text-field
        ></v-col>
        <v-col cols="2">
          <v-btn color="#88dba3" @click="searchProduct()"
            >검색
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
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
    searchProduct() {},
  },
};
</script>

<style lang="">
</style>