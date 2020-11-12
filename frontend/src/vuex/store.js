import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);
const state = {
  emailAuthCode: "",
  foodCategoryList: {
    "0":{ name: "돼지고기", no: "0", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F0.png?alt=media&token=be611027-ae95-48e3-8a6f-157013769210" },
    "1":{ name: "쇠고기", no: "1", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F1.jpg?alt=media&token=1a076a81-d08f-4131-bcdd-fa4d77865053" },
    "2":{ name: "닭고기", no: "2", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F2.jpg?alt=media&token=c18c5cc3-a308-4b44-8719-4158010eb054"},
    "3":{ name: "알류", no: "3", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F3.jpg?alt=media&token=cd28fb03-e651-4b17-b841-645439380e1f"},
    "4":{ name: "축산가공식품", no: "4", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F4.jpg?alt=media&token=684861c3-05ec-491a-8232-ac803bdf693c"},
    "5":{ name: "생선", no: "5", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F5.png?alt=media&token=d50bdc7e-d512-49de-8953-5cc5b5fb772a"},
    "6":{ name: "김_해초", no: "6", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F6.jpg?alt=media&token=354211e9-7a5a-4f98-ab54-49d52420a450"},
    "7":{ name: "해산물/어패류", no: "7", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F24.jpg?alt=media&token=744bff7c-a5c0-47eb-9c6a-38d8d9841129"},
    "8":{ name: "젓갈", no: "8", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F7.jpg?alt=media&token=9125c358-b344-41dd-be23-b357952727f9"},
    "9":{ name: "건어물", no: "9", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F8.jpg?alt=media&token=f779391d-8cbd-4697-8ddb-79f015341e43"},
    "10":{ name: "아이스크림_빙수", no: "10", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F9.svg?alt=media&token=11a5da08-3f51-47da-87ea-19e881c8486b"},
    "11":{ name: "냉동_간편조리식품", no: "11", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F10.jpg?alt=media&token=1adce305-64a1-4679-b8d1-15194acafdba"},
    "12":{ name: "쌀", no: "12", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F11.png?alt=media&token=ad5f197a-2c85-401e-bb6d-fb27c2e1ee6d"},
    "13":{ name: "잡곡_혼합곡", no: "13", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F12.png?alt=media&token=8e409b2b-5ef0-4b6a-9fd5-a3c8d12973f3"},
    "14":{ name: "과일", no: "14", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F13.jpg?alt=media&token=a5d86853-0f86-46ee-a345-985aeb8b230a"},
    "15":{ name: "채소", no: "15", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F14.jpg?alt=media&token=c336a673-5264-4e25-90b7-2fced69028cf"},
    "16":{ name: "견과류", no: "16", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F15.png?alt=media&token=7270eac9-9828-402f-a205-5aa429bcde52"},
    "17":{ name: "반찬", no: "17", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F16.jpg?alt=media&token=738808db-2d1c-486f-a7a6-235ddd5d78fe"},
    "18":{ name: "김치", no: "18", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F17.png?alt=media&token=29e166ba-a155-4485-8294-e16f8f8738f8"},
    "19":{ name: "음료", no: "19", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F18.jpeg?alt=media&token=e691974a-916f-4be3-a522-ae2624caeda1"},
    "20":{ name: "차류", no: "20", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F19.png?alt=media&token=8c13eb84-712c-46f8-9386-4da50938350d"},
    "21":{ name: "커피", no: "21", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F20.jpg?alt=media&token=22915117-9bab-4d8a-967f-215049218c32"},
    "22":{ name: "유제품", no: "22", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F21.jpg?alt=media&token=2b21d736-df2e-4a17-a845-a8918f31b81e"},
    "23":{ name: "과자", no: "23", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F22.jpg?alt=media&token=3b3c4d32-1597-4485-b0eb-39ba0047fe26"},
    "24":{ name: "가공식품", no: "24", img: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F23.jpg?alt=media&token=fa930041-63cc-4ec1-9522-e50506274b94"},
    "25":{ name: "미분류", no: "25", img:"https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/category%2F25.png?alt=media&token=aafaeb4e-3466-4847-872f-5e4963207f60"},
  },
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
});
