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
        <input type="text" placeholder="상세 주소" v-model="addressDetail" />
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
      <v-row>
        <v-col>
          <v-select
            :items="items"
            label="냉장고선택"
            v-model="refName"
            dense
            outlined
          ></v-select>
        </v-col>
      </v-row>
      <hr />
      <v-container>
        <v-form ref="form">
          <v-row>
            <v-col cols="6">
              <v-img max-width="200px" :src="product.imgUrl"></v-img>
            </v-col>
            <v-col cols="6">
              <p>상품명 : {{ product.productName }}</p>
              <p>가격 : {{ product.productPrice }}</p>

              <v-text-field
                v-model="curStock"
                :rules="stockRules"
                label="수량"
                style="width: 60px"
                type="number"
                outlined
                required
              ></v-text-field>
              남은 수량 : {{ product.stock - curStock }}
            </v-col>
            <p class="ml-11">
              상품 금액 : {{ product.productPrice * curStock }} 원
            </p>
          </v-row>
        </v-form>
      </v-container>
    </v-card>
    <v-card class="mt-3">
      <v-card-title>결제 현황</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <p>총 상품 금액 : {{ product.productPrice * curStock }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>총 수량 : {{ curStock }} 개</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>배송비 : {{ postMethod() }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>주문 금액 : {{ post + product.productPrice * curStock }} 원</p>
          </v-col>
          <v-col>
            <button @click="payment()">
              <v-img
                :src="require(`@/assets/payment_icon_yellow_small.png`)"
              ></v-img>
            </button>
            <!-- 구매하기 버튼 -->
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </div>
</template>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
import ProductApi from "../../api/ProductApi";
import PaymentApi from "../../api/PaymentApi";
import RefApi from "../../api/RefApi";

const storage = window.sessionStorage;

export default {
  data() {
    return {
      title: "구매 페이지",
      stockRules: [
        (v) => (v > 0 && v <= this.product.stock) || "수량을 확인하세요",
      ],
      curStock: 1,
      post: 0,
      productPlusPostTotal: 0,
      refrigNo: "no",
      refrigList: [],
      items: ["선택안함"],
      refName: "",
      product: {},
    };
  },
  computed: {
    zipcode() {
      return document.getElementById("zipcode").value;
    },
    address() {
      return document.getElementById("street").value;
    },
  },
  created() {
    this.$emit("updateTitle", this.title);
    ProductApi.requestProductDetail(
      {
        userId: storage.getItem("login_user"),
        productNo: this.$route.params.productno,
      },
      (res) => {
        this.product = res.data.object;
      },
      (error) => {
      }
    );
    this.refrigList = this.getRef();
  },
  methods: {
    payment() {
      if (!this.$refs.form.validate()) {
        alert("수량을 확인하세요");
        return;
      }
      for (var i = 0; i < this.refrigList.length; i++) {
        if (this.refrigList[i].refrigName == this.refName) {
          this.refrigNo = this.refrigList[i].refrigNo;
        }
      }
      PaymentApi.requestPayment(
        {
          userId: storage.getItem("login_user"),
          refrigNo: this.refrigNo,
          shoplistNo: "no",
          stockList: String(this.curStock),
          productNo: this.$route.params.productno,
          itemName: this.product.productName,
          priceList: this.product.productPrice,
          quantity: String(this.curStock),
          totalAmount: String(
            this.post + this.product.productPrice * this.curStock
          ),
        },
        (res) => {
          location.href = res.data;
        },
        (error) => {
        }
      );
    },
    postMethod() {
      if (this.curStock * this.product.productPrice >= 10000) {
        return (this.post = 0);
      } else {
        return (this.post = 2500);
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
    getRef() {
      const data = {
        userId: storage.getItem("login_user"),
      };
      RefApi.getRef(
        data,
        (res) => {
          this.refrigList = res.data.object;
          for (var j = 0; j < this.refrigList.length; j++) {
            this.items.push(this.refrigList[j].refrigName);
          }
        },
        (error) => {}
      );
    },
    // productPlusPostTotalMethod() {
    //   return (this.productPlusPostTotal =
    //     this.post + this.curStock * Number(this.prouct.productPrice));
    // },
  },
};
</script>

<style lang="">
</style>