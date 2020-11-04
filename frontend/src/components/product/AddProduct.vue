<template>
  <v-container>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-select
        v-model="categoryName"
        :items="items"
        :rules="[(v) => !!v || 'Item is required']"
        label="카테고리"
        required
      ></v-select>

      <v-text-field
        v-model="description"
        :counter="50"
        :rules="descriptionRules"
        label="제목"
        required
      ></v-text-field>

      <v-text-field
        v-model="productName"
        :rules="productNameRules"
        label="상품명"
        required
      ></v-text-field>

      <v-text-field
        v-model="productPrice"
        :rules="productPriceRules"
        label="가격"
        type="number"
        required
      ></v-text-field>

      <v-text-field
        v-model="stock"
        :rules="stockRules"
        label="수량"
        style="width: 60px"
        type="number"
        outlined
        required
      ></v-text-field>

      <v-img position="center" :src="picture" />
      <br />
      <v-file-input
        v-model="imageData"
        accept=".jpg, .jpeg, .png, .bmp"
        show-size
        label="썸네일 사진 추가"
        @change="previewImage"
        outlined
      ></v-file-input>

      <!-- <v-img position="center" :src="detailPicture" />
      <br />
      <v-file-input
        v-model="imageData"
        accept=".jpg, .jpeg, .png, .bmp"
        show-size
        label="썸네일 사진 추가"
        @change="previewImage"
        outlined
      ></v-file-input> -->

      <!-- <input type="file" @change="previewImage" accept="image/*" /> -->
      <!-- <p>
        Progress:{{ uploadValue.toFixed() + "%" }}
        <progress value="uploadValue" max="100"></progress>
      </p> -->
      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="addProduct()"
      >
        등록하기
      </v-btn>

      <v-btn color="error" class="mr-4" @click="reset"> 지우기 </v-btn>
    </v-form>
  </v-container>
</template>

<script>
import ProductApi from "../../api/ProductApi";
import firebase from "firebase";
// import { mdiArrowUpCircleOutline, mdiArrowDownCircleOutline } from "@mdi/js";

export default {
  data() {
    return {
      title: "메인페이지",
      valid: true,
      productNameRules: [(v) => !!v || "상품명 등록이 필요합니다."],
      productPriceRules: [(v) => v >= 0 || "가격 등록이 필요합니다."],
      descriptionRules: [(v) => !!v || "제목 등록이 필요합니다."],
      stockRules: [(v) => v > 0 || "수량 등록이 필요합니다."],
      items: [
        "돼지고기",
        "쇠고기",
        "닭고기",
        "알류",
        "축산가공식품",
        "생선",
        "김_해초",
        "젓갈",
        "건어물",
        "아이스크림_빙수",
        "냉동_간편조리식품",
        "쌀",
        "잡곡_혼합곡",
        "과일",
        "채소",
        "견과류",
        "반찬",
        "김치",
        "음료",
        "차류",
        "커피",
        "유제품",
        "과자",
        "가공식품",
      ],
      categoryName: null,
      productName: "",
      productPrice: "",
      stock: "",
      description: "",
      imageData: "",
      picture: null,
      uploadValue: 0,
    };
  },
  created() {
    this.$emit("updateTitle", "메인페이지");
  },
  methods: {
    previewImage(event) {
      this.uploadValue = 0;
      this.picture = null;
      this.imageData = event.target.files[0];
    },
    async addProduct() {
      // ProductApi.requestAddProduct(
      //   formData,
      //   (res) => {},
      //   (error) => {}
      // );
      this.picture = null;
      const storageRef = firebase
        .storage()
        .ref(`${this.imageData.name}`)
        .put(this.imageData);
      storageRef.on(
        `state_changed`,
        (snapshot) => {
          this.uploadValue =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        },
        (error) => {
          console.log(error.message);
        },
        () => {
          this.uploadValue = 100;
          storageRef.snapshot.ref.getDownloadURL().then((url) => {
            this.picture = url;
          });
        }
      );
    },

    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>

<style lang=""></style>
