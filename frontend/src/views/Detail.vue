<template>
  <div>
   
    <!-- <nav-bar :title="title"></nav-bar> -->
    <div class="food">
         <v-avatar class="mx-auto" size='90'>
          <img :src="$store.state.foodCategoryList[food.categoryNo].img">
        </v-avatar>
         <p style="margin-left:45%;font-weight: bold ;">{{food.foodName}}</p>
    </div>
    <div >
      <div class="expire">
        <p style="margin-left:3%;font-weight: bold ;"> 
          유통기한 {{expireDate.getFullYear()}}년 {{expireDate.getMonth()+1}}월 {{expireDate.getDate()}}일 까지
        </p>
        <p :class="dateStatus.color+'--text pb-3'" style="margin-left:3%;">{{dateStatus.stat}}</p>
        <div id="inspire" style=" margin-top:5%">
          <v-progress-linear :value="progressVal" height="25" color="#9DC8C8" rounded></v-progress-linear>
        </div>
        <p style=" font-size:12px; float:left; margin-left:5%">
          {{regDate.getFullYear()}}년 {{regDate.getMonth()+1}}월 {{regDate.getDate()}}일
        </p>
        <p style=" font-size:12px; float:right; margin-right:5%">
          {{expireDate.getFullYear()}}년 {{expireDate.getMonth()+1}}월 {{expireDate.getDate()}}일
        </p>
      </div>
        <!-- <div>
        <p style="margin-left:3%;font-weight: bold ;">영양성분</p> 
        <Doughnut v-if="loaded" :chartData="chartData" :options="options" style="height:30vh;width:30vw; margin-left:65%"/>
        </div> -->
        <p style="margin-left:3%;font-weight: bold ;">수량 : {{food.stock}}개</p>
        <p style="margin-left:3%;font-weight: bold ;">단가 : {{food.price}}원</p>
    </div>

    <v-row>
      <v-col>
        <v-btn depressed color="#e2efef" style="margin-left:25%; width:30%">홈</v-btn>
      </v-col>
      <v-col >
        <v-btn depressed color="#e2efef" style="float:right; margin-right:25%; width:30%">삭제하기</v-btn>
      </v-col>
    </v-row>


  </div>
</template>
<script>
// import NavBar from "../components/NavBar";
// import Doughnut from "../vuex/Doughnut.js";
import FoodApi from "../api/FoodApi";
const storage = window.sessionStorage;
export default {
    components: {
    // NavBar,
    // Doughnut,
  },
   data() {
    return {
      title:"상세페이지",
      tab: null,
      food: {
        foodName: "",
        stock: "",
        price: "",
        categoryNo: 0,
      },
      loaded:false,
      chartData: null,
      expireDate: new Date(),
      dateNow: new Date(),
      regDate: new Date(),
      progressVal: 0,
      dateStatus: {
        stat: "",
        color: "",
      },
    }
  },
  created() {
    this.$emit('updateTitle', '메인페이지');
    const data = {
      foodNo: storage.getItem('foodNo'),
    }
    FoodApi.getFoodByNo(
      data,
      (res) => {
        this.food = res.data.object;
        this.regDate = new Date(this.food.registDate);
        this.expireDate = new Date(this.regDate);
        this.expireDate.setDate(this.expireDate.getDate()+Number(this.food.expireDate));
        this.calProgressVal();
        this.setDateStatus();
      },
      (error) => {

      }
    )
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
  methods: {
    calProgressVal(){
      this.progressVal = (this.dateNow - this.regDate)/(this.expireDate - this.regDate)*100;
      if(this.dateNow > this.expireDate){
        this.progressVal = 100;
      }
    },
    setDateStatus(){
      var remain = (this.expireDate - this.dateNow)/1000/60/60/24;
      if(remain <= 2){
        this.dateStatus.stat = "위험";
        this.dateStatus.color = "red";
      } else if(remain > 2 && remain <= 4){
        this.dateStatus.stat = "보통";
        this.dateStatus.color = "blue";
      } else {
        this.dateStatus.stat = "신선";
        this.dateStatus.color = "green";
      }
    },
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
  margin-bottom: 10%;
}
.mx-auto{
  display: flex;
  height: 50vh;
  justify-content: center;
  align-items: center;
  margin-bottom: 5%;
  margin-top: 10%;
}
</style>