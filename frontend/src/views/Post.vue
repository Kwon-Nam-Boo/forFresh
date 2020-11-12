<template>
  <div>
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
            <v-icon small class="mr-2" @click="editItem(food)">mdi-pencil</v-icon>
            <v-icon small @click="deleteFood(index)">mdi-delete</v-icon>
          </tr>
          <v-dialog v-model="dialog" max-width="500px">  
            <v-card>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.foodName" label="제품명"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.count" label="수 량"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
  
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="editClose">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="editSave">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
      </tbody>
    </table>
    <div style="text-align:center; margin-top:5%; margin-bottom: 10%;">
      <v-btn depressed color="#e2efef" style="width:80%" @click="putFood">냉장고에 넣기</v-btn>
    </div>
  </div>
</template>
<script>
import RefApi from "../../src/api/RefApi";
import firebase from "firebase";
const storage = window.sessionStorage;
export default {
    components: {
    
  },
   data() {
    return {
      title:"등록페이지",
      dialog : false,
      chartData: null,
      imageData: "",
      receiptPicture: "https://firebasestorage.googleapis.com/v0/b/forfresh-ea84c.appspot.com/o/%EC%9D%B4%EB%A7%88%ED%8A%B8%EC%98%81%EC%88%98%EC%A6%9D.jpg?alt=media&token=114978f8-4f47-4196-af8c-6defb20a1a12",
      uploadValue: 0,
      foods: [{"foodName": "재사용종량20L_ 왕십리", "price": 490, "count": 1}, {"foodName": "프리미엄시크릿양념치", "price": 8980, "count": 1}, {"foodName": "노브랜드 콜라 오리", "price": 2280, "count": 1}, {"foodName": "대추방울토마토 750g/", "price": 8980, "count": 1}, {"foodName": "1등급란 15개입 대란", "price": 3980, "count": 1}, {"foodName": "해태제과 골라담기", "price": 3980, "count": 1}, {"foodName": "CJ 미정당비엔나떡볶", "price": 2980, "count": 1}, {"foodName": "CJ 미정당어묵떡볶이3", "price": 2980, "count": 1}, {"foodName": "해태 초코홈런볼5번들", "price": 1598, "count": 1}, {"foodName": "오리온 대단한나쵸", "price": 17, "count": 1}, {"foodName": "해태 감자칩버터갈릭", "price": 3980, "count": 1}],
      editedItem : {
                  foodName : "",
                  count: 0,
                  },
      defaultItem : {
                  foodName : "",
                  count: 0,
                  }
    }
  },
  created() {
    this.$emit('updateTitle',this.title);
  },
   watch: {
    // dialog (val) {
    //   val || this.editClose()
    // },
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
          refrigNo: storage.getItem('RefNoForAddFood'),
          foods: JSON.stringify(this.foods),
        },
        (res) => {
          console.log("음식 넣기 완료")
        },
        (error) => {
          console.log(error);
        }
      );
    },
    editItem(food) {
      this.editIndex = this.foods.indexOf(food)
      this.editedItem = Object.assign({}, food)
      this.dialog = true
    },
    editClose() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editIndex = -1
      })
    },
    editSave() {
      if (this.editIndex > -1) {
        Object.assign(this.foods[this.editIndex], this.editedItem)
      } else {
        this.foods.push(this.editedItem)
      }
      this.editClose()
    },
  }

}
</script>
<style scoped>
#post{
   margin-left: 70%;
   
}

</style>