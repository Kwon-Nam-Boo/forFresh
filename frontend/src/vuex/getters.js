export default {
  getEmailAuthCode: (state) => () => {
    return state.emailAuthCode;
  },
};
