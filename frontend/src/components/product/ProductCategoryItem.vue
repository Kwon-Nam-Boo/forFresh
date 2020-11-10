<template>
  <v-card class="ml-1 mr-1 my-12" max-width="374">
    <v-img
      height="250"
      :src="`${product.imgUrl}`"
      @click="
        $router.push('/productdetail/' + `${product.productNo}`).catch(() => {})
      "
    ></v-img>

    <v-card-title>{{ product.description }}</v-card-title>

    <v-card-text>
      <div class="my-4 subtitle-1">{{ product.productName }}</div>
      <div>가격 : {{ product.productPrice }}원</div>
      <v-row align="center" class="mx-0">
        <v-rating
          :value="product.avgRate"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">
          {{ product.avgRate }}점 ({{ product.commentCnt }})
        </div>
        <v-chip-group
          class="ml-5"
          v-model="selection"
          active-class="deep-purple accent-4 white--text"
          column
        >
          <v-chip @click="changeStatus()"><v-icon>mdi-shopping</v-icon></v-chip>
        </v-chip-group>

        <v-btn
          v-if="product.stock > 0"
          absolute
          right
          rounded
          @click="buyProduct()"
          >구매하기</v-btn
        >
        <v-btn v-else disabled>품 절</v-btn>
      </v-row>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>
  </v-card>
</template>

<script>
import ProductApi from "../../api/ProductApi";
const storage = window.sessionStorage;

export default {
  components: {},
  data() {
    return {
      selection: null,
      shoplistNo: "",
    };
  },
  props: ["product"],
  created() {
    if (this.product.shoplistNo != null) {
      this.selection = 0;
    }
  },
  methods: {
    buyProduct() {},
    changeStatus() {
      // console.log("들어왓다.");
      if (this.selection == null) {
        ProductApi.addUserShopList(
          {
            userId: storage.getItem("login_user"),
            productNo: this.product.productNo,
          },
          (res) => {
            // console.log(res.data);
          },
          (error) => {
            // console.log(error);
          }
        );
      } else {
        ProductApi.requestUserShoppingListNo(
          {
            userId: storage.getItem("login_user"),
            productNo: this.product.productNo,
          },
          (res) => {
            this.shoplistNo = res.data.shoplistNo;
            ProductApi.deleteShopList(
              this.shoplistNo,
              (res) => {
                //장바구니 삭제 성공
              },
              (error) => {
                //장바구니 삭제 실패
              }
            );
          },
          (error) => {
            // shoplistNo 가져오기 실패
          }
        );
      }
    },
  },
};
</script>

<style lang=""></style>
