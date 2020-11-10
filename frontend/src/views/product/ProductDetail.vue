<template>
  <div>
    <CategoryList />
    <v-card>
      <v-img :src="`${productInfo.imgUrl}`"></v-img>

      <v-card-title>{{ productInfo.description }}</v-card-title>

      <v-card-text>
        <div class="my-4 subtitle-1">{{ productInfo.productName }}</div>
        <div>가격 : {{ productInfo.productPrice }}</div>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="productInfo.avgRate"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ml-4">
            {{ productInfo.avgRate }}점 {{ productInfo.commentCnt }}
          </div>
          <v-chip-group
            class="ml-5"
            v-model="selection"
            active-class="deep-purple accent-4 white--text"
            column
          >
            <v-chip @click="changeStatus()"
              ><v-icon>mdi-shopping</v-icon></v-chip
            >
          </v-chip-group>

          <v-btn absolute right rounded @click="buyProduct()">구매하기</v-btn>
        </v-row>
      </v-card-text>
    </v-card>
    <v-tabs class="tabs mt-3">
      <!-- <v-spacer></v-spacer> -->
      <v-tab @click="$vuetify.goTo('#detail', options)">상품정보</v-tab>
      <v-tab @click="$vuetify.goTo('#comments', options)">상품평</v-tab>
      <v-tab @click="$vuetify.goTo('#qna', options)">배송/교환/반품 안내</v-tab>
      <!-- <v-spacer></v-spacer> -->
    </v-tabs>
    <v-card id="detail" class="target">
      <v-card-title>상품정보</v-card-title>
      <v-img :src="`${productInfo.detailUrl}`"></v-img>
    </v-card>
    <!-- <v-card id="comments" class="target"> -->
    <ProductComment
      id="comments"
      class="target"
      :items="commentInfo"
      :commentCnt="productInfo.commentCnt"
    />
    <!-- </v-card> -->
    <QnaInfoForm id="qna" class="target" />
    <BottomNavigation />
  </div>
</template>
<script>
import ProductApi from "../../api/ProductApi";
import BottomNavigation from "../../components/product/BottomNavigation";
import CategoryList from "../../components/product/CategoryList";
import * as easings from "vuetify/es5/services/goto/easing-patterns";
import ProductComment from "../../components/product/ProductComment";
import QnaInfoForm from "../../components/product/QnaInfoForm";
const storage = window.sessionStorage;

export default {
  components: {
    BottomNavigation,
    CategoryList,
    ProductComment,
    QnaInfoForm,
  },
  data() {
    return {
      title: "상품 상세보기",
      shoplistNo: "",
      selection: null,
      productInfo: {},
      commentInfo: [],
      duration: 500,
      offset: 0,
      easing: "easeInOutCubic",
      easings: Object.keys(easings),
    };
  },
  created() {
    this.$emit("updateTitle", this.title);
    ProductApi.requestProductDetail(
      {
        userId: storage.getItem("login_user"),
        productNo: this.$route.params.productno,
      },
      (res) => {
        this.productInfo = res.data.object;
        if (res.data.data != null) {
          this.selection = 0;
        }
      },
      (error) => {
        // console.log(error);
      }
    );
    ProductApi.requestProductCommentList(
      this.$route.params.productno,
      (res) => {
        this.commentInfo = res.data;
      },
      (error) => {
        // console.log(error);
      }
    );
  },
  computed: {
    target() {
      const value = this[this.type];
      if (!isNaN(value)) return Number(value);
      else return value;
    },
    options() {
      return {
        duration: this.duration,
        offset: this.offset,
        easing: this.easing,
      };
    },
  },
  methods: {
    buyProduct() {},
    changeStatus() {
      // console.log("들어왓다.");
      if (this.selection == null) {
        ProductApi.addUserShopList(
          {
            userId: storage.getItem("login_user"),
            productNo: this.productInfo.productNo,
          },
          (res) => {
            // console.log(res.data);
          },
          (error) => {
            // console.log(error);
          }
        );
      } else {
        ProductApi.requestUserShoppingListNo(
          {
            userId: storage.getItem("login_user"),
            productNo: this.productInfo.productNo,
          },
          (res) => {
            this.shoplistNo = res.data.shoplistNo;
            ProductApi.deleteShopList(
              this.shoplistNo,
              (res) => {
                //장바구니 삭제 성공
              },
              (error) => {
                //장바구니 삭제 실패
              }
            );
          },
          (error) => {
            // shoplistNo 가져오기 실패
          }
        );
      }
    },
  },
};
</script>

<style lang=""></style>
