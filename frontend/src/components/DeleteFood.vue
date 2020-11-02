<template>
  <v-card>
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
              <img :src=item.img>
            </v-avatar>
          </v-list-item>
          <v-list-item class='foodname pa-0' dense>
            <v-checkbox
              class='ma-auto'
              v-model="checkboxList[i]"
              :label="item.name"
            >
            </v-checkbox>
            
          </v-list-item>
          <!-- <v-list-item class='pa-0'>
            <v-checkbox
              class='ma-auto'
              v-model="checkboxList[i]"
            >
            </v-checkbox>
          </v-list-item> -->
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
  data(){
    return{
      items:[
        {num:1,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:2,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:3,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:4,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:5,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:6,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:7,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
        {num:8,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
      ],
      checkboxList:[],
    }
  },
  methods: {
    deleteFood() {
      //삭제하고 checkboxList비우기
      this.closeDialog();
    },
    closeDialog() {
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