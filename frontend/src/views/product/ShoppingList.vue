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
            <v-select
              :items="items"
              label="냉장고선택"
              v-model="refName"
              dense
              outlined
            ></v-select>
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
        <v-form ref="form">
          <v-row v-for="(item, i) in shopList" :key="item.shoplistNo">
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
              남은 수량 : {{ calStock(i, item.stock) }}
            </v-col>
            <p class="ml-11">
              상품 금액 : {{ item.productPrice * item.stock }} 원
            </p>
          </v-row>
        </v-form>
      </v-container>
      <!-- <v-checkbox v-model="selected" label="John" value="John"></v-checkbox>
      <v-checkbox v-model="selected" label="Jacob" value="Jacob"></v-checkbox> -->
    </v-card>
    <v-card class="mt-3">
      <v-card-title>결제 현황</v-card-title>
      <v-container>
        <v-row>
          <v-col>
            <p>총 상품 금액 : {{ productTotalMethod() }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>총 수량 : {{ stockCnt }} 개</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>배송비 : {{ postMethod() }} 원</p>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <p>주문 금액 : {{ productPlusPostTotalMethod() }} 원</p>
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
  components: {},
  data() {
    return {
      title: "장바구니",
      stockRules: [(v) => (v > 0 && v <= this.maxStock) || "수량을 확인하세요"],
      maxStock: 0,
      selected: [],
      allselect: ["all"],
      initStatus: false,
      shopList: [],
      selectedAllCopy: [],
      originStock: [],
      productTotal: 0,
      post: 0,
      productPlusPostTotal: 0,
      stockCnt: 0,
      itemName: "",
      productNo: "",
      shopNo: "",
      stockList: "",
      priceList: "",
      refrigNo: "no",
      refrigList: [],
      items: ["선택안함"],
      refName: "",
      addressDetail: "",
      uniqueness: "",
      phonenumber: "",
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
  watch: {
    selected: {
      deep: true,
      immediate: true,
      handler(selected) {
        var temp = this.shopList;
        this.shopNo = "";
        this.itemName = "";
        this.productNo = "";
        this.stockList = "";
        this.priceList = "";
        for (var i = 0; i < selected.length; i++) {
          this.shopNo += selected[i] + " ";
          for (var j = 0; j < temp.length; j++) {
            if (selected[i] == temp[j].shoplistNo) {
              this.itemName += temp[j].productName + " ";
              this.productNo += temp[j].productNo + " ";
              this.stockList += temp[j].stock + " ";
              this.priceList += temp[j].productPrice + " ";
            }
          }
        }
      },
    },
  },
  created() {
    this.$emit("updateTitle", this.title);

    ProductApi.requestUserShoppingList(
      storage.getItem("login_user"),
      (res) => {
        this.shopList = res.data;
        for (var i = 0; i < this.shopList.length; i++) {
          this.originStock.push({
            shoplistNo: this.shopList[i].shoplistNo,
            stock: this.shopList[i].stock,
          });
          this.shopList[i].stock = 1;
          this.selected.push(this.shopList[i].shoplistNo);
        }
        this.selectedAllCopy = this.selected;
      },
      (error) => {}
    );
    this.refrigList = this.getRef();
  },
  methods: {
    calStock(idx, curStock) {
      this.maxStock = this.originStock[idx].stock;
      return this.originStock[idx].stock - curStock;
    },
    payment() {
      if (!this.$refs.form.validate()) {
        alert("수량을 확인하세요");
        return;
      }
      if (this.shopList.length == 0) {
        alert("구매할 상품이 없습니다.");
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
          shoplistNo: this.shopNo,
          stockList: this.stockList,
          productNo: this.productNo,
          itemName: this.itemName,
          priceList: this.priceList,
          quantity: String(this.stockCnt),
          totalAmount: String(this.productPlusPostTotal),
        },
        (res) => {
          location.href = res.data;
        },
        (error) => {
        }
      );
    },
    productTotalMethod() {
      var temp = this.shopList;
      var cost = 0;
      var cnt = 0;
      for (var i = 0; i < this.selected.length; i++) {
        var tempNo = this.selected[i];
        for (var j = 0; j < temp.length; j++) {
          var tmp = temp[j];
          if (tmp.shoplistNo == tempNo) {
            cnt += Number(tmp.stock);
            cost += tmp.productPrice * tmp.stock;
          }
        }
      }
      this.stockCnt = cnt;
      return (this.productTotal = cost);
    },
    postMethod() {
      if (this.productTotal >= 10000) {
        return (this.post = 0);
      } else {
        return (this.post = 2500);
      }
    },
    productPlusPostTotalMethod() {
      return (this.productPlusPostTotal = this.post + this.productTotal);
    },
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
  },
};
</script>
<style lang=""></style>
