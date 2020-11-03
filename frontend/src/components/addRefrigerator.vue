<template>
  <v-card>
    <v-card-title class="headline lighten-2 green--text">
      냉장고 만들기
    </v-card-title>
    <v-row>
      <v-text-field
        class="mt-10"
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
        },
        (error) => {
          console.log(error);
        }
      );

      this.refrigName="";
      this.closeDialog();
    },
    closeDialog() {
      this.$emit('close');
    },
  }
}
</script>
