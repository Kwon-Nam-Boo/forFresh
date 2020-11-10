import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const getFoodList = (data, callback, errorCallback) => {
    axios({
        method: "GET",
        url: BASE_URL + "/food/getFood",
        params: {
          refrigNo: data.refrigNo
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
    getFoodList: (data, callback, errorCallback) =>
      getFoodList(data, callback, errorCallback),
};

export default RefApi;