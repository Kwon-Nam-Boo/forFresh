export default {
  getEmailAuthCode: (state) => () => {
    return state.emailAuthCode;
  },
  // getNoByName: (state) => (name) => {
  //   return state.foodCategoryList.find((food) => food.categoryName === name)
  //     .categoryNo;
  // },
  getNoByName: (state) => (name) => {
    return state.foodCategoryList.find((food) => food.name === name).no;
  },
};
