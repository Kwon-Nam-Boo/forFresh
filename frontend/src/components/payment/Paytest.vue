<template>
  <div>
    <h1>kakaoPay api 이용하기</h1>
    <button @click="payment()">카카오페이로 결제하기</button>
  </div>
</template>

<script>
import PaymentApi from "../../api/PaymentApi";

export default {
  data() {
    return {
      itemName: "사과",
      quantity: "1",
      totalAmount: "1000",
    };
  },
  methods: {
    payment() {
      PaymentApi.requestPayment(
        {
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

<style lang="">
</style>