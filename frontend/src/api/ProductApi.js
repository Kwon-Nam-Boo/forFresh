// import store from "../vuex/store";
import BASE_URL from "../main";

const axios = require("axios");
// const storage = window.sessionStorage;

const requestAddProduct = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/product/add",
    data: {
      productNo: data.productNo,
      categoryNo: data.categoryNo,
      productName: data.productName,
      productPrice: data.productPrice,
      stock: data.stock,
      description: data.description,
      imgUrl: data.imgUrl,
      detailUrl: data.detailUrl,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const ProductApi = {
  requestAddProduct: (data, callback, errorCallback) =>
    requestAddProduct(data, callback, errorCallback),
};

export default ProductApi;
