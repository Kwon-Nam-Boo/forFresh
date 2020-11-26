import UserApi from "../api/UserApi";

export default {
  sendMail(state, payload) {
    UserApi.sendMailApi(
      payload,
      (res) => {
        state.emailAuthCode = res.data.data;
      },
      (error) => {
        state.emailAuthCode = "email overlapped";
      }
    );
  },
};
