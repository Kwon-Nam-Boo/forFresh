<template>
    <v-list-item-title @click="logout">로그아웃</v-list-item-title>
</template>
<script>
export default {
    methods:{
        logout(){
            // userinfo session만 없애는 거 & 해당 사용자 검색어 세션 삭제 
            this.$session.remove(this.$store.state.nickname)
            this.$session.remove("userinfo")
            this.$store.dispatch("logout")

            this.$router.push('/').catch(()=>{});
        }   
    }
}
</script>