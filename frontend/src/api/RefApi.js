import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const addRef = (data, callback, errorCallback) => {
    axios({
        method: "post",
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
        method: "get",
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

const RefApi = {
    addRef: (data, callback, errorCallback) =>
        addRef(data, callback, errorCallback),
    getRef: (data, callback, errorCallback) =>
        getRef(data, callback, errorCallback),
};

export default RefApi;