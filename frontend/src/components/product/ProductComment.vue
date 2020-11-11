<template>
  <div>
    <v-card max-width="450" class="mx-auto">
      <v-card-title>상품평 ({{ commentCnt }}) </v-card-title>
      <v-text-field
        :label="`${userInfo.nickname}` + '님 상품평:'"
        v-model="userComment"
        append-icon="mdi-send"
        @click:append="openDialog()"
      ></v-text-field>

      <v-list three-line>
        <template v-for="(item, index) in items">
          <v-subheader
            v-if="item.header"
            :key="item.header"
            v-text="item.header"
          ></v-subheader>

          <v-divider
            v-else-if="item.divider"
            :key="index"
            :inset="item.inset"
          ></v-divider>

          <v-list-item v-else :key="item.pcommentNo">
            <v-list-item-avatar>
              {{ item.nickName }}
            </v-list-item-avatar>
            <!-- <v-list-item-content>
              {{ item.nickName }}
            </v-list-item-content> -->
            <v-list-item-content>
              <v-list-item-title
                v-html="item.commentDetail"
              ></v-list-item-title>
              <v-rating
                :value="item.userRate"
                color="amber"
                dense
                half-increments
                readonly
                size="14"
              >
                {{ item.userRate }}점</v-rating
              >
            </v-list-item-content>
            <div v-if="userInfo.nickname == item.nickName">
              <v-btn
                @click="
                  updateComment(
                    item.pcommentNo,
                    item.commentDetail,
                    item.userRate
                  )
                "
                ><v-icon>mdi-pencil</v-icon></v-btn
              >
              <v-btn @click="deleteComment(item.pcommentNo)"
                ><v-icon>mdi-close</v-icon></v-btn
              >
            </div>
          </v-list-item>
        </template>
      </v-list>
    </v-card>
    <v-dialog v-model="dialog" persistent max-width="400">
      <v-card>
        <div class="d-flex flex-column align-center py-3">
          <v-card-title class="h5 pb-2 font-weight-bold">만족도</v-card-title>
          <div>
            <v-rating color="indigo" medium v-model="userRate"></v-rating>
          </div>
        </div>
        <v-card-actions>
          <v-btn
            color="#3949AB"
            class="font-weight-bold"
            text
            @click="dialog = false"
            >취소</v-btn
          >
          <v-spacer></v-spacer>
          <v-btn
            v-if="!updateStatus"
            color="#3949AB"
            class="font-weight-bold"
            text
            @click="saveComment()"
            >확인</v-btn
          >
          <v-btn
            v-else
            color="#3949AB"
            class="font-weight-bold"
            text
            @click="sendUpdateComment()"
            >확인</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import UserApi from "../../api/UserApi";
import ProductApi from "../../api/ProductApi";
const storage = window.sessionStorage;

export default {
  data() {
    return {
      dialog: false,
      userInfo: {},
      userRate: "",
      updateStatus: false,
      // rules: [(value) => (value || "").length > 0 || "상품평을 입력하세요"],
      userComment: "",
      // items: [
      // {
      //   avatar: "https://cdn.vuetifyjs.com/images/lists/1.jpg",
      //   title: "Brunch this weekend?",
      //   subtitle: `<span class="text--primary">Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
      // },
      // { divider: true, inset: true },
      // ],
      commentNo: "",
    };
  },
  props: ["commentInfo", "commentCnt", "items", "productNo"],
  created() {
    UserApi.getUserInfo(
      storage.getItem("login_user"),
      (res) => {
        this.userInfo = {
          email: res.data.object.userId,
          nickname: res.data.object.nickName,
          phone: res.data.object.phone,
          addr1: res.data.object.addr1,
          addr2: res.data.object.addr2,
          addr3: res.data.object.addr3,
        };
      },
      (error) => {}
    );
  },
  methods: {
    sendUpdateComment() {
      ProductApi.updateUserComment(
        {
          pcommentNo: this.commentNo,
          userRate: this.userRate,
          commentDetail: this.userComment,
        },
        (res) => {
          console.log(res.data);
          //댓글 업데이트 성공
        },
        (error) => {
          //댓글 업데이트 실패
        }
      );
      this.updateStatus = false;
      this.dialog = false;
    },
    updateComment(no, comment, rate) {
      this.userComment = comment;
      this.userRate = rate;
      this.commentNo = no;
      this.updateStatus = true;
    },
    deleteComment(no) {
      ProductApi.deleteUserComment(
        no,
        (res) => {
          console.log(res.data);
          //삭제 성공
        },
        (error) => {
          //삭제 실패
        }
      );
    },
    openDialog() {
      this.dialog = true;
    },
    saveComment() {
      if (this.userComment.length > 0) {
        ProductApi.addUserComment(
          {
            userId: storage.getItem("login_user"),
            productNo: this.productNo,
            userRate: this.userRate,
            commentDetail: this.userComment,
          },
          (res) => {
            // console.log(res.data);
            this.dialog = false;
          },
          (error) => {
            // console.log(error);
            this.dialog = false;
          }
        );
      } else {
        alert("댓글을 입력해주세요");
      }
    },
  },
};
</script>
<style lang=""></style>
