<template>
  <v-card>
    <v-alert :type="alertType" v-if="alertMessage">
      {{alertMessage}}
    </v-alert>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      공유받은 냉장고
    </v-card-title>
    <v-row>
      <v-card-text class="mt-5 ma-5">
        {{ alarm.nickName }}님이 {{alarm.refrigName}}을 공유했습니다.
      </v-card-text>
    </v-row>
    <v-divider></v-divider>

    <v-card-actions>
      <v-btn
        color="primary"
        text
        @click="accept"
        :disabled="!isSubmit"
      >
        수락하기
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        color="red accent-3"
        text
        @click="refuse"
        :disabled="!isSubmit"
      >
        거절하기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import RefApi from "../api/RefApi"
const storage = window.sessionStorage;
export default {
  props:['alarm'],
  data(){
    return{
      alertType: false,
      alertMessage: "",
      isSubmit = true,
    }
  },
  methods: {
    accept() {
      this.isSubmit = false;
      const data = {
        refrigNo: this.alarm.refrigNo,
        userId: storage.getItem('login_user'),
      }
      RefApi.shareAllow(
        data,
        (res) => {
          this.alertType = "success";
          this.alertMessage = "냉장고 공유를 수락하셨습니다.";
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {
          this.alertType = "error";
          this.alertMessage = "냉장고 공유 수락 중 에러가 발생했습니다.";
          this.isSubmit = true;
        }
      )
    },
    refuse() {
      this.isSubmit = false;
      const data = {
        refrigNo: this.alarm.refrigNo,
        userId: storage.getItem('login_user'),
      }
      RefApi.shareRefuse(
        data,
        (res) => {
          this.alertType = "success";
          this.alertMessage = "냉장고 공유를 거절하셨습니다.";
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {
          this.alertType = "error";
          this.alertMessage = "냉장고 공유 거절 중 에러가 발생했습니다.";
          this.isSubmit = true;
        }
      )
    },
    closeDialog() {
      this.alertType = false;
      this.alertMessage = "";
      this.isSubmit = true;
      this.$emit('close');
    },
  }
}
</script>
