// import store from "../vuex/store";
import BASE_URL from "../main";

const axios = require("axios");
// const storage = window.sessionStorage;

const requestPayment = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/kakaoPay",
    data: {
      itemName: data.itemName,
      quantity: data.quantity,
      totalAmount: data.totalAmount,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const PaymentApi = {
  requestPayment: (data, callback, errorCallback) =>
    requestPayment(data, callback, errorCallback),
};

export default PaymentApi;
