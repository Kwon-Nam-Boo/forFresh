<template>
  <v-card>
    <v-alert :type="alertType" v-if="alertMessage">
      {{alertMessage}}
    </v-alert>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      식재료 삭제하기
    </v-card-title>
    <v-row>

        <v-col
            v-for="(item,i) in items"
            :key="i"
            cols="4"
        >
          <v-list-item 
            class='pa-0'

          >
            <v-avatar class="mx-auto" size='40'>
              <img :src="$store.state.foodCategoryList[item.categoryNo].img">
            </v-avatar>
          </v-list-item>
          <v-list-item class='foodname pa-0' dense>
            <v-checkbox
              class='ma-auto'
              v-model="selected"
              :value="i"
              :label="item.foodName"
            >
            </v-checkbox>
            
          </v-list-item>
        </v-col>
              </v-row>
    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="primary"
        text
        @click="deleteFood"
        :disabled="!isSubmit"
      >
        삭제하기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import FoodApi from '../api/FoodApi'
export default {
  props:["items", "refrigNo"],
  data(){
    return{
      selected:[],
      alertType: false,
      alertMessage: "",
      isSubmit: true,
    }
  },
  created() {

  },
  methods: {
    deleteFood() {
      this.isSubmit = false;
      const foodNoList = [];
      for(var index of this.selected){
        foodNoList.push(this.items[index].foodNo);
      }
      const data = {
        foodNoList: foodNoList,
      }
      FoodApi.deleteFood(
        data,
        (res) => {
          this.$emit('getFood');
          this.alertType = "success";
          this.alertMessage = "식재료 삭제가 완료되었습니다.";
          this.selected = [];
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {
          this.isSubmit = true;
          this.alertType = "error";
          this.alertMessage = "식재료 삭제 중 에러가 발생하였습니다.";
        }
      )
    },
    closeDialog() {
      this.alertType = false;
      this.alertMessage = "";
      this.isSubmit = true;
      this.$emit('close');
    }
  }
}
</script>
<style scoped>
  .v-list-item{
    min-height:30px;
  }
</style>