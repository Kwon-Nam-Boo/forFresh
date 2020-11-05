<template>
  <v-container class="text-center fill-height">
    <v-row class="ma-0">
      <v-row>
        <v-col>
          <h1 align="center">페이지를 찾을 수 없습니다.</h1> 
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <h3 align="center">주소가 잘못 입력되었거나, 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</h3>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-btn class="ma-2" color="orange darken-2" dark @click="moveBack">
            <v-icon dark left>mdi-arrow-left</v-icon>이전
          </v-btn>
        </v-col>
        <v-col>
          <v-btn class="ma-2" color="primary" dark @click="moveHome">
            메인페이지
          </v-btn>
        </v-col>
      </v-row>
    </v-row>
  </v-container>
</template>
<script>
    export default {
        name : 'PageNotFound',
        methods: {
            moveHome(){
                this.$router.push("/home");
            },
            moveBack(){
                this.$router.go(-1);
            }
        }
    }
</script>