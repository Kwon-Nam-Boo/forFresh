<template>
  <v-card>
    <v-tabs v-model="tab" dark background-color="#88dba3" show-arrows>
      <v-tabs-slider color="lighten-5"></v-tabs-slider>

      <v-tab v-for="(item, i) in items" :key="i" @click="changePage(i)">
        {{ item }}
      </v-tab>
    </v-tabs>
    <!-- <v-tabs-items v-model="tab">
      <v-tab-item v-for="(item, i) in items" :key="i">
        <ProductHome v-if="i == 0" />
        <ProductCategory v-else :categoryNo="i - 1" />
      </v-tab-item>
    </v-tabs-items> -->
  </v-card>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      tab: null,
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
    };
  },
  created() {},
  methods: {
    changePage(no) {
      this.$router.push("/productcategory/" + no).catch(() => {});
      // location.reload();
    },
  },
};
</script>

<style lang=""></style>
