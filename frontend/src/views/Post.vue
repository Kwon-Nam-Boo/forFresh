<template>
  <div>
    <div v-if="receiptPicture == null" style="text-align:center; margin-top:20%; margin-bottom: 20%;">
      <img src="@/assets/camera.png" onClick="post" style="width:10%; height:10%;">
      <p>영수증 사진을 등록해주세요</p>
    </div>

    <div v-else style="text-align:center; margin: 20px; margin-top:10%">
      <p>영수증 사진 미리보기</p>
      <v-img position="center" :src="receiptPicture" />
    </div>
    
    <div style="display: flex; margin: 20px;">
      <input type="file" @change="receiptImage" accept="image/*" />
      <v-btn depressed v-show="imageData" color="#e2efef" style="width:20%;" @click="getDummy">등록</v-btn>
    </div>

    <table style="margin: auto; margin-top:20px; width:80%;">
      <thead>
        <th>상품명</th>
        <th>수량</th>
        <th>단가</th>
        <th></th>
      </thead>
      <tbody>
          <tr v-for="(food, index) in foods" :key="index">
            <td style="font-size: small;">
              {{ food.foodName }}
            </td>
            <td style="text-align: center; font-size: small;">
              {{ food.count }}
            </td>                       
            <td style="text-align: center; font-size: small;">
              {{ food.price }}
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
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="editedItem.price" label="단 가"></v-text-field>
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
          
          <v-dialog v-model="addDialog" max-width="500px">  
            <v-card>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="addedItem.foodName" label="제품명"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="addedItem.count" label="수 량"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field v-model="addedItem.price" label="단 가"></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
  
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="addClose">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="addSave">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
      </tbody>
    </table>
    <div style="margin-top: 5px; text-align: center;">
      <v-icon class="mr-2" @click="addItem">mdi-plus-circle-outline</v-icon>
    </div>
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
      addDialog : false,
      chartData: null,
      imageData: "",
      receiptPicture: null,
      uploadValue: 0,
      foods: [],
      editedItem : {
                  foodName : "",
                  count: 1,
                  price: 0,
                  },
      addedItem : {
                  foodName : "",
                  count: 1,
                  price: 0,
                  },
      defaultItem : {
                  foodName : "",
                  count: 1,
                  price: 0,
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
      console.log(this.foods)
      RefApi.registFood(
        {
          refrigNo: storage.getItem('RefNoForAddFood'),
          foods: JSON.stringify(this.foods),
        },
        (res) => {
          this.$router.push('/home').catch(() => {})
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
    addItem() {
      this.addDialog = true
    },
    addClose() {
      this.addDialog = false
      this.$nextTick(() => {
        this.addedItem = Object.assign({}, this.defaultItem)
      })
    },
    addSave() {
      this.foods.push(this.addedItem)
      this.addClose()
    },
  }

}
</script>
<style scoped>
#post{
   margin-left: 70%;
   
}

</style>