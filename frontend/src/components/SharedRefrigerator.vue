<template>
  <v-card>
    <v-alert :type="alertType" v-if="alertMessage">
      {{alertMessage}}
    </v-alert>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      공유요청받은 냉장고
    </v-card-title>
    <v-row>
      <v-card-text class="mt-5 ma-5">
        공유받은 냉장고라면 내 냉장고에서만 삭제되며,
        직접만든 냉장고라면 모든 유저에게서 이 냉장고가 삭제됩니다.
      </v-card-text>
    </v-row>
    <v-divider></v-divider>

    <v-card-actions>
      <v-btn
        color="primary"
        text
        @click="deleteRef"
      >
        수락하기
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn
        color="red accent-3"
        text
        @click="deleteRef"
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
  props:['item'],
  data(){
    return{
      alertType: false,
      alertMessage: "",
    }
  },
  methods: {
    deleteRef() {
      const data={
        refrigNo: this.item.refrigNo,
        userId: storage.getItem("login_user"),
      };
      RefApi.deleteRef(
        data,
        (res) => {
          this.$emit('getRef');
          this.alertType = "success";
          this.alertMessage = "냉장고 삭제가 완료되었습니다.";
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {
          this.alertType = "error";
          this.alertMessage = "냉장고 삭제 중 에러가 발생하였습니다.";
        }
      );
    },
    closeDialog() {
      this.alertType = false;
      this.alertMessage = "";
      this.$emit('close');
    },
  }
}
</script>
