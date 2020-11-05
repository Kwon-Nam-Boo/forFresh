<template>
  <div>카테고리번호: {{ categoryNo }}</div>
</template>
<script>
export default {
  data() {
    return {
      categoryNo: "",
    };
  },
  created() {
    this.categoryNo = this.$route.params.categroy;
  },
};
</script>

<style lang="">
</style>