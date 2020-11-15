<template>
  <v-card class="mx-auto" max-width="500">
    <v-card-title>
      "오늘은 이런 상품
      <br />
      어떤가요?"
    </v-card-title>
    <v-container fluid>
      <v-row dense>
        <v-col v-for="(card, i) in cards" :key="card.title" :cols="card.flex">
          <v-card
            @click="
              $router
                .push('/productdetail/' + `${product[i].productNo}`)
                .catch(() => {})
            "
          >
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

            <v-card-actions style="height: 50px">
              <v-spacer></v-spacer>

              <v-btn
                v-if="product[i].stock > 0"
                absolute
                right
                rounded
                class="my-1"
                @click="
                  $router
                    .push('/buypage/' + product[i].productNo)
                    .catch(() => {})
                "
                >구매하기</v-btn
              >
              <v-btn v-else disabled>품 절</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import ProductApi from "../../api/ProductApi";
export default {
  data: () => ({
    product: [],
    cards: [
      // {
      //   title: "Pre-fab homes",
      //   src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
      //   flex: 12,
      // },
      // {
      //   title: "Favorite road trips",
      //   src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
      //   flex: 6,
      // },
      // {
      //   title: "Best airlines",
      //   src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
      //   flex: 6,
      // },
    ],
  }),
  created() {
    ProductApi.requestProductRecommend(
      {},
      (res) => {
        this.product = res.data;
        for (var i = 0; i < 3; i++) {
          var fl = 6;
          if (i == 0) fl = 12;
          this.cards.push({
            title: this.product[i].description,
            src: this.product[i].imgUrl,
            flex: fl,
          });
        }
      },
      (error) => {}
    );
  },
};
</script>

<style lang="">
</style>