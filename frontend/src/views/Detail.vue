<template>
  <div>
    <nav-bar :title="title"></nav-bar>
    <div>
         <img>
         <p>{{name}}</p>
    </div>
    <div >
      <div class="expire">
        <p style="margin-left:3%;"> 유통기한 </p>
        <v-app id="inspire" style=" margin-top:10%">
        <v-progress-linear value="30" height="25" color="#41D3BD" rounded></v-progress-linear>
        </v-app>
        <p style=" font-size:12px; float:left; margin-right:74%; margin-left:5%">10월 1일</p>
        <p style=" font-size:12px;  margin-right:5%">10월 31일</p>
      </div>
        <div>
        <p style="margin-left:3%">영양성분</p> 
        <Doughnut v-if="loaded" :chartData="chartData" :options="options" style="height:30vh;width:30vw; margin-left:65%"/>
        </div>
        <p style="margin-left:3%">수량</p>
        
    </div>


  </div>
</template>
<script>
import NavBar from "../components/NavBar";
import Doughnut from "../vuex/Doughnut.js";

export default {
    components: {
    NavBar,
    Doughnut,
  },
   data() {
    return {
      title:"상세페이지",
      tab: null,
      items: [
        'ExpirationDate', 'Entrees', 'Deserts', 'Cocktails',
      ],
      loaded:false,
      chartData: null,
    }
  },
  async mounted() {
    this.chartData = {
      labels: ['용량', ''],
      datasets: [
        {
          backgroundColor: [
            '#41D3BD',
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
   
}
#v-application--wrap {
    flex: 1 1 auto;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    display: flex;
    flex-direction: column;
    min-height: 20vh !important;
    max-width: 100%;
    position: relative;
}
.expire{
  margin-bottom: 5%;
}
</style>