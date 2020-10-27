import UserApi from "../api/UserApi";

export default {
  sendMail(email) {
    UserApi.sendMailApi(
      email,
      (res) => {
        console.log(res);
        // this.emailAuthCode = res.data
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
