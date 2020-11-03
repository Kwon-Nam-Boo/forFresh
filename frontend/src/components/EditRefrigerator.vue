<template>
  <v-card>
    <v-btn icon style="float: right;" @click="closeDialog" >
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-card-title class="headline lighten-2 green--text">
      냉장고 편집하기
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
        @click="editRef"
      >
        편집하기
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
      refrigName: "",
    }
  },
  methods: {
    shareRef() {
      const data={
        refrigNo: this.item.refrigNo,
        userId: storage.getItem("login_user"),
        refrigName: this.refrigName,
      };
      RefApi.shareRef(
        data,
        (res) => {
          this.refrigName="";
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
