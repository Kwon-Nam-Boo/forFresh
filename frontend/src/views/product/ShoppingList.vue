<template>
  <div>
    <v-card>
      <v-card-title>배송 정보</v-card-title>
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
<script>
import ProductApi from "../../api/ProductApi";
import PaymentApi from "../../api/PaymentApi";

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
    };
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
        for (var i = 0; i < selected.length; i++) {
          this.shopNo += selected[i] + " ";
          for (var j = 0; j < temp.length; j++) {
            if (selected[i] == temp[j].shoplistNo) {
              this.itemName += temp[j].productName + " ";
              this.productNo += temp[j].productNo + " ";
              this.stockList += temp[j].stock + " ";
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
        // console.log(this.originStock);
        // console.log(this.shopList);
      },
      (error) => {}
    );
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
      PaymentApi.requestPayment(
        {
          userId: storage.getItem("login_user"),
          shoplistNo: this.shopNo,
          stockList: this.stockList,
          productNo: this.productNo,
          itemName: this.itemName,
          quantity: String(this.stockCnt),
          totalAmount: String(this.productPlusPostTotal),
        },
        (res) => {
          // console.log(res.data);
          location.href = res.data;
        },
        (error) => {
          // console.log(error);
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
      // console.log(cost);
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
  },
};
</script>
<style lang="">
</style>