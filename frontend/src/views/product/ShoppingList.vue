<template>
  <div>
    <v-card>
      <v-card-title>배송 정보</v-card-title>
      <div class="col-lg-12">
                  <label for="street">
                    주소
                    <span>*</span>
                  </label>
                  <div class="col-lg-4" style="margin: 0; padding: 0">
                    <input
                      type="text"
                      placeholder="우편번호"
                      id="zipcode"
                      @click="getAddress"
                      readonly
                    />
                  </div>
                  <input
                    type="text"
                    id="street"
                    class="street-first"
                    placeholder="주소"
                    @click="getAddress"
                    readonly
                  />
                  <input
                    type="text"
                    placeholder="상세 주소"
                    v-model="addressDetail"
                  />
                </div>
                <div class="col-lg-12">
                  <label for="request">요청사항</label>
                  <input type="text" id="request" v-model="uniqueness" />
                </div>
                <div class="col-lg-12">
                  <label for="phone">
                    연락처
                    <span>*</span>
                  </label>
                  <input type="text" id="phone" v-model="phonenumber" />
                </div>

    </v-card>
    <v-card class="mt-3">
      <v-card-title>상품 정보</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <v-checkbox
              v-model="allselect"
              label="전체 선택"
              value="all"
              @click="initList()"
              float
            ></v-checkbox>
          </v-col>
          <v-col>
            <v-btn absolute right class="mt-3" @click="delShopList()"
              >선택 삭제</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
      <hr />
      <v-container>
        <v-row v-for="item in shopList" :key="item.shoplistNo">
          <!-- <v-checkbox v-model="selected" :value="item.productNo" ></v-checkbox> -->
          <v-col cols="1">
            <v-checkbox
              v-model="selected"
              :value="item.shoplistNo"
            ></v-checkbox>
          </v-col>
          <v-col cols="6">
            <v-img max-width="200px" :src="item.imgUrl"></v-img>
          </v-col>
          <v-col cols="5">
            <p>상품명 : {{ item.productName }}</p>
            <p>가격 : {{ item.productPrice }}</p>

            <v-text-field
              v-model="item.stock"
              :rules="stockRules"
              label="수량"
              style="width: 60px"
              type="number"
              outlined
              required
            ></v-text-field>
          </v-col>
          <p class="ml-11">주문 금액 : {{ item.productPrice * item.stock }}</p>
        </v-row>
      </v-container>
      <!-- <v-checkbox v-model="selected" label="John" value="John"></v-checkbox>
      <v-checkbox v-model="selected" label="Jacob" value="Jacob"></v-checkbox> -->
    </v-card>
    <v-card class="mt-3">
      <v-card-title>결제 현황</v-card-title>
    </v-card>
  </div>
</template>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import ProductApi from "../../api/ProductApi";


const storage = window.sessionStorage;
export default {
  data() {
    return {
      title: "장바구니",
      stockRules: [(v) => v > 0 || "수량 등록이 필요합니다."],
      selected: [],
      allselect: ["all"],
      initStatus: false,
      shopList: [],
      selectedAllCopy: [],
      sum: 0,
    };
  },
  computed:{
    zipcode() {
      return document.getElementById("zipcode").value;
    },
    address() {
      return document.getElementById("street").value;
    },

  },
  created() {
    this.$emit("updateTitle", this.title);
    ProductApi.requestUserShoppingList(
      storage.getItem("login_user"),
      (res) => {
        this.shopList = res.data;
        for (var i = 0; i < this.shopList.length; i++) {
          this.shopList[i].stock = 1;
          this.selected.push(this.shopList[i].shoplistNo);
        }
        this.selectedAllCopy = this.selected;
        // console.log(this.shopList);
      },
      (error) => {}
    );
  },
  methods: {
    initList() {
      if (this.initStatus) {
        this.selected = this.selectedAllCopy;
        this.allselect = ["all"];
      } else {
        this.selected = [];
        this.allselect = [];
      }
      this.initStatus = !this.initStatus;
    },
    delShopList() {
      // console.log(this.selected);
      for (var i = 0; i < this.selected.length; i++) {
        ProductApi.deleteShopList(
          this.selected[i],
          (res) => {
            location.reload(true);
          },
          (error) => {}
        );
      }
    },
    getAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          document.getElementById("street").value = data.address; // 도로명 주소 변수
          document.getElementById("zipcode").value = data.zonecode;
        },
      }).open();
    },
  },
};
</script>
<style lang="">
</style>