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
          <DeleteFood @close="closeDialog" :items="foodList" :refrigNo="refrigNo" @getFood="getFood"></DeleteFood>
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
            v-for="item2 in items2"
            :key="item2[0]">
            <v-card-text :class="item2[1]+'--text pb-3'">{{item2[0]}}</v-card-text>
            <v-slide-group
              class="pb-3"
              show-arrows
            >
              <v-slide-item
                class="ma-1"
                v-for="(food, i) in foodList"
                :key="i"
              >
                <v-list class="pa-1 ma-0" v-if="food.status == item && dateList[i] <= item2[2] && dateList[i] > item2[3]">
                  <v-list-item-group>
                    <v-list-item class='pa-0' @click="moveDetail(food.foodNo)">
                      <v-avatar class="mx-auto" size='40'>
                        <img :src="$store.state.foodCategoryList[food.categoryNo].img">
                      </v-avatar>
                    </v-list-item>
                    <v-list-item class='foodname pa-0' dense @click="moveDetail(food.foodNo)">
                      <p class='ma-auto'>{{food.foodName}}</p>
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
import DeleteFood from './DeleteFood'
import FoodApi from '../api/FoodApi'
const storage = window.sessionStorage;
export default {
  props:['refrigNo'],
  components:{
    DeleteFood,
  },
  data() {
    return {
      tab: null,
      items: [
        '냉장', '냉동',
      ],
      items2: [
        ['위험', 'red', 2, -100000], ['보통', 'blue', 4, 2], ['신선', 'green', 100000, 4],
      ],
      foodList: [],
      dateList: [],
      options:[
        {text:'추가하기', val:'1'}, {text:'삭제하기', val:'2'}
      ],
      select: '0',
      deleteDialog: false,
    }
  },
  created() {
    this.getFood();
  },
  watch: {
    select: function() {
      if(this.select==1){
        storage.setItem('RefNoForAddFood',this.refrigNo);
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
    },
    moveDetail(foodNo) {
      storage.setItem('foodNo', foodNo);
      this.$router.push('/detail');
    },
    getFood() {
      const data = {
      refrigNo: this.refrigNo
      }
      FoodApi.getFoodList(
        data,
        (res) => {
          this.foodList = res.data.object;
          for(var food of this.foodList){
            var dateCur = new Date(food.registDate);
            dateCur.setDate(dateCur.getDate()+Number(food.expireDate));
            this.dateList.push((dateCur - new Date())/1000/60/60/24);
          }
        },
        (error) => {

        }
      )
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