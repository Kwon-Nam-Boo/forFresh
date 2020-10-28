<template>
  <div class="ma-5">
    <v-card width="180">
    <v-tabs
      v-model="tab"
      background-color="#FDD692"
      color=""
      grow
    >
      <v-tabs-slider color="#FDD692"></v-tabs-slider>
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
          class="pa-1"
          color=""
          :elevation="0"
        >
          <div height="1"></div>
          <v-card class="ma-4" v-for="item in items2" :key="item[0]">
            <v-card-text :class="item[1]+'--text pb-3'">{{item[0]}}</v-card-text>
            <v-slide-group
              class="pb-3"
              show-arrows
            >
              <v-slide-item
                class="ma-1"
                v-for="food in foodList"
                :key="food.num"
              >
                <v-list class="pa-1 ma-0">
                  <v-list-item-group>
                    <v-list-item class='pa-0'>
                      <v-avatar class="mx-auto" size='30'>
                        <img :src=food.img>
                      </v-avatar>
                    </v-list-item>
                    <v-list-item class='foodname pa-0' dense>
                      {{food.name}}
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
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
        {num:1,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:2,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:3,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:4,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:5,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:6,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:7,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:8,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},

      ]
    }
  },
};
</script>
<style scoped>
  .foodname{
    font-size:12px;
  }
  .v-list-item{
    min-height:30px;
  }
</style>