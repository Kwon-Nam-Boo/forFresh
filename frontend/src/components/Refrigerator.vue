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
            v-for="item2 in items2"
            :key="item2[0]">
            <v-card-text :class="item2[1]+'--text pb-3'">{{item2[0]}}</v-card-text>
            <v-slide-group
              class="pb-3"
              show-arrows
            >
              <v-slide-item
                class="ma-1"
                v-for="food in foodList"
                :key="food.foodNo"
              >
                <v-list class="pa-1 ma-0" v-if="food.status == item">
                  <v-list-item-group>
                    <v-list-item class='pa-0' @click="moveDetail">
                      <v-avatar class="mx-auto" size='40'>
                        <!-- <img :src="require(`@/assets/img/${food.img}`)"> -->
                        <img :src="require(`@/assets/img/tofu.png`)">
                      </v-avatar>
                    </v-list-item>
                    <v-list-item class='foodname pa-0' dense @click="moveDetail">
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
import deleteFood from './DeleteFood'
import FoodApi from '../api/FoodApi'
export default {
  props:['refrigNo'],
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


      ],
      options:[
        {text:'추가하기', val:'1'}, {text:'삭제하기', val:'2'}
      ],
      select: '0',
      deleteDialog: false,
    }
  },
  created() {
    const data = {
      refrigNo: this.refrigNo
    }
    FoodApi.getFoodList(
      data,
      (res) => {
        this.foodList = res.data.object;
        var date0 = new Date(this.foodList[3].registDate);
        date0.setDate(date0.getDate()+Number(this.foodList[3].expireDate));
        console.log(date0 - new Date());
      },
      (error) => {

      }
    )
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
    },
    moveDetail() {
      this.$router.push('/detail');
    },
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