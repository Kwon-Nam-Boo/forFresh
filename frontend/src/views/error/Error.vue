<template>
  <v-container class="text-center fill-height">
    <v-row class="ma-0">
      <v-row>
        <v-col>
          <h1 align="center">예기치 못한 문제로 에러가 발생 했습니다.</h1>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <h3 align="center">불편함을 드려서 죄송합니다.</h3>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-btn class="ma-2" color="orange darken-2" dark @click="moveBack">
            <v-icon dark left>mdi-arrow-left</v-icon>이전
          </v-btn>
        </v-col>
      </v-row>
    </v-row>
  </v-container>
</template>
<script>
export default {
  methods: {
    moveBack(){
      this.$router.go(-1);
    }

  }
}
</script>