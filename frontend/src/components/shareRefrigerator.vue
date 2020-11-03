<template>
  <v-card>
    <v-card-title class="headline lighten-2 green--text">
      냉장고 공유하기
    </v-card-title>
    <v-row>
      <v-text-field
        class="mt-10"
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
        @click="createRef"
      >
        만들기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data(){
    return{
      shareUserName: "",
    }
  },
  methods: {
    createRef() {
      console.log(this.shareUserName);

      
      this.shareUserName="";
      this.closeDialog();
    },
    closeDialog() {
      this.$emit('close');
    },
  }
}
</script>
