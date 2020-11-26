import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const addRef = (data, callback, errorCallback) => {
    axios({
        method: "POST",
        url: BASE_URL + "/refrig/register",
        data: {
          userId: data.userId,
          refrigName: data.refrigName,
        },
        headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
        .then(function(response) {

          callback(response);

        })
        .catch(function(error) {
          errorCallback(error);
        });
};
const getRef = (data, callback, errorCallback) => {
    axios({
        method: "GET",
        url: BASE_URL + "/refrig/getrefrig",
        params: {
          userId: data.userId,
        },
        headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
        },
      })
        .then(function(response) {

          callback(response);

        })
        .catch(function(error) {
          errorCallback(error);
        });
};
const shareRef = (data, callback, errorCallback) => {
  axios({
      method: "POST",
      url: BASE_URL + "/refrig/share",
      params: {
        userId: data.userId,
        refrigNo: data.refrigNo,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {

        callback(response);

      })
      .catch(function(error) {
        errorCallback(error);
      });
};
const deleteRef = (data, callback, errorCallback) => {
  axios({
      method: "DELETE",
      url: BASE_URL + "/refrig/deleterefrig",
      params: {
        userId: data.userId,
        refrigNo: data.refrigNo,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {

        callback(response);

      })
      .catch(function(error) {
        errorCallback(error);
      });
};
const editRef = (data, callback, errorCallback) => {
  axios({
      method: "PATCH",
      url: BASE_URL + "/refrig/changename",
      params: {
        userId: data.userId,
        refrigNo: data.refrigNo,
        refrigName: data.refrigName,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {

        callback(response);

      })
      .catch(function(error) {
        errorCallback(error);
      });
};
const getReceiptData = (data, callback, errorCallback) => {
  axios({
      method: "GET",
      url: BASE_URL + "/food/getocr",
      params: {
        receiptUrl: data.receiptUrl,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {
        callback(response);
      })
      .catch(function(error) {
        errorCallback(error);
      });
};

const registFood = (data, callback, errorCallback) => {
  axios({
      method: "POST",
      url: BASE_URL + "/food/regist",
      data: {
        refrigNo : data.refrigNo,
        foods: data.foods,
      },
      headers: {
        "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

      
const getRefByNo = (data, callback, errorCallback) => {
  axios({
      method: "GET",
      url: BASE_URL + "/refrig/getrefrig/"+data.refrigNo,
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {
        callback(response);
      })
      .catch(function(error) {
        errorCallback(error);
      });
};
const shareAllow = (data, callback, errorCallback) => {
  axios({
      method: "PATCH",
      url: BASE_URL + "/refrig/share/allow",
      params: {
        userId: data.userId,
        refrigNo: data.refrigNo,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {

        callback(response);

      })
      .catch(function(error) {
        errorCallback(error);
      });
};
const shareRefuse = (data, callback, errorCallback) => {
  axios({
      method: "Delete",
      url: BASE_URL + "/refrig/share",
      params: {
        userId: data.userId,
        refrigNo: data.refrigNo,
      },
      headers: {
          "jwt-auth-token": storage.getItem("jwt-auth-token"),
      },
    })
      .then(function(response) {

        callback(response);

      })
      .catch(function(error) {
        errorCallback(error);
      });
};

const RefApi = {
    addRef: (data, callback, errorCallback) =>
      addRef(data, callback, errorCallback),
    getRef: (data, callback, errorCallback) =>
      getRef(data, callback, errorCallback),
    shareRef: (data, callback, errorCallback) =>
      shareRef(data, callback, errorCallback),
    deleteRef: (data, callback, errorCallback) =>
      deleteRef(data, callback, errorCallback),
    editRef: (data, callback, errorCallback) =>
      editRef(data, callback, errorCallback),
    getRefByNo: (data, callback, errorCallback) =>
      getRefByNo(data, callback, errorCallback),
    getReceiptData: (data, callback, errorCallback) =>
      getReceiptData(data, callback, errorCallback),
    registFood: (data, callback, errorCallback) =>
      registFood(data, callback, errorCallback),
    shareAllow: (data, callback, errorCallback) =>
      shareAllow(data, callback, errorCallback),
    shareRefuse: (data, callback, errorCallback) =>
      shareRefuse(data, callback, errorCallback),
};

export default RefApi;