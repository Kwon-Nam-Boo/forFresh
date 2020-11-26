<template>
  <div>
    <v-container>
      <v-row>
        <v-col cols="9">
          <v-text-field
            rounded
            solo
            dense
            label="식품 검색"
            v-model="keyword"
          ></v-text-field
        ></v-col>
        <v-col cols="2">
          <v-btn color="#88dba3" @click="searchProduct()" fab small>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
    <Advertise />
    <RecommendFood />
  </div>
</template>

<script>
import Advertise from "../../components/product/Advertise";
import RecommendFood from "../../components/product/RecommendFood";

export default {
  components: {
    Advertise,
    RecommendFood,
  },
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    searchProduct() {
      this.$router.push("/productsearch/" + this.keyword).catch(() => {});
    },
  },
};
</script>
