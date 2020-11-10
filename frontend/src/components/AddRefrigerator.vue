<template>
  <v-card>
    <v-alert :type="alertType" v-if="alertMessage">
        {{alertMessage}}
    </v-alert>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>

    <v-card-title class="headline lighten-2 green--text">
      냉장고 만들기
    </v-card-title>

    <v-row>
      <v-text-field
        class="mt-5 ma-5"
        v-model="refrigName"
        label="냉장고 이름"
        outlined
        hide-details
      ></v-text-field>
    </v-row>
    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="primary"
        text
        @click="createRef"
        :disabled="!isSubmit"
      >
        만들기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import RefApi from "../api/RefApi"
const storage = window.sessionStorage;
export default {
  data(){
    return{
      refrigName: "",
      alertType: false,
      alertMessage: "",
      isSubmit: false,
    }
  },
  watch: {
    refrigName: function() {
      if(this.refrigName.length > 0)
        this.isSubmit = true;
      else
        this.isSubmit = false;
    }
  },
  methods: {
    createRef() {
      const data = {
        userId: storage.getItem("login_user"),
        refrigName: this.refrigName,
      }
      RefApi.addRef(
        data,
        (res) => {
          this.$emit('getRef');
          this.alertType = "success";
          this.alertMessage = "냉장고 등록이 완료되었습니다.";
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {
          this.alertType = "error";
          this.alertMessage = "냉장고 등록 중 에러가 발생했습니다.";
        }
      );

    },
    closeDialog() {
      this.refrigName="";
      this.alertType = false;
      this.alertMessage = "";
      this.isSubmit = false;
      this.$emit('close');
    },
  }
}
</script>
