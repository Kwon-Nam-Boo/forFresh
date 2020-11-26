<template>
  <v-card>
    <v-card-title> 배송/교환/반품 안내 </v-card-title>

    <v-card>
      <v-card-title class="subheading font-weight-bold">
        {{ items[0].name }}
      </v-card-title>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item>
          <v-list-item-content>배송방법</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[0].calories }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>배송비</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[0].fat }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>묶음배송 여부</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[0].carbs }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>배송기간</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[0].protein }}
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>

    <v-card>
      <v-card-title class="subheading font-weight-bold">
        {{ items[1].name }}
      </v-card-title>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item>
          <v-list-item-content>교환/반품 비용</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[1].calories }}
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>교환/반품 신청기준일</v-list-item-content>
          <v-list-item-content class="align-end">
            {{ items[1].fat }}
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    itemsPerPage: 4,
    items: [
      {
        name: "배송정보",
        calories: "순차배송",
        fat:
          "무료배송, 1만원 이상 구매시 무료배송, 도서산간 지역 추가비용 없음",
        carbs: "가능",
        protein:
          "2~3일 내 도착, 천재지변 물량 수급 변동 등 예외 상황 발생시 다소 지연될 수 있습니다.",
      },
      {
        name: "교환/반품 안내",
        calories: "5000원, 단 고객 변심 경우에만 발생",
        fat: "단순 변심에 의한 상품 제품 수령 후 교환/반품 30일 이내까지 가능",
      },
    ],
  }),
};
</script>
<style lang="">
</style>