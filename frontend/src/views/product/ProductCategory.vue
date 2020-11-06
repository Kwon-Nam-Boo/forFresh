<template>
  <div>
    <CategoryList @categoryList="category" />

    <v-card class="mx-auto my-12" max-width="374">
      <v-img
        height="250"
        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
      ></v-img>

      <v-card-title>제목</v-card-title>

      <v-card-text>
        <div class="my-4 subtitle-1">상품명</div>

        <v-row align="center" class="mx-0">
          <v-rating
            :value="4.5"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ml-4">평점 (댓글수)</div>
        </v-row>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>
    </v-card>
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
      categoryNo: "",
      selection: 1,
    };
  },
  created() {
    this.categoryNo = this.$route.params.categroy;
    this.$emit("updateTitle", "상품페이지");
  },
  methods: {},
};
</script>

<style lang="">
</style>