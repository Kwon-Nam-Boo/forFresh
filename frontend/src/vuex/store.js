import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);

const state = {
  emailAuthCode: "",
  foodCategoryList: [
    { name: "돼지고기", no: "0" },
    { name: "쇠고기", no: "1" },
    { name: "닭고기", no: "2" },
    { name: "알류", no: "3" },
    { name: "축산가공식품", no: "4" },
    { name: "생선", no: "5" },
    { name: "김/해초", no: "6" },
    { name: "해산물/어패류", no: "7" },
    { name: "젓갈", no: "8" },
    { name: "건어물", no: "9" },
    { name: "아이스크림/빙수", no: "10" },
    { name: "냉동/간편조리식품", no: "11" },
    { name: "쌀", no: "12" },
    { name: "잡곡/혼합곡", no: "13" },
    { name: "과일", no: "14" },
    { name: "채소", no: "15" },
    { name: "견과류", no: "16" },
    { name: "반찬", no: "17" },
    { name: "김치", no: "18" },
    { name: "음료", no: "19" },
    { name: "차류", no: "20" },
    { name: "커피", no: "21" },
    { name: "유제품", no: "22" },
    { name: "과자", no: "23" },
    { name: "가공식품", no: "24" },
  ],
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
});
