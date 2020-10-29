<template>
  <div>
    <nav-bar :title="title"></nav-bar>
    <div style="text-align:center; margin-top:20%">
         <img src="@/assets/camera.png" onClick="post" style="width:10%; height:10%;">
         <p>영수증 사진을 등록해주세요</p>
    </div>
    <div style="height:90%; margin-top: 20%">
      <table style="margin:0px auto; width:70%">
        <thead>
          <th>상품명</th>
          <th>금액</th>
          <th>수량</th>
        </thead>
        <tbody>

        </tbody>
      </table>

    </div>
    <div >
      <v-app id="inspire">
        <v-btn depressed color="#e2efef" style="width:30%">등록하기</v-btn>
      </v-app>
    </div>


  </div>
</template>
<script>
import NavBar from "../components/NavBar";


export default {
    components: {
    NavBar,
    
  },
   data() {
    return {
      title:"등록페이지",
      tab: null,
      items: [
        'ExpirationDate', 'Entrees', 'Deserts', 'Cocktails',
      ],
      loaded:false,
      chartData: null,
    }
  },
  

}
</script>
<style scoped>
#inspire{
   margin-left: 70%;
   
}

</style>