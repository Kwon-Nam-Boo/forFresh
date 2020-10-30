<template>
  <div>
   
    <!-- <nav-bar :title="title"></nav-bar> -->
    <div class="food">
         <v-avatar class="mx-auto" size='90'>
          <img :src=food.img>
        </v-avatar>
         <p style="margin-left:45%;font-weight: bold ;">{{food.name}}</p>
    </div>
    <div >
      <div class="expire">
        <p style="margin-left:3%;font-weight: bold ;"> 유통기한 10.31 까지</p>
        <div id="inspire" style=" margin-top:10%">
          <v-progress-linear value="30" height="25" color="#9DC8C8" rounded></v-progress-linear>
        </div>
        <p style=" font-size:12px; float:left; margin-right:60%; margin-left:5%">10월 1일</p>
        <p style=" font-size:12px;  margin-right:5%">10월 31일</p>
      </div>
        <div>
        <p style="margin-left:3%;font-weight: bold ;">영양성분</p> 
        <Doughnut v-if="loaded" :chartData="chartData" :options="options" style="height:30vh;width:30vw; margin-left:65%"/>
        </div>
        <p style="margin-left:3%;font-weight: bold ;">수량</p>
    </div>
    <div id="inspire">
        <v-btn depressed color="#e2efef" style="width:30%">삭제하기</v-btn>
      </div>


  </div>
</template>
<script>
// import NavBar from "../components/NavBar";
import Doughnut from "../vuex/Doughnut.js";

export default {
    components: {
    // NavBar,
    Doughnut,
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