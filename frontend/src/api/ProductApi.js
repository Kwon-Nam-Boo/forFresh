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
      registDate: "",
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
      userId: data.userId,
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

const addUserComment = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/product/comment/add",
    data: {
      pcommentNo: "0",
      userId: data.userId,
      productNo: data.productNo,
      userRate: data.userRate,
      commentDetail: data.commentDetail,
      registDate: "",
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestProductCommentList = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/product/comment/list",
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

const deleteUserComment = (data, callback, errorCallback) => {
  axios({
    method: "DELETE",
    url: BASE_URL + "/product/comment/delete",
    params: {
      pcommentNo: data,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const updateUserComment = (data, callback, errorCallback) => {
  axios({
    method: "put",
    url: BASE_URL + "/product/comment/update",
    data: {
      pcommentNo: data.pcommentNo,
      userRate: data.userRate,
      commentDetail: data.commentDetail,
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

  addUserComment: (data, callback, errorCallback) =>
    addUserComment(data, callback, errorCallback),

  requestProductCommentList: (data, callback, errorCallback) =>
    requestProductCommentList(data, callback, errorCallback),

  deleteUserComment: (data, callback, errorCallback) =>
    deleteUserComment(data, callback, errorCallback),

  updateUserComment: (data, callback, errorCallback) =>
    updateUserComment(data, callback, errorCallback),
};

export default ProductApi;
