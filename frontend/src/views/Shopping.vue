<template>
  <div>
   <v-tabs content-class="mt-3" fill fixed-tabs v-model="tab">
      <v-tab> 홈 </v-tab>
      <v-tab> 소고기 </v-tab>
      <v-tab> 과일 </v-tab>
      <v-tab> 채소 </v-tab>
      <v-tab> 디저트 </v-tab>
      <v-tab> 유제품 </v-tab>
    </v-tabs>


  </div>
</template>
<script>


export default {
    components: {
    
  },
   data() {
    return {
      title:"상세페이지",
      tab: null,
      food: {num:1,img:"https://cdn.vuetifyjs.com/images/john.jpg", name:'두부'},
      loaded:false,
      chartData: null,
      Expiration:{date:10.31}
    }
  },
  created() {
    this.$emit('updateTitle', '메인페이지');
    // if(this.$session.get('userinfo')){

    // }
  },
  async mounted() {
    this.chartData = {
      labels: ['용량', ''],
      datasets: [
        {
          backgroundColor: [
            '#9DC8C8','#e2efef'
          ],
          data: [40, 60]
        }
      ],
      
    };
    
    this.loaded = true;
  },

}
</script>
<style scoped>
#inspire{
  width: 80%;
  margin: 0 auto;
  flex: 1 1 auto;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  display: flex;
  flex-direction: column;
  min-height: 1vh !important;
  max-width: 100%;
  position: relative;
}

.expire{
  margin-bottom: 5%;
}
.mx-auto{
  display: flex;
  height: 50vh;
  justify-content: center;
  align-items: center;
  margin-bottom: 5%;
  margin-top: 10%;
}

#inspire{
   margin-left: 70%;
   
}
</style>