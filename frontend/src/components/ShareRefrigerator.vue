<template>
  <v-card>
    <v-alert :type="alertType" v-if="alertMessage">
      {{alertMessage}}
    </v-alert>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      냉장고 공유하기
    </v-card-title>
    <v-row>
      <v-text-field
        class="mt-5 ma-5"
        v-model="shareUserName"
        label="공유할 유저 아이디"
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
        @click="shareRef"
        :disabled="!isSubmit"
      >
        공유하기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import RefApi from "../api/RefApi"
export default {
  props:['item'],
  data(){
    return{
      shareUserName: "",
      alertType: false,
      alertMessage: "",
      isSubmit: false,
    }
  },
  watch: {
    shareUserName: function() {
      if(this.shareUserName.length > 0)
        this.isSubmit = true;
      else
        this.isSubmit = false;
    }
  },
  methods: {
    shareRef() {
      const data={
        refrigNo: this.item.refrigNo,
        userId: this.shareUserName,
      };
      RefApi.shareRef(
        data,
        (res) => {
          this.alertType = "success";
          this.alertMessage = "냉장고를 공유하였습니다.";
          setTimeout(()=>{
            this.closeDialog();
          },2000)
        },
        (error) => {

        }
      );
    },
    closeDialog() {
      this.shareUserName="";
      this.alertType = false;
      this.alertMessage = "";
      this.isSubmit = false;
      this.$emit('close');
    },
  }
}
</script>
