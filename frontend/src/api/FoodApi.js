import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const getFoodList = (data, callback, errorCallback) => {
    axios({
        method: "GET",
        url: BASE_URL + "/food/getfood",
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

const getFoodByNo = (data, callback, errorCallback) => {
  axios({
      method: "GET",
      url: BASE_URL + "/food/getfood/"+data.foodNo,
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

const deleteFood = (data, callback, errorCallback) => {
  axios({
      method: "DELETE",
      url: BASE_URL + "/food/deletefood",
      data: {
        foodNos: data.foodNoList,
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

const deleteFoodOne = (data, callback, errorCallback) => {
  axios({
      method: "DELETE",
      url: BASE_URL + "/food/deletefoodone",
      params: {
        foodNo: data.foodNo,
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
    getFoodByNo: (data, callback, errorCallback) =>
      getFoodByNo(data, callback, errorCallback),
    deleteFood: (data, callback, errorCallback) =>
      deleteFood(data, callback, errorCallback),
    deleteFoodOne: (data, callback, errorCallback) =>
      deleteFoodOne(data, callback, errorCallback),
};

export default RefApi;