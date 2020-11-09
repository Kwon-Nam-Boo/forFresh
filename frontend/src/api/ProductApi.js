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
      registDate: "0000-00-00",
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};
const requestProductByCategory = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/list",
    params: {
      categoryNo: data.categoryNo,
      page: data.page,
      size: data.size,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestProductDetail = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/detail",
    params: {
      productNo: data,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestSearchProduct = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/search",
    params: {
      productName: data.productName,
      page: data.page,
      size: data.size,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestUserShoppingList = (email, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/shop/list",
    params: {
      userId: email,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const deleteShopList = (data, callback, errorCallback) => {
  axios({
    method: "DELETE",
    url: BASE_URL + "/product/shop/delete",
    params: {
      shoplistNo: data,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestUserShoppingListNo = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/shop/search",
    params: {
      userId: data.userId,
      productNo: data.productNo,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const addUserShopList = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/product/shop/add",
    data: {
      shoplistNo: "0",
      userId: data.userId,
      productNo: data.productNo,
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

  requestProductByCategory: (data, callback, errorCallback) =>
    requestProductByCategory(data, callback, errorCallback),

  requestProductDetail: (data, callback, errorCallback) =>
    requestProductDetail(data, callback, errorCallback),

  requestSearchProduct: (data, callback, errorCallback) =>
    requestSearchProduct(data, callback, errorCallback),

  requestUserShoppingList: (email, callback, errorCallback) =>
    requestUserShoppingList(email, callback, errorCallback),

  deleteShopList: (data, callback, errorCallback) =>
    deleteShopList(data, callback, errorCallback),

  addUserShopList: (data, callback, errorCallback) =>
    addUserShopList(data, callback, errorCallback),

  requestUserShoppingListNo: (data, callback, errorCallback) =>
    requestUserShoppingListNo(data, callback, errorCallback),
};

export default ProductApi;
