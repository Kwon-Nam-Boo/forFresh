import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);

const state = {
  emailAuthCode: "",
  foodCategoryList: [
    { name: "돼지고기", no: "0", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F0.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "쇠고기", no: "1", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F1.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "닭고기", no: "2", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F2.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "알류", no: "3", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F3.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "축산가공식품", no: "4", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F4.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "생선", no: "5", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F5.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "김_해초", no: "6", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F6.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "해산물/어패류", no: "7", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F24.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210"},
    { name: "젓갈", no: "8", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F7.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "건어물", no: "9", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F8.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "아이스크림_빙수", no: "10", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F9.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "냉동_간편조리식품", no: "11", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F10.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "쌀", no: "12", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F11.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "잡곡_혼합곡", no: "13", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F12.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "과일", no: "14", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F13.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "채소", no: "15", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F14.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "견과류", no: "16", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F15.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "반찬", no: "17", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F16.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "김치", no: "18", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F17.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "음료", no: "19", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F18.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "차류", no: "20", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F19.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "커피", no: "21", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F20.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "유제품", no: "22", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F21.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "과자", no: "23", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F22.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    { name: "가공식품", no: "24", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F23.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
  ],
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
});
