import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const getShare = (data, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/refrig/getShare",
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

const AlarmApi = {
  getShare: (data, callback, errorCallback) =>
    getShare(data, callback, errorCallback),
};

export default AlarmApi;