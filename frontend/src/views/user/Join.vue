<template>
  <div>
    <nav-bar :viewType="navbarType" />
    <v-col align="center" md="4" offset-md="4" class="my-10">
      <!-- <v-img
        class="white--text align-end"
        width="600"
        height="300"
        src="../../assets/images/lolbti_logo_2.png"
      ></v-img> -->
      <v-alert
        v-model="alertStatus"
        dismissible
        color="#9DC8C8"
        border="left"
        elevation="2"
        colored-border
        icon="mdi-message-outline"
      >
        {{ alertMessage }}
      </v-alert>
      <v-card class="mx-auto my-5" max-width="500">
        <v-card-text class="display-1 text--primary">회원가입</v-card-text>
        <div class="px-10">
          <v-text-field
            class="mt-10"
            :disabled="authStatus"
            v-model="email"
            label="이메일*"
            
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.email">
            <b>{{ error.email }}</b>
          </div>
          <v-btn
            v-if="authStatus"
            class="mt-3"
            color="btncolor"
            v-bind="attrs"
            v-on="on"
            @click="cancelAuth()"
          >
            인증취소
          </v-btn>

          <!-- 여기는 이메일 인증 버튼 누를 때 modal 창 팝업 되는 곳 -->
          <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="290">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  :disabled="!chkForSendEmail"
                  class="mt-3"
                  color="btncolor"
                  v-bind="attrs"
                  v-on="on"
                  @click="sendMail()"
                  style="margin-left:55%;"
                >
                  이메일 인증
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="headline">
                  {{ email }} 로 메일을 발송했습니다.
                </v-card-title>
                <v-text-field
                  class="mt-10"
                  v-model="userInputCode"
                  label="코드 입력*"
                  
                  hide-details
                ></v-text-field>
                <v-btn
                  color="green darken-1"
                  
                  text
                  @click="authorizeCode()"
                >
                  확인
                </v-btn>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="green darken-1" text @click="dialog = false">
                    닫기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
          <!-- 여기까지 modal 창 -->

          <v-text-field
            class="mt-5"
            v-model="password"
            type="password"
            label="비밀번호*"
            
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.password">
            <b>{{ error.password }}</b>
          </div>

          <v-text-field
            class="mt-5"
            v-model="passwordRe"
            type="password"
            label="비밀번호 확인*"
            
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.passwordRe">
            <b>{{ error.passwordRe }}</b>
          </div>

          <v-text-field
            class="mt-10"
            v-model="nickname"
            label="닉네임*"
            
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.nickname">
            <b>{{ error.nickname }}</b>
          </div>
          <v-btn class="my-5" v-if="!nickNameChkStatus" @click="checkNickName()" style="margin-left:60%;"
            >중복 검사</v-btn
          >
          <v-btn
            v-if="nickNameChkStatus && checkedNickName === nickname"
            color="btncolor"
            class="my-3"
            >인증확인
            <v-icon dark right>{{ svgPath }}</v-icon>
          </v-btn>
          <p class="text-red">* 표시는 필수로 작성해야합니다.</p>
          <v-btn class="my-10" block color="#88dba3" @click="onJoin()"
            >회원가입</v-btn
          >
          <v-divider></v-divider>
        </div>
      </v-card>
    </v-col>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import { mdiCheckCircle } from "@mdi/js";
import UserApi from "../../api/UserApi";

// import NavBar from "../../components/NavBar.vue";

export default {
  components: {
    // NavBar,
  },
  data: () => {
    return {
      alertStatus: false,
      chkForSendEmail: false,
      svgPath: mdiCheckCircle,
      userInputCode: "",
      authStatus: false,
      authorizedEmail: "",
      email: "",
      password: "",
      passwordRe: "",
      nickname: "",
      checkedNickName: "",
      nickNameChkStatus: false,
      passwordSchema: new PV(),
      dialog: false,
      error: {
        email: false,
        password: false,
        passwordRe: false,
        nickname: false,
      },
      isSubmit: false,
      navbarType: true,
      alertMessage: "",
    };
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    passwordRe: function() {
      this.checkForm();
    },
    password: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    },
    nickname: function(val) {
      if (val != this.checkedNickName) {
        this.nickNameChkStatus = false;
        this.error.nickname = "";
      }
    },
  },
  methods: {
    cancelAuth() {
      this.authStatus = false;
      this.email = "";
      this.authorizedEmail = "";
    },
    authorizeCode() {
      if (this.userInputCode == this.$store.state.emailAuthCode) {
        this.authStatus = true;
        this.alertMessage = "인증되었습니다.";
        this.alertStatus = true;
        this.userInputCode = "";
        this.authorizedEmail = this.email;
        this.chkForSendEmail = false;
        this.dialog = false;
      } else {
        this.alertMessage = "인증 코드가 틀렸습니다.";
        this.alertStatus = true;
      }
    },
    sendMail() {
      this.$store.commit("sendMail", this.email);
      this.$store.watch(this.$store.getters.getEmailAuthCode, (n) => {
        if (n == "email overlapped") {
          this.dialog = false;
          this.alertMessage = "이미 가입된 이메일입니다.";
          this.alertStatus = true;
          // console.log(this.$store.getters.getEmailAuthCode);
          this.$store.state.emailAuthCode = "";
        }
      });
    },
    checkNickName() {
      if (this.nickname != "") {
        UserApi.checkNickName(
          this.nickname,
          (res) => {
            // console.log(res.data.data);
            // console.log(res.data.object);
            if (res.data.object == null) {
              // console.log("중복이 아닙니다.");
              this.checkedNickName = this.nickname;
              this.nickNameChkStatus = true;
              this.error.nickname = false;
              this.checkForm();
            } else {
              this.error.nickname = "중복된 닉네임 입니다.";
            }
          },
          (error) => {
            // console.log(error);
          }
        );
      } else {
        this.error.nickname = "닉네임을 입력해주세요.";
      }
    },

    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (!this.authStatus && EmailValidator.validate(this.email)) {
        this.chkForSendEmail = true;
      } else {
        this.chkForSendEmail = false;
      }

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if (this.passwordRe != this.password)
        this.error.passwordRe = "비밀번호가 일치하지 않습니다.";
      else this.error.passwordRe = false;

      if (this.nickname.length <= 0)
        this.error.nickname = "닉네임을 입력해주세요.";
      else this.error.nickname = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    onJoin() {
      // console.log(this.isSubmit);

      // console.log(this.error.nickname);
      // console.log(this.error.email);
      // console.log(this.error.password);
      // console.log(this.error.passwordRe);

      if (this.isSubmit && this.nickNameChkStatus && this.authStatus) {
        this.isSubmit = false;
        UserApi.requestJoin(
          {
            email: this.email,
            password: this.password,
            nickname: this.nickname,
          },
          (res) => {
            this.$router.push({ path: "/login" }).catch(() => {
              // console.log("error 회원가입안됨");
            });
          },
          (error) => {
            this.alertMessage =
              "이메일 인증, 닉네임 중복 검사 등 작성 양식을 다시 확인해주세요";
            this.alertStatus = true;
          }
        );
      } else {
        this.alertMessage =
          "이메일 인증, 닉네임 중복 검사 등 작성 양식을 다시 확인해주세요";
        this.alertStatus = true;
      }
    },
  },
};
</script>
<style scoped>
.error-text {
  color: #e53935;
  font-size: 12px;
}
.text-red{
  font-size: 2vh;
  margin-right: 30%;
}
</style>
