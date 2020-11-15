<template>
  <div>
    <!-- <h1>kakaoPay api 이용하기</h1> -->
    <button @click="payment()">
      <v-img :src="require(`@/assets/payment_icon_yellow_small.png`)"></v-img>
    </button>
  </div>
</template>

<script>
import PaymentApi from "../../api/PaymentApi";

const storage = window.sessionStorage;

export default {
  data() {
    return {
      itemName: "사과",
      quantity: "1",
      totalAmount: "1000",
      productNo: "2",
    };
  },
  created() {},
  methods: {
    payment() {
      PaymentApi.requestPayment(
        {
          userId: storage.getItem("login_user"),
          productNo: this.productNo,
          itemName: this.itemName,
          quantity: this.quantity,
          totalAmount: this.totalAmount,
        },
        (res) => {
          location.href = res.data;
        },
        (error) => {
        }
      );
    },
  },
};
</script>

<style lang=""></style>
