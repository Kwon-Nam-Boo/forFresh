<template>
  <v-carousel cycle hide-delimiters height="250">
    <v-carousel-item
      v-for="(item, i) in items"
      :key="i"
      :src="require(`@/assets/advimg/${item.src}`)"
    ></v-carousel-item>
  </v-carousel>
</template>
<script>
export default {
  data() {
    return {
      items: [
        {
          src: "advertise1.jpg",
        },
        {
          src: "advertise2.png",
        },
        {
          src: "advertise3.jpg",
        },
        {
          src: "advertise4.jpg",
        },
        {
          src: "advertise5.jpg",
        },
        {
          src: "advertise6.jpg",
        },
        {
          src: "advertise7.jpg",
        },
      ],
    };
  },
};
</script>
<style lang="">
</style>