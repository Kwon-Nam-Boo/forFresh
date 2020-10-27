<template>
  <div>
    <nav-bar :viewType="navbarType" />
    <v-col align="center" md="4" offset-md="4" class="my-10">
      <v-card class="mx-auto my-5" max-width="500">
        <v-card-text class="text--primary" style="">비밀번호찾기</v-card-text>
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
                >
                  이메일 인증
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="headline">
                  {{ email }} 로 메일을 발송했습니다.
                </v-card-title>
                <v-card-text
                  >Let Google help apps determine location. This means sending
                  anonymous location data to Google, even when no apps are
                  running.</v-card-text
                >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="green darken-1" text @click="dialog = false">
                    Disagree
                  </v-btn>
                  <v-btn color="green darken-1" text @click="dialog = false">
                    Agree
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
          <!-- 여기까지 modal 창 -->
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

          <v-text-field
            class="mt-5"
            v-model="passwordRe"
            type="password"
            label="비밀번호 확인*"
            outlined
            hide-details
          ></v-text-field>
          <div align="left" class="error-text" v-if="error.passwordRe">
            <b>{{ error.passwordRe }}</b>
          </div>

          <v-btn
            class="mt-13"
            block
            color="btncolor"
            @click="savePassword"
            :disabled="!isSubmit"
            :class="{ disabled: !isSubmit }"
            >저장</v-btn
          >
          <br />
          <br />
        </div>
      </v-card>
    </v-col>
  </div>
</template>

<script>
// import UserApi from "../../api/UserApi";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
export default {
  data: () => {
    return {
      dialog: false,
      chkForSendEmail: false,
      emailAuthCode: "",
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      navbarType: true,
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
    sendMail() {
      this.$store.commit("sendMail", this.email);
    },
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (EmailValidator.validate(this.email)) {
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

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
  },
};
</script>

<style lang="">
</style>