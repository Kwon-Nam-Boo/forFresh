<template>
  <div>
    <nav-bar :title="title"></nav-bar>
    <div v-if="receiptPicture == null" style="text-align:center; margin-top:20%">
      <img src="@/assets/camera.png" onClick="post" style="width:10%; height:10%;">
      <p>영수증 사진을 등록해주세요</p>
    </div>

    <div v-else style="text-align:center; margin: 20px; margin-top:10%">
      <p>영수증 사진 미리보기</p>
      <v-img position="center" :src="receiptPicture" />
    </div>
    
    <div style="display: flex; margin: 20px;">
      <input type="file" @change="receiptImage" accept="image/*" />
      <v-btn depressed color="#e2efef" style="width:20%;" @click="getDummy">등록</v-btn>
    </div>

    <table style="margin: auto; margin-top:20px; width:80%;">
      <thead>
        <th>상품명</th>
        <th>수량</th>
        <th></th>
      </thead>
      <tbody>
          <tr v-for="(food, index) in foods" :key="index">
            
            <td>
              {{ food.foodName }}
            </td>
            <td style="text-align: center;">
              {{ food.count }}
            </td>
            <v-btn x-small color="error" @click="deleteFood(index)">삭제</v-btn>
          </tr>
      </tbody>
    </table>
    <div style="text-align:center; margin-top:5%; margin-bottom: 10%;">
      <v-btn depressed color="#e2efef" style="width:80%" @click="putFood">냉장고에 넣기</v-btn>
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
      receiptPicture: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/%EC%9D%B4%EB%A7%88%ED%8A%B8%EC%98%81%EC%88%98%EC%A6%9D.jpg?alt=media&token=114978f8-4f47-4196-af8c-6defb20a1a12",
      uploadValue: 0,
      foods: [{"foodName": "재사용종량20L_ 왕십리", "price": 490, "count": 1}, {"foodName": "프리미엄시크릿양념치", "price": 8980, "count": 1}, {"foodName": "노브랜드 콜라 오리", "price": 2280, "count": 1}, {"foodName": "대추방울토마토 750g/", "price": 8980, "count": 1}, {"foodName": "1등급란 15개입 대란", "price": 3980, "count": 1}, {"foodName": "해태제과 골라담기", "price": 3980, "count": 1}, {"foodName": "CJ 미정당비엔나떡볶", "price": 2980, "count": 1}, {"foodName": "CJ 미정당어묵떡볶이3", "price": 2980, "count": 1}, {"foodName": "해태 초코홈런볼5번들", "price": 1598, "count": 1}, {"foodName": "오리온 대단한나쵸", "price": 17, "count": 1}, {"foodName": "해태 감자칩버터갈릭", "price": 3980, "count": 1}],
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
      var self = this;
      const fb1 = await this.upload(this.imageData);
      this.receiptPicture = fb1;
      RefApi.getReceiptData(
        {
          receiptUrl: fb1,
        },
        (res) => {
          var temp = JSON.parse(res.data.object)
          this.foods = temp.data
          console.log(this.foods)
        },
        (error) => {
          console.log(error);
        }
      );
      alert("잘못된 부분 수정 요망");
    },
    deleteFood(idx){
      if (idx > -1) this.foods.splice(idx, 1)
    },
    putFood(){
      RefApi.registFood(
        {
          refrigNo: 1,
          foods: this.foods,
        },
        (res) => {
          console.log("음식 넣기 완료")
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