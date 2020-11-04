<template>
  <div>
    <!-- <h1>kakaoPay api 이용하기</h1> -->
    <button @click="payment()">카카오페이로 결제하기</button>
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
          console.log(res.data);
          location.href = res.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style lang=""></style>
