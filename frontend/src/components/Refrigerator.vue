<template>
  <div class="ma-10">
    <v-card width="180">
    <v-tabs
      v-model="tab"
      background-color="#FFFF00"
      color=""
      grow
    >
      <v-tabs-slider color="#FFFF00"></v-tabs-slider>
      <v-tab
        v-for="item in items"
        :key="item"
      >
        {{item}}
      </v-tab>
    </v-tabs>
    </v-card>
    <v-card>
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
          <v-card class="ma-5" v-for="item in items2" :key="item[0]">
            <v-card-text :class="item[1]+'--text'">{{item[0]}}</v-card-text>
            <v-slide-group
              multiple
              show-arrows
            >
              <v-slide-item
                class="ma-1 mb-3"
                v-for="food in foodList"
                :key="food.date"
              >
                <v-avatar>
                  <img :src=food.img>
                </v-avatar>
              </v-slide-item>
            </v-slide-group>
          </v-card>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    </v-card>
  </div>
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
      foodList: [
        {num:1,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:2,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:3,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:4,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:5,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:6,img:"https://cdn.vuetifyjs.com/images/john.jpg"},
        {num:7,img:"https://cdn.vuetifyjs.com/images/john.jpg"},

      ]
    }
  },
};
</script>
<style scoped>
  
</style>