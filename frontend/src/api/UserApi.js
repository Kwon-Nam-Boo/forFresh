// import store from "../vuex/store";
import BASE_URL from "../main";

const axios = require("axios");
const storage = window.sessionStorage;

const requestLogin = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/account/login",
    data: {
      userId: data.email,
      password: data.password,
    },
  })
    .then(function(response) {
      // if (response.data.status) {
      //   store.commit("login", {
      //     email: response.data.object.id,
      //     summonerName: response.data.object.summonerName,
      //   });
      callback(response);
      // } else {
      //   errorCallback();
      // }
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const requestJoin = (data, callback, errorCallback) => {
  axios({
    method: "post",
    url: BASE_URL + "/account/user/join",
    data: {
      userId: data.email,
      password: data.password,
      nickName: data.nickname,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const checkNickName = (nickname, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/account/user/search/nickname",
    params: {
      nickName: nickname,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const getUserInfo = (email, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/account/user/search",

    params: {
      userId: email,
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

const sendMailApi = (email, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/mail/send",
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

const sendMailForPassword = (email, callback, errorCallback) => {
  axios({
    method: "get",
    url: BASE_URL + "/mail/send/password",
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

const updateUserPassword = (data, callback, errorCallback) => {
  axios({
    method: "put",
    url: BASE_URL + "/account/user/update/password",
    data: {
      userId: data.email,
      password: data.password,
      nickName: data.nickname,
    },
  })
    .then(function(response) {
      callback(response);
    })
    .catch(function(error) {
      errorCallback(error);
    });
};

const UserApi = {
  requestLogin: (data, callback, errorCallback) =>
    requestLogin(data, callback, errorCallback),

  requestJoin: (data, callback, errorCallback) =>
    requestJoin(data, callback, errorCallback),

  updateUserPassword: (data, callback, errorCallback) =>
    updateUserPassword(data, callback, errorCallback),

  checkNickName: (nickname, callback, errorCallback) =>
    checkNickName(nickname, callback, errorCallback),

  sendMailApi: (email, callback, errorCallback) =>
    sendMailApi(email, callback, errorCallback),

  sendMailForPassword: (email, callback, errorCallback) =>
    sendMailForPassword(email, callback, errorCallback),

  getUserInfo: (email, callback, errorCallback) =>
    getUserInfo(email, callback, errorCallback),
};

export default UserApi;
