<template>
  <div>
    <ProductCtegoryItem
      v-for="(item, i) in list"
      :product="list[i]"
      v-bind:key="i"
    ></ProductCtegoryItem>
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
import ProductCtegoryItem from "../product/ProductCategoryItem";
import InfiniteLoading from "vue-infinite-loading";
import ProductApi from "../../api/ProductApi";

export default {
  components: {
    ProductCtegoryItem,
    InfiniteLoading,
  },
  data() {
    return {
      page: "0",
      size: "5",
      list: [],
    };
  },
  props: ["categoryNo"],
  created() {
    this.page = "0";
  },
  methods: {
    infiniteHandler($state) {
      ProductApi.requestProductByCategory(
        {
          categoryNo: this.categoryNo,
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

<style lang=""></style>
