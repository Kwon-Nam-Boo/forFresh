<template>
  <v-list-item-title @click="logout">로그아웃</v-list-item-title>
</template>
<script>
const storage = window.sessionStorage;

export default {
  methods: {
    logout() {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "");
      // this.statusMessage = "로그인해주세요.";
      // this.setInfo("로그아웃 성공", "", "");
      location.reload();
    },
  },
};
</script>
