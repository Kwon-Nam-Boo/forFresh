<template>
  <div>
    <nav-bar :title="title"></nav-bar>
    <div style="text-align:center; margin-top:20%">
      <img src="@/assets/camera.png" onClick="post" style="width:10%; height:10%;">
      <p>영수증 사진을 등록해주세요</p>
      <input type="file" @change="receiptImage" accept="image/*" />
      <v-img position="center" :src="receiptPicture" />
      <v-button @click="getDummy">등록</v-button>
    </div>
    <div style="height:30%; margin-top: 20%">
      <table style="margin:0px auto; width:70%; height: 30%">
        <thead>
          <th>상품명</th>
          <th>금액</th>
          <th>수량</th>
        </thead>
        <tbody>
            
            <tr v-for="food in foods" :key="food.food_no">
              <td>
                {{ food.name }}
              </td>
              <td>{{ food.price }}</td>
              <td>
                {{ food.no }}
              </td>
              
            </tr>
        </tbody>
      </table>

    </div>
  
      <div id="inspire">
        <v-btn depressed color="#e2efef" style="width:30%">등록하기</v-btn>
      </div>
    


  </div>
</template>
<script>
import NavBar from "../components/NavBar";
import RefApi from "../../src/api/RefApi";
import firebase from "firebase";

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
      imageData: "",
      receiptPicture: null,
      uploadValue: 0,
    }
  },
  methods: {
    receiptImage(event) {
      this.uploadValue = 0;
      this.picture = null;
      this.imageData = event.target.files[0];
    },
    upload(target) {
      return new Promise(resolve => {
        const storageRef = firebase
          .storage()
          .ref(`${target.name}`)
          .put(target);
        storageRef.on(
          `state_changed`,
          (snapshot) => {
            this.uploadValue =
              (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          },
          (error) => {
            console.log(error.message);
          },
          () => {
            this.uploadValue = 100;
            storageRef.snapshot.ref.getDownloadURL().then((url) => {
              resolve(url);
            });
          }
        );
      });
    },
    async getDummy() {
      console.log(this.imageData)
      var self = this;
      const fb1 = await this.upload(this.imageData);
      this.receiptPicture = null;
      RefApi.getReceiptData(
        {
          receiptUrl: fb1,
        },
        (res) => {
          console.log(res);
        },
        (error) => {
          console.log(error);
        }
      );
      alert("잘못된 부분 수정 요망");
    },
    
  }

}
</script>
<style scoped>
#inspire{
   margin-left: 70%;
   
}

</style>