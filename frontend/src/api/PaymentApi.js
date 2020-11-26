// import store from "../vuex/store";
import BASE_URL from "../main";

const axios = require("axios");
// const storage = window.sessionStorage;

const requestPayment = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/kakaopay",
    data: {
      userId: data.userId,
      refrigNo: data.refrigNo,
      shoplistNo: data.shoplistNo,
      stockList: data.stockList,
      productNo: data.productNo,
      itemName: data.itemName,
      priceList: data.priceList,
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
