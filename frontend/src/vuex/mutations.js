import UserApi from "../api/UserApi";

export default {
  sendMail(state, payload) {
    UserApi.sendMailApi(
      payload,
      (res) => {
        // console.log(res);
        // this.emailAuthCode = res.data
        state.emailAuthCode = res.data.data;
      },
      (error) => {
        state.emailAuthCode = "email overlapped";
      }
    );
  },
};
