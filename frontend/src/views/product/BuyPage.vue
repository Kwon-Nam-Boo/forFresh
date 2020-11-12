<template>
  <div>
    <v-card>
      <v-card-title>배송 정보</v-card-title>
    </v-card>
    <v-card class="mt-3">
      <v-card-title>상품 정보</v-card-title>
      <v-container>
        <v-form ref="form">
          <v-row>
            <v-col cols="6">
              <v-img max-width="200px" :src="product.imgUrl"></v-img>
            </v-col>
            <v-col cols="6">
              <p>상품명 : {{ product.productName }}</p>
              <p>가격 : {{ product.productPrice }}</p>

              <v-text-field
                v-model="curStock"
                :rules="stockRules"
                label="수량"
                style="width: 60px"
                type="number"
                outlined
                required
              ></v-text-field>
              남은 수량 : {{ product.stock - curStock }}
            </v-col>
            <p class="ml-11">
              상품 금액 : {{ product.productPrice * curStock }} 원
            </p>
          </v-row>
        </v-form>
      </v-container>
    </v-card>
    <v-card class="mt-3">
      <v-card-title>결제 현황</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <p>총 상품 금액 : {{ product.productPrice * curStock }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>총 수량 : {{ curStock }} 개</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>배송비 : {{ postMethod() }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>주문 금액 : {{ post + product.productPrice * curStock }} 원</p>
          </v-col>
          <v-col>
            <button @click="payment()">
              <v-img
                :src="require(`@/assets/payment_icon_yellow_small.png`)"
              ></v-img>
            </button>
            <!-- 구매하기 버튼 -->
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
<script>
import ProductApi from "../../api/ProductApi";
import PaymentApi from "../../api/PaymentApi";

const storage = window.sessionStorage;

export default {
  data() {
    return {
      title: "구매 페이지",
      stockRules: [
        (v) => (v > 0 && v <= this.product.stock) || "수량을 확인하세요",
      ],
      curStock: 1,
      post: 0,
      productPlusPostTotal: 0,
      // itemName: "",
      // productNo: "",
      // shopNo: "",
      // stockList: "",
      product: {},
    };
  },

  created() {
    this.$emit("updateTitle", this.title);
    ProductApi.requestProductDetail(
      {
        userId: storage.getItem("login_user"),
        productNo: this.$route.params.productno,
      },
      (res) => {
        this.product = res.data.object;
      },
      (error) => {
        // console.log(error);
      }
    );
  },
  methods: {
    payment() {
      if (!this.$refs.form.validate()) {
        alert("수량을 확인하세요");
        return;
      }

      PaymentApi.requestPayment(
        {
          userId: storage.getItem("login_user"),
          shoplistNo: "no",
          stockList: String(this.curStock),
          productNo: this.$route.params.productno,
          itemName: this.product.productName,
          quantity: String(this.curStock),
          totalAmount: String(
            this.post + this.product.productPrice * this.curStock
          ),
        },
        (res) => {
          // console.log(res.data);
          location.href = res.data;
        },
        (error) => {
          // console.log(error);
        }
      );
    },
    postMethod() {
      if (this.curStock * this.product.productPrice >= 10000) {
        return (this.post = 0);
      } else {
        return (this.post = 2500);
      }
    },
    // productPlusPostTotalMethod() {
    //   return (this.productPlusPostTotal =
    //     this.post + this.curStock * Number(this.prouct.productPrice));
    // },
  },
};
</script>

<style lang="">
</style>