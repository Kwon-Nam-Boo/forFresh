<template>
  <v-card class="ml-1 mr-1 my-12" max-width="374">
    <v-img
      height="250"
      :src="`${product.imgUrl}`"
      @click="
        $router.push('/productdetail/' + `${product.productNo}`).catch(() => {})
      "
    ></v-img>

    <v-card-title>{{ product.description }}</v-card-title>

    <v-card-text>
      <div class="my-4 subtitle-1">{{ product.productName }}</div>

      <v-row align="center" class="mx-0">
        <v-rating
          :value="`${product.avgRage}`"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">{{ product.avgRage }} (댓글수)</div>
        <v-chip-group
          class="ml-5"
          v-model="selection"
          active-class="deep-purple accent-4 white--text"
          column
        >
          <v-chip><v-icon>mdi-shopping</v-icon></v-chip>
        </v-chip-group>

        <div class="grey--text">장바구니 담기</div>
      </v-row>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>
  </v-card>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      selection: 1,
    };
  },
  props: ["product"],
  created() {
    // console.log(this.product);
  },
  methods: {},
};
</script>

<style lang=""></style>
