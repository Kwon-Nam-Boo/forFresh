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
        url: BASE_URL + "/refrig/getRefrig",
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

const RefApi = {
    addRef: (data, callback, errorCallback) =>
        addRef(data, callback, errorCallback),
    getRef: (data, callback, errorCallback) =>
        getRef(data, callback, errorCallback),
    shareRef: (data, callback, errorCallback) =>
        shareRef(data, callback, errorCallback),
};

export default RefApi;