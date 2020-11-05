<template>
  <div class="text-center">
    <h1 align="center">예기치 못한 문제로 에러가 발생 했습니다.</h1>
    <h3 align="center">불편함을 드려서 죄송합니다.</h3>
    <div>
      <v-btn class="ma-2" color="orange darken-2" dark @click="moveBack">
        <v-icon dark left>mdi-arrow-left</v-icon>이전
      </v-btn>
    </div>
  </div>
</template>
<script>
    export default {
        name : 'error',
        methods: {
            moveBack(){
                this.$router.go(-1);
            }

        }
    }
</script>