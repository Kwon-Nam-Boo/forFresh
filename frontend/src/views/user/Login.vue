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
        <v-card-text class="text--primary" style="">로그인</v-card-text>
        <div class="px-10">
          <v-text-field
            class="mt-10"
            v-model="email"
            label="이메일"
            outlined
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.email">
            <b>{{ error.email }}</b>
          </div>

          <v-text-field
            class="mt-10"
            v-model="password"
            type="password"
            label="비밀번호"
            outlined
            hide-details
            @keydown.enter="onLogin"
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.password">
            <b>{{ error.password }}</b>
          </div>

          <v-btn
            class="mt-13"
            block
            color="btncolor"
            @click="onLogin"
            :disabled="!isSubmit"
            :class="{ disabled: !isSubmit }"
            >로그인</v-btn
          >
          <v-btn
            class="mt-5"
            block
            color="btncolor"
            @click="$router.push('/join').catch(() => {})"
            >회원가입</v-btn
          >
          <v-btn
            class="mt-5"
            block
            color="btncolor"
            @click="$router.push('/findpassword').catch(() => {})"
            >비밀번호 찾기</v-btn
          >
          <br />
          <br />
        </div>
      </v-card>
    </v-col>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import UserApi from "../../api/UserApi";
// import NavBar from "../../components/NavBar.vue";

const storage = window.sessionStorage;

export default {
  components: {
    // NavBar,
  },
  data: () => {
    return {
      alertStatus: false,
      status: "",
      token: "",
      info: "",
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      navbarType: true,
      statusMessage: "로그인해주세요.",
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
    password: function () {
      this.checkForm();
    },
    email: function () {
      this.checkForm();
    },
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    setInfo(status, token, info) {
      this.status = status;
      this.token = token;
      this.info = info;
    },
    onLogin() {
      if (this.isSubmit) {
        this.isSubmit = false;

        storage.setItem("jwt-auth-token", "");
        storage.setItem("login_user", "");

        UserApi.requestLogin(
          {
            email: this.email,
            password: this.password,
          },
          (res) => {
            // console.log(res);
            this.isSubmit = true;
            // this.$session.start();
            // this.$session.set("userinfo", {
            //   email: this.$store.state.email,
            //   summonerName: this.$store.state.summonerName,
            // });
            // this.$router.push({ path: "/home" }).catch(() => {});
            if (res.data.status) {
              this.statusMessage = res.data.object.userId + "님 환영합니다.";
              console.dir(res.headers["jwt-auth-token"]);
              this.setInfo(
                "성공",
                res.headers["jwt-auth-token"],
                JSON.stringify(res.data.object)
              );
              storage.setItem("jwt-auth-token", res.headers["jwt-auth-token"]);
              storage.setItem("login_user", res.data.object.userId);
              this.$router.push({ path: "/home" }).catch(() => {});
            } else {
              this.setInfo("", "", "");
              this.statusMessage = "로그인해주세요.";
              this.alertMessage = "입력정보를 확인하세요";
              this.alertStatus = true;
            }
          },
          (error) => {
            //console.log(error);
            this.alertMessage = "입력정보를 확인하세요";
            this.alertStatus = true;
          }
        );
      }
    },
    init() {
      if (storage.getItem("jwt-auth-token")) {
        this.statusMessage = storage.getItem("login_user") + "님 환영합니다.";
      } else {
        storage.setItem("jwt-auth-token", "");
      }
    },
    mounted() {
      this.init();
    },
  },
};
</script>
<style scoped>
.error-text {
  color: #e53935;
  font-size: 12px;
}
</style>
