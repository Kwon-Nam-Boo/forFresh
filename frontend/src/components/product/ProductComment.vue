<template>
  <v-data-table
    :headers="headers"
    :items="comments"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
</template>
<script>
export default {
  data() {
    return {
      headers: [
        {
          text: "닉네임",
          align: "start",
          sortable: false,
          value: "nickName",
        },
        { text: "상품평", value: "comment" },
        { text: "평점", value: "rate" },
        { text: "시간", value: "registDate" },
      ],
      comments: [
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
        {
          nickName: "Frozen Yogurt",
          comment: 159,
          rate: 6.0,
          registDate: 24,
        },
      ],
    };
  },
};
</script>
<style lang="">
</style>