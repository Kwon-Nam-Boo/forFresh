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
      >
        삭제하기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props:["items"],
  data(){
    return{
      selected:[],
      alertType: false,
      alertMessage: "",
    }
  },
  created() {

  },
  methods: {
    deleteFood() {
      //삭제하고 checkboxList비우기
      this.closeDialog();
    },
    closeDialog() {
      this.alertType = false;
      this.alertMessage = "";
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