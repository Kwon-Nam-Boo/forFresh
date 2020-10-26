<template>
  <v-card class="ma-10">
    <v-tabs
      v-model="tab"
      background-color="#FFFF00"
      color=""
    >
      <v-tabs-slider color="#FFFF00"></v-tabs-slider>
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
          class="pa-5"
          color="#FFFF00"
          :elevation="0"
        >
          <div height="1"></div>
          <v-card class="ma-5" v-for="item in items2" :key="item">
            <v-card-text :class="item[1]+'--text'">{{item[0]}}</v-card-text>
          </v-card>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      tab: null,
      items: [
        '냉장', '냉동',
      ],
      items2: [
        ['위험','red'], ['보통','blue'], ['신선','green'],
      ],
      text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
    }
  },
};
</script>
<style scoped>
  .v-tabs {
    width: 60%;
  }
  
</style>