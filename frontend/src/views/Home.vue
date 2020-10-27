<template>
  <v-main>
      <v-tabs
        v-model="tab"
        background-color=""
        color=""
        show-arrows
      >
        <v-tabs-slider color="teal lighten-3"></v-tabs-slider>
  
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
      <v-fab-transition>
        <v-btn
          color="green"
          fixed
          bottom
          right
          fab
          dark
          small
        >
          <v-icon>
            mdi-plus
          </v-icon>
        </v-btn>
      </v-fab-transition>
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
      items: [
        'Appetizers', 'Entrees', 'Deserts', 'Cocktails',
      ],
    }
  },
};
</script>