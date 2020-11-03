<template>
  <v-file-input
    label="File input"
    filled
    prepend-icon="mdi-camera"
    multiple
    accept="image/png, image/jpeg, image/bmp, image/jpg"
    v-model="files"
    @click="addProduct()"
  ></v-file-input>
</template>

<script>
import ProductApi from "../../api/ProductApi";

export default {
  data() {
    return {
      files: "",
    };
  },
  methods: {
    addProduct() {
      let formData = new FormData();

      if (this.files) {
        for (let file in this.files) {
          formData.append("cave", file);
        }

        console.log(formData.getAll("cave"));
        console.log(this.files);
        ProductApi.requestAddProduct(
          {},
          (res) => {},
          (error) => {}
        );
      } else {
        console.log("there are no files.");
      }
    },
  },
};
</script>

<style lang="">
</style>