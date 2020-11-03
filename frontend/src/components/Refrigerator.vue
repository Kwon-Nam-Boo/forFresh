<template>
  <div class="ma-5">
    <v-row>
      <v-col>
        <v-card width="180" height="30">
          <v-tabs
            v-model="tab"
            background-color=""
            color="#88dba3"
            grow
            height="30"
          >
            <v-tabs-slider color="#88dba3"></v-tabs-slider>
            <v-tab
              class="px-1"
              v-for="item in items"
              :key="item"
            >
              {{item}}
            </v-tab>
          </v-tabs>
        </v-card>
      </v-col>
      <v-col>
        <v-select
          v-model="select"
          :items="options"
          item-text="text"
          item-value="val"
          label="식재료 수정"
          dense
          solo
        ></v-select>
        <v-dialog
          v-model="deleteDialog"
        >
          <delete-food @close="closeDialog"></delete-food>
        </v-dialog>
      </v-col>
    </v-row>
    
    
    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
          <v-card 
            class="ma-4"
            outlined
            v-for="item in items2"
            :key="item[0]">
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
                      <v-avatar class="mx-auto" size='40'>
                        <img :src=food.img>
                      </v-avatar>
                    </v-list-item>
                    <v-list-item class='foodname pa-0' dense>
                      <p class='ma-auto'>{{food.name}}</p>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>
              </v-slide-item>
            </v-slide-group>
          </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import deleteFood from './DeleteFood'
export default {
  components:{
    deleteFood,
  },
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

      ],
      options:[
        {text:'추가하기', val:'1'}, {text:'삭제하기', val:'2'}
      ],
      select: '0',
      deleteDialog: false,
    }
  },
  watch: {
    select: function() {
      if(this.select==1){
        this.$router.push('/post');
      } else if(this.select==2){
        this.deleteDialog = true;
      }
    },
  },
  methods: {
    closeDialog() {
      this.select = 0;
      this.deleteDialog = false;
    }
  }
};
</script>
<style scoped>
  .foodname{
    font-size:12px;
  }
  .v-list-item{
    min-height:30px;
  }
  .v-card{
    border-color:#e2efef;
    border-width: 2px;
  }
  .v-select{
    font-size:12px;
  }

</style>