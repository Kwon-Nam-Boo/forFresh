<template>
  <div>
    <ProductCtegoryItem
      v-for="(item, i) in list"
      :product="list[i]"
      v-bind:key="i"
    ></ProductCtegoryItem>
    <v-btn
      v-scroll="onScroll"
      v-show="fab"
      fab
      dark
      fixed
      bottom
      right
      color="#88dba3"
      @click="toTop"
    >
      <v-icon>mdi-chevron-up</v-icon>
    </v-btn>
    <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        목록의 끝입니다 :)
      </div>
    </infinite-loading>
    <!-- <infinite-loading
      @infinite="infiniteHandler"
      spinner="waveDots"
    ></infinite-loading> -->
  </div>
</template>
<script>
import ProductApi from "../../api/ProductApi";
import InfiniteLoading from "vue-infinite-loading";
import ProductCtegoryItem from "../../components/product/ProductCategoryItem";

export default {
  components: {
    ProductCtegoryItem,
    InfiniteLoading,
  },
  data() {
    return {
      title: "검색페이지",
      page: 0,
      size: "5",
      list: [],
      fab: false,
    };
  },
  created() {
    this.$emit("updateTitle", this.title);
  },
  methods: {
    onScroll(e) {
      if (typeof window === "undefined") return;
      const top = window.pageYOffset || e.target.scrollTop || 0;
      this.fab = top > 20;
    },
    toTop() {
      this.$vuetify.goTo(0);
    },
    infiniteHandler($state) {
      ProductApi.requestSearchProduct(
        {
          productName: this.$route.params.keyword,
          page: this.page,
          size: this.size,
        },
        (res) => {
          // console.log(res.data);
          setTimeout(() => {
            if (res.data.length) {
              this.page += 1;
              this.list.push(...res.data);
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1000);
        },
        (error) => {
          $state.complete();
        }
      );
    },
  },
};
</script>

<style lang="">
</style>