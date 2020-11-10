<template>
  <div>
    <v-bottom-navigation
      absolute
      hide-on-scroll
      horizontal
      scroll-target="#hide-on-scroll-example"
    >
      <v-btn color="deep-purple accent-4" text>
        <span>Recents</span>

        <v-icon>mdi-history</v-icon>
      </v-btn>

      <v-btn color="deep-purple accent-4" text>
        <span>Favorites</span>

        <v-icon>mdi-heart</v-icon>
      </v-btn>

      <v-btn color="deep-purple accent-4" text>
        <span>Nearby</span>

        <v-icon>mdi-map-marker</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script>
export default {};
</script>

<style lang="">
</style>