<template>
  <div>
    <v-card>
      <v-card-title>배송 정보</v-card-title>
    </v-card>
    <v-card class="mt-3">
      <v-card-title>상품 정보</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <v-checkbox
              v-model="allselect"
              label="전체 선택"
              value="all"
              @click="initList()"
              float
            ></v-checkbox>
          </v-col>
          <v-col>
            <v-btn absolute right class="mt-3" @click="delShopList()"
              >선택 삭제</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
      <hr />
      {{ selected }}
      <v-container>
        <v-row v-for="item in shopList" :key="item.shoplistNo">
          <!-- <v-checkbox v-model="selected" :value="item.productNo" ></v-checkbox> -->
          <v-col cols="1">
            <v-checkbox
              v-model="selected"
              :value="item.shoplistNo"
            ></v-checkbox>
          </v-col>
          <v-col cols="6">
            <v-img max-width="200px" :src="item.imgUrl"></v-img>
          </v-col>
          <v-col cols="5">
            <p>상품명 : {{ item.productName }}</p>
            <p>가격 : {{ item.productPrice }}</p>

            <v-text-field
              v-model="item.stock"
              :rules="stockRules"
              label="수량"
              style="width: 60px"
              type="number"
              outlined
              required
            ></v-text-field>
          </v-col>
          <p class="ml-11">
            주문 금액 : {{ item.productPrice * item.stock }}원
          </p>
        </v-row>
      </v-container>
      <!-- <v-checkbox v-model="selected" label="John" value="John"></v-checkbox>
      <v-checkbox v-model="selected" label="Jacob" value="Jacob"></v-checkbox> -->
    </v-card>
    <v-card class="mt-3">
      <v-card-title>결제 현황</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <p>총 상품 금액 : {{ computedProductTotal }}</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>배송비 : {{ computedPost }}</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>주문 금액 : {{ computedProductPlusPostTotal }}</p>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
<script>
import ProductApi from "../../api/ProductApi";
const storage = window.sessionStorage;
export default {
  data() {
    return {
      title: "장바구니",
      stockRules: [(v) => v > 0 || "수량 등록이 필요합니다."],
      selected: [],
      allselect: ["all"],
      initStatus: false,
      shopList: [],
      selectedAllCopy: [],
      originStock: [],
      productTotal: 0,
      post: 0,
      productPlusPostTotal: 0,
    };
  },
  // computed: {
  //   computedProductTotal() {
  //     for (var i = 0; i < this.selected.length; i++) {}
  //     return `${this.productTotal}`;
  //   },
  //   computedPost() {
  //     if (this.productTotal >= 10000) {
  //       this.post = 0;
  //     } else {
  //       this.post = 2500;
  //     }
  //     return `${this.post}`;
  //   },
  //   computedProductPlusPostTotal() {
  //     return `${this.productTotal + this.post}`;
  //   },
  // },
  created() {
    this.$emit("updateTitle", this.title);
    ProductApi.requestUserShoppingList(
      storage.getItem("login_user"),
      (res) => {
        this.shopList = res.data;
        for (var i = 0; i < this.shopList.length; i++) {
          this.originStock.push({
            shoplistNo: this.shopList[i].shoplistNo,
            stock: this.shopList[i].stock,
          });
          this.shopList[i].stock = 1;
          this.selected.push(this.shopList[i].shoplistNo);
        }
        this.selectedAllCopy = this.selected;
        // console.log(this.originStock);
        // console.log(this.shopList);
      },
      (error) => {}
    );
  },
  methods: {
    initList() {
      if (this.initStatus) {
        this.selected = this.selectedAllCopy;
        this.allselect = ["all"];
      } else {
        this.selected = [];
        this.allselect = [];
      }
      this.initStatus = !this.initStatus;
    },
    delShopList() {
      // console.log(this.selected);
      for (var i = 0; i < this.selected.length; i++) {
        ProductApi.deleteShopList(
          this.selected[i],
          (res) => {
            location.reload(true);
          },
          (error) => {}
        );
      }
    },
  },
};
</script>
<style lang="">
</style>