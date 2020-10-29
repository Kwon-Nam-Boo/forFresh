<template>
  <v-main>
    <!-- 냉장고 고르기 탭 -->
    <v-tabs
      v-model="tab"
      background-color=""
      color="#88dba3"
      show-arrows
    >
      <v-tabs-slider color="#88dba3"></v-tabs-slider>

      <v-tab
        v-for="item in items"
        :key="item"
      >
        {{item}}
      </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card
          color="="
          flat
        >
          <refrigerator></refrigerator>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    <v-speed-dial
      v-model="fab"
      bottom
      right
      fixed
      direction="top"
      transition="slide-y-reverse-transition"
    >
      <template v-slot:activator>
        <v-btn
          v-model="fab"
          color="#88dba3"
          dark
          fab
          small
        >
          <v-icon v-if="fab">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-format-list-bulleted-square
          </v-icon>
        </v-btn>
      </template>
        <v-btn
          fab
          dark
          small
          color="green"
        >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
       
        <v-btn
          fab
          dark
          small
          color="indigo"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          small
          color="red"
        >
          <v-icon>mdi-delete</v-icon>
        </v-btn>
    </v-speed-dial>
  </v-main>
</template>

<script>
import Refrigerator from "../components/Refrigerator";
export default {
  components: {
    Refrigerator,
  },
  created() {
    this.$emit('updateTitle', '메인페이지');
    // if(this.$session.get('userinfo')){

    // }
  },
  data() {
    return {
      title:"메인페이지",
      tab: null,
      fab: false,
      items: [
        '냉장고1', '냉장고2', '냉장고3', '냉장고4',
      ],
    }
  },
};
</script>
<style>

</style>
