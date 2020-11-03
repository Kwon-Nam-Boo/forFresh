<template>
  <v-card>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      냉장고 삭제하기
    </v-card-title>
    <v-row>
      <v-card-text class="mt-5 ma-5">
        공유받은 냉장고라면 내 냉장고에서만 삭제되며,
        직접만든 냉장고라면 모든 유저에게서 이 냉장고가 삭제됩니다.
      </v-card-text>
    </v-row>
    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="red accent-3"
        text
        @click="deleteRef"
      >
        삭제하기
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
      
    }
  },
  methods: {
    deleteRef() {
      const data={
        refrigNo: this.item.refrigNo,
        userId: this.shareUserName,
      };
      RefApi.shareRef(
        data,
        (res) => {
          this.shareUserName="";
          this.closeDialog();
        },
        (error) => {

        }
      );
    },
    closeDialog() {
      this.$emit('close');
    },
  }
}
</script>
